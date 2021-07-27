package com.example.gauntlet;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.animation.Animation;

import java.util.ArrayList;

public class PlayerAnimationComponent {

    Context mContext;
    ObjectSpec mObjectSpec;

    // Start frames of each animation
    final int FACING_NORTH = 0;
    final int FACING_NORTHEAST = 1;
    final int FACING_EAST = 2;
    final int FACING_SOUTHEAST = 3;
    final int FACING_SOUTH = 4;
    final int FACING_SOUTHWEST = 5;
    final int FACING_WEST = 6;
    final int FACING_NORTHWEST= 7;

    // How many frames each animation is
    final int ANIMATION_LENGTH = 3;

    AnimationComponent generator = new AnimationComponent();

    private ArrayList<Bitmap> mSpriteArray;

    void initialize(Context c, ObjectSpec s){ mSpriteArray = generator.inititalize(c, s); }

    void determineAnimation(Transform t){
        double currentAngle = t.getAngle();
        if (currentAngle == 0){
            // heading east
        } else if (currentAngle < 90){
            // heading north east
        } else if (currentAngle == 90){
            // heading north
        } else if (currentAngle < 180){
            // heading northwest
        } else if (currentAngle == 180){
            // heading west
        } else if (currentAngle < 270){
            // heading south west
        } else if (currentAngle == 270){
            // heading south
        } else {
            // heading south east

        }
    }


}
