package com.example.gauntlet;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;

public class AnimationComponent extends SpriteGraphicsComponent{

    public ArrayList<Bitmap> inititalize(Context c, ObjectSpec s){
        ArrayList<Bitmap> animationList = new ArrayList<Bitmap>();
        Bitmap tempBitmap = null;
        AssetManager assetManager = c.getAssets();
        InputStream inputStream = null;


        try{
            inputStream = assetManager.open("sprite_sheet16x16.png");

        } catch (IOException e){
            Log.d("Error", "Failed to find Sprite Sheet");
        }

        switch (s.getBitmapName()){
            case "player":
                for (int i = 0; i < 24; i++){
                    tempBitmap = generateSprite(c, s, i, inputStream);
                    animationList.add(tempBitmap);
                }
                break;
            default:
        }

        return animationList;
    }
}
