package com.example.gauntlet;

import android.graphics.PointF;
import android.graphics.RectF;

class BackgroundMovementComponent implements MovementComponent {
    PointF mPlayerPadding;
    public static RectF backgroundPortionToDraw1;

    BackgroundMovementComponent() {
        mPlayerPadding = new PointF(0, 0);
    }

    @Override
    public boolean move(long fps,
                        Transform t,
                        Transform playerTransform) {

        PointF screenSize = t.getmScreenSize();
        mPlayerPadding.x = 5120 / 6;
        mPlayerPadding.y = 5120 / 10;
        RectF backgroundPortionToDraw = t.getCollider();
        backgroundPortionToDraw1 = backgroundPortionToDraw;

        int width = 5120;
        int height = 5120;
        PointF playerLocation = playerTransform.getLocation();
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        // TO DO: Redefine how we are going about below with player location in 5120 map..
        /*
         if ((playerLocation.x - mPlayerPadding.x) < 0) {
            left = 0;
            right = (int)(mPlayerPadding.x * 2);
        }

        else if ((playerLocation.x + mPlayerPadding.x) > width) {
            right = width;
            left = (int)(right - (mPlayerPadding.x * 2));
        }
         */



            right = (int)(playerLocation.x + mPlayerPadding.x);
            left = (int)(playerLocation.x - mPlayerPadding.x);

        /*
            if ((playerLocation.y + mPlayerPadding.y) > height) {
            bottom = height;
            top = (int)(bottom - (mPlayerPadding.y * 2));
        }
         */

    /*
        if ((playerLocation.y - mPlayerPadding.y) < 0) {
            top = 0;
            bottom = (int)(mPlayerPadding.y * 2);
        }
     */



            top = (int)(playerLocation.y - mPlayerPadding.y);
            bottom = (int)(playerLocation.y + mPlayerPadding.y);

        backgroundPortionToDraw.top = top;
        backgroundPortionToDraw.bottom = bottom;
        backgroundPortionToDraw.left = left;
        backgroundPortionToDraw.right = right;


        return true;
    }
}
