package com.example.gauntlet;

import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;


public class SpatialCollision {

    private final int GRID_SIZE = 4;

    // 5120/4 = 1280
    private final int GRID_DIMENSION = GameData.IMAGE_RESOLUTION_X/4 ;
    public static ArrayList<RectF> gridRects = new ArrayList<RectF>();
    public static ArrayList<ArrayList<RectF>> gridWalls = new ArrayList<ArrayList<RectF>>(16);


    SpatialCollision(){
        int left, right, top, bottom;
        int count = 0;

        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                left = j*GRID_DIMENSION;
                top = i*GRID_DIMENSION;
                right = (j+1)*GRID_DIMENSION;
                bottom = (i+1)*GRID_DIMENSION;

            gridRects.add(new RectF(
                        left, top,
                        right , bottom));
             }

        }

        prepareQuadrants();


    }

   private static void prepareQuadrants() {
        for (int i = 0; i < gridRects.size(); i++) {
            ArrayList<RectF> temp = new ArrayList<RectF>();
            gridWalls.add(i, temp);
        }
    }

    static void loadQuadrants(RectF wall) {
        for (int i = 0; i < gridRects.size(); i++) {
            if (RectF.intersects(wall, gridRects.get(i))) {
                gridWalls.get(i).add(wall);
            }
        }
    }

}
