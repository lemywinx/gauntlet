package com.example.gauntlet;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class SpriteGraphicsComponent implements GraphicsComponent{


    private Bitmap mBitmap;
    private Bitmap mBitmapReversed;


    public Bitmap generateSprite(Context c, ObjectSpec s, int spriteIndex, InputStream inputStream){
        Bitmap spriteBitmap = null;
        return spriteBitmap;
    }

    public void setBitmap(Bitmap bitmap){ mBitmap = bitmap; }

    @Override
    public void initialize(Context c, ObjectSpec s, PointF objectSize) {

        // Dimensions for each sprite
        final int frameWidth = 16;
        final int frameHeight = 16;

        // Sprite edge padding
        final int framePadding = 1;

        // Position of first sprite on sheet
        final int sheetStartX = 3;
        final int sheetStartY = 5;


        try{

            AssetManager assetManager = c.getAssets();
            InputStream inputStream;
            inputStream = assetManager.open("sprite_sheet16x16.png");
            mBitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e){
            Log.d("Error", "Failed to load sprite sheet");
        }

        // Find where on sprite sheet a sprite is
        PointF spritePosition = findSpriteLocation(s.getBitmapName());

        // Create sprite based on location in sprite sheet
        mBitmap = Bitmap.createBitmap(mBitmap,
                sheetStartX + (frameWidth * (int) spritePosition.x) + (framePadding * (int) spritePosition.x * 2),
                sheetStartY + (frameHeight * (int) spritePosition.y) + (framePadding * (int) spritePosition.y * 2),
                frameWidth, frameHeight);

        mBitmap = Bitmap.createScaledBitmap(
                mBitmap,
                (int)objectSize.x, (int)objectSize.y,
                false);

        // Create a mirror image of the bitmap if needed
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        mBitmapReversed = Bitmap.createBitmap(mBitmap,
                0, 0,
                mBitmap.getWidth(),
                mBitmap.getHeight(),
                matrix, true);

    }

    @Override
    public void initialize(Context c, ObjectSpec s, PointF screenSize, PointF sheetPosition) {

    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t) {

        canvas.drawBitmap(mBitmap,
                t.getLocation().x,
                t.getLocation().y,
                paint);

    }

    private PointF findSpriteLocation(String spriteName){
        // Initialize sprite point
        PointF position = new PointF();
        position.x = 0;
        position.y = 0;

        // Set sprite position based on sprite name
        switch (spriteName){
            case "player_stationary":
                position.x = 23;
                position.y = 11;
                return position;
            case "ghost_stationary":
                position.x = 0;
                position.y = 0;
                return position;
            case "key":
                position.x = 19;
                position.y = 6;
                break;
            default:
                Log.d("Error", "Failed to find sprite");
                break;
        }

        return position;
    }
}
