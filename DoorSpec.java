package com.example.gauntlet;

import android.graphics.PointF;

public class DoorSpec extends ObjectSpec{
 // This class contains all of the class specific specifications
    //for the door object

    private static final String tag = "Door";
    private static final String bitmapName = "alien_ship2";
    private static final float speed = 0f;
    private static final PointF relativeScale =
            new PointF(10f, 10f);

    private static final String[] components = new String [] {
            "DoorSpawnComponent",
            "SimpleMovementComponent",
            "StdGraphicsComponent"};

    DoorSpec() {
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}