package com.example.gauntlet;

import java.util.Random;

public class PowerUpSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerLTransform, Transform t){
        Random random = new Random();


        t.setLocation(2150, 2150);
    }
}
