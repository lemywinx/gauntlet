package com.example.gauntlet;

public class DoorSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform t) {
        // spawn key (currently spawns at x=screen bounds, y=0)
        t.setLocation(t.getmScreenSize().x/2, 0);
    }
}