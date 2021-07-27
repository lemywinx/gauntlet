package com.example.gauntlet;

public class PassKeySpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform t) {
        // spawn passkey (currently spawns at center of screen)
        t.setLocation(t.getmScreenSize().x/2, t.getmScreenSize().y/2);
    }
}
