package com.example.gauntlet;

import android.content.Context;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameMap {
    public static int[][] mMapMatrix;
    private static  int MAP_ROWS;
    private static  int MAP_COLS;
    public ArrayList<Obstacle> obstacleContainer = new ArrayList<Obstacle>();
    private Point lowResFactor;

    GameMap(Context c) {
        lowResFactor = new Point(GameData.LOWRES_CONV_FACTOR_X,
                                 GameData.LOWRES_CONV_FACTOR_X);

        MAP_ROWS = GameData.ROWS;
        MAP_COLS = GameData.COLS;

        
        mMapMatrix = new int[MAP_ROWS][MAP_COLS];
        int currentRow = 0;
        String line;
        int count=0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    c.getAssets().open(
                            Level.dungeonMaps[1])));

            while( (line = reader.readLine()) != null){
                Log.d("Sprint",line + " Size \t" + line.length());
                String[] stringArray = line.split(" ");
                Log.d("Array-L",String.valueOf(stringArray.length));
                for( int i=0; i<stringArray.length; i++ ){
                    mMapMatrix[currentRow][i] = Integer.parseInt(stringArray[i]);
                    if (mMapMatrix[currentRow][i] == 1) {

//                        if(count++ == 0){

                        obstacleContainer.add(new Obstacle(new RectF(((i * lowResFactor.x)), (currentRow * lowResFactor.y),
                                (i * lowResFactor.x) + 160, (currentRow * lowResFactor.y) + 160)));

                        Log.d("obstacle-location" + ++count, String.valueOf("("+ (i * lowResFactor.x) + "," + (currentRow * lowResFactor.y) + "i"));
//                        }

                    }

                }


                currentRow++;

                Log.d("map-row", String.valueOf(currentRow));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}