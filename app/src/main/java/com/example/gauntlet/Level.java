package com.example.gauntlet;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Level {

    // Keep track of specific types
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int FIRST_PLAYER_ARROW = 2;
    public static final int LAST_PLAYER_ARROW = 4;
    public static int mNextPlayerArrow;
    public static final int FIRST_ALIEN = 5;

    public static final int LAST_ALIEN = 5;
    public static final int FIRST_ALIEN_ARROW = 6;
    public static final int LAST_ALIEN_ARROW = 10;
    public static int mNextAlienArrow;
    public static int[][] mMapMatrix;
    private static final int MAP_ROWS = 32;
    private static final int MAP_COLS = 32;

    // track all dungeon maps
    Context appContext;
    public static int currentDungeon = 1;
    public static final int lastDungeon = 1;

    // keep dungeonMaps[0] empty because dungeon levels start at 1
    public static final String[] dungeonMaps = {
            "",
            "gauntlet_room_1_text_32.txt"
    };

    // This will hold all the instances of GameObject
    private ArrayList<GameObject> objects;

    public Level(Context context,
                 PointF mScreenSize,
                 GameEngine ge) {

        appContext = context;

        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(
                context, mScreenSize, ge);
        mMapMatrix = new int[MAP_ROWS][MAP_COLS];
        buildGameObjects(factory);

        loadMap();
    }

    ArrayList<GameObject> buildGameObjects(
            GameObjectFactory factory) {

        objects.clear();
        objects.add(BACKGROUND_INDEX, factory
                .create(new BackgroundSpec()));

        objects.add(PLAYER_INDEX, factory
                .create(new PlayerSpec()));

        // Spawn the player's Arrows
        for (int i = FIRST_PLAYER_ARROW;
             i != LAST_PLAYER_ARROW + 1; i++) {

            objects.add(i, factory
                    .create(new PlayerArrowSpec()));
        }

        mNextPlayerArrow = FIRST_PLAYER_ARROW;

        // Create some aliens
        objects.add(FIRST_ALIEN, factory
                .create(new GhostChaseSpec()));

        // Create some alien Arrows
        for (int i = FIRST_ALIEN_ARROW; i != LAST_ALIEN_ARROW + 1; i++) {
            objects.add(i, factory
                    .create(new GhostArrowSpec()));
        }
        mNextAlienArrow = FIRST_ALIEN_ARROW;


        return objects;
    }

    ArrayList<GameObject> getGameObjects() {
        return objects;
    }

    void loadMap() {

        int currentRow = 0;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    appContext.getAssets().open(
                            dungeonMaps[currentDungeon])));

            while( (line = reader.readLine()) != null){
                String[] stringArray = line.split(" ");
                for( int i=0; i<stringArray.length; i++ ){
                    mMapMatrix[currentRow][i] = Integer.parseInt(stringArray[i]);
                }
                currentRow++;

                Log.d("map-row", String.valueOf(currentRow));

                for(int i=0; i<32; i++){
                    String temp = " Col: " + i + "\t Val: " +mMapMatrix[2][i] ;
                    Log.d("map-col", temp);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    int getCurrentDungeon() { return currentDungeon; }

    public void goToNextDungeon() {
        if (currentDungeon != lastDungeon) {
            currentDungeon++;
            loadMap();
        }
    }
}
