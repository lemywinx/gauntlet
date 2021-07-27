package com.example.gauntlet;

import java.util.Random;

public class PowerUpSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerLTransform, Transform t){
        Random random = new Random();




        float upperXLimit = t.getmScreenSize().x - t.getSize().x;

        float upperYLimit = t.getmScreenSize().y - t.getSize().y;

        float lowerXLimit = 0 + t.getSize().x;
        float lowerYLimit = 0 + t.getSize().y;

        float horizontalLocation = (random.nextFloat() * (upperXLimit - lowerXLimit)) + lowerXLimit;
        float verticalLocation = (random.nextFloat() * (upperYLimit - lowerYLimit) + lowerYLimit);

        t.setLocation(horizontalLocation, verticalLocation);
    }
}
