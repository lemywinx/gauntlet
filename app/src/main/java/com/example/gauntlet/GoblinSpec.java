package com.example.gauntlet;

import android.graphics.PointF;

public class GoblinSpec extends ObjectSpec{
    // This is all the unique specifications
    // for a goblin that chases the player
    private static final String tag = "Goblin";
    private static final String bitmapName = "goblin";
    private static final float speed = 14f;
    private static final PointF relativeScale =
            new PointF(10f, 8f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "GoblinMovementComponent",
            "GoblinSpawnComponent"};

    GoblinSpec(){
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}
