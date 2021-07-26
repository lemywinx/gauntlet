package com.example.gauntlet;

import java.util.Random;

public class GoblinSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerLTransform, Transform t) {
        Random random = new Random();


        boolean leftSide = random.nextBoolean();
        float distance =   t.getmScreenSize().x;

        float spawnHeight = t.getmScreenSize().y;

        // Spawn the goblin
        if(leftSide){
            t.setLocation(500, 500);
            t.headRight();
        }else{
            t.setLocation(distance - 500, spawnHeight - 500);
            t.headLeft();
        }

    }
}
