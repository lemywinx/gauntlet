package com.example.gauntlet;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameMap {
    public static int[][] mMapMatrix;
    private static final int MAP_ROWS = 32;
    private static final int MAP_COLS = 32;
    public ArrayList<Obstacle> obstacleContainer = new ArrayList<Obstacle>();

    GameMap(Context c) {
        mMapMatrix = new int[MAP_ROWS][MAP_COLS];
        int currentRow = 0;
        String line;
        int count=0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    c.getAssets().open(
                            Level.dungeonMaps[1])));

            while( (line = reader.readLine()) != null){
                String[] stringArray = line.split(" ");
                for( int i=0; i<stringArray.length; i++ ){
                    mMapMatrix[currentRow][i] = Integer.parseInt(stringArray[i]);
                    if (mMapMatrix[currentRow][i] == 1) {

//                        if(count++ == 0){

                        obstacleContainer.add(new Obstacle(new RectF(((i * Transform.lowResConversionFactor.x)), (currentRow * Transform.lowResConversionFactor.y),
                                (i * Transform.lowResConversionFactor.x) + 32, (currentRow * Transform.lowResConversionFactor.y) + 32)));

                        Log.d("obstacle-location" + ++count, String.valueOf("("+ (i * Transform.lowResConversionFactor.x) + "," + (currentRow * Transform.lowResConversionFactor.y) + "i"));
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
