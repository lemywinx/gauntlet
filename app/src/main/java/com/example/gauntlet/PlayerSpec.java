package com.example.gauntlet;

import android.graphics.PointF;

class PlayerSpec extends ObjectSpec {
    // This is all the unique specifications
    // for a player
    private static final String tag = "Player";
    private static final String bitmapName = "player_ship";
    private static final float speed = 14f;
    private static final PointF relativeScale =
            new PointF(11f, 7f);

    private static final String[] components = new String [] {
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "PlayerMovementComponent",
            "PlayerSpawnComponent"};

    PlayerSpec() {
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}

