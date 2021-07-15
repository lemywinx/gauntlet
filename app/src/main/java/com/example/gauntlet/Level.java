package com.example.gauntlet;
// test for commit
import android.content.Context;
import android.graphics.PointF;

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

    // This will hold all the instances of GameObject
    private ArrayList<GameObject> objects;
    public Level(Context context,
                 PointF mScreenSize,
                 GameEngine ge){

        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(
                context, mScreenSize, ge);

        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(
            GameObjectFactory factory){

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

    ArrayList<GameObject> getGameObjects(){
        return objects;
    }


}
