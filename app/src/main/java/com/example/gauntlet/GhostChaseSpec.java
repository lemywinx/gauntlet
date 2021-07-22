package com.example.gauntlet;

import android.graphics.PointF;

class GhostChaseSpec extends ObjectSpec {
    // This is all the unique specifications
    // for an alien that chases the player
    private static final String tag = "Alien";
    private static final String bitmapName = "ghost_stationary";
    private static final float speed = 14f;
    private static final PointF relativeScale =
            new PointF(12f, 12f);

    private static final String[] components = new String [] {
            "SpriteGraphicsComponent",
            "GhostChaseMovementComponent",
            "GhostSpawnComponent"};

    GhostChaseSpec(){
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}
