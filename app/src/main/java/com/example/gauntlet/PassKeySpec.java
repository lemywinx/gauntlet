package com.example.gauntlet;

import android.graphics.PointF;

public class PassKeySpec extends ObjectSpec{
    // This contains all the unique specifications
    // for a key that the player can use to open locked doors

    private static final String tag = "PassKey";
    private static final String bitmapName = "alien_ship2";
    private static final float speed = 0f;
    private static final PointF relativeScale =
            new PointF(10f, 10f);

    private static final String[] components = new String [] {
            "PassKeySpawnComponent",
            "SimpleMovementComponent",
            "StdGraphicsComponent"};

    PassKeySpec() {
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}