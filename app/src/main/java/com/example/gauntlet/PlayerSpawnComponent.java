package com.example.gauntlet;

class PlayerSpawnComponent implements SpawnComponent {

    @Override
    public void spawn(Transform playerTransform, Transform t) {

        // Spawn in the centre of the screen
        t.setLocation(
                5120 /2,
                5120 / 2);


    }
}

