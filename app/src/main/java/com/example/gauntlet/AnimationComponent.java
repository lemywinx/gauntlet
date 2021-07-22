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

public class AnimationComponent {

    public ArrayList<Bitmap> inititalize(Context c, ObjectSpec s){
        ArrayList<Bitmap> animationList = new ArrayList<Bitmap>();

        try{

            AssetManager assetManager = c.getAssets();
            InputStream inputStream;
            inputStream = assetManager.open("sprite_sheet16x16.png");

        } catch (IOException e){
            Log.d("Error", "Failed to find Sprite Sheet");
        }
        return animationList;
    }
}
