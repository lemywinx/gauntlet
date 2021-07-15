package com.example.gauntlet;

import android.graphics.PointF;
import android.graphics.RectF;

class BackgroundMovementComponent implements MovementComponent {
    PointF mPlayerPadding;

    BackgroundMovementComponent() {
        mPlayerPadding = new PointF(0, 0);
    }

    @Override
    public boolean move(long fps,
                        Transform t,
                        Transform playerTransform) {

        PointF screenSize = t.getmScreenSize();
        mPlayerPadding.x = screenSize.x / 10;
        mPlayerPadding.y = screenSize.y / 8;
        RectF backgroundPortionToDraw = t.getCollider();

        int width = (int)screenSize.x;
        int height = (int)screenSize.y;
        PointF playerLocation = playerTransform.getLocation();
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;


        if ((playerLocation.x - mPlayerPadding.x) < 0) {
            left = 0;
            right = (int)(mPlayerPadding.x * 2);
        }

        else if ((playerLocation.x + mPlayerPadding.x) > width) {
            right = width;
            left = (int)(right - (mPlayerPadding.x * 2));
        }

        else {
            right = (int)(playerLocation.x + mPlayerPadding.x);
            left = (int)(playerLocation.x - mPlayerPadding.x);
        }

        if ((playerLocation.y + mPlayerPadding.y) > height) {
            bottom = height;
            top = (int)(bottom - (mPlayerPadding.y * 2));
        }

        else if ((playerLocation.y - mPlayerPadding.y) < 0) {
            top = 0;
            bottom = (int)(mPlayerPadding.y * 2);
        }

        else {
            top = (int)(playerLocation.y - mPlayerPadding.y);
            bottom = (int)(playerLocation.y + mPlayerPadding.y);
        }

        backgroundPortionToDraw.top = top;
        backgroundPortionToDraw.bottom = bottom;
        backgroundPortionToDraw.left = left;
        backgroundPortionToDraw.right = right;


        return true;
    }
}
