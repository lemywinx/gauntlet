package com.example.gauntlet;

import java.util.Random;

class GhostSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform playerLTransform, Transform t) {


        /*
        Random random = new Random();

        boolean leftSide = random.nextBoolean();
        float distance =   t.getmScreenSize().x;

        // Generate a height to spawn at where
        // the entire ship is vertically on-screen

        float spawnHeight = t.getmScreenSize().y;

        // Spawn the ship

        if(leftSide){

            t.setLocation(10, 10);
            t.headRight();

        }

        else{

            t.setLocation(distance - 10, spawnHeight - 10);
            t.headLeft();
        }

         */

        t.setLocation(0 + 150, 0 + 150);


    }
}
