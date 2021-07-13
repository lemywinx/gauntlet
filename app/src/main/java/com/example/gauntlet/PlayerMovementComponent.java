package com.example.gauntlet;

import android.graphics.PointF;

class PlayerMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, Transform t,
                        Transform playerTransform){
        // How high is the screen?
        float screenHeight = t.getmScreenSize().y;
        PointF velocity;
        // Resetting movement upon each animation - if finger was lifted. Want to reset info on heading right/left.
        // This does not affect held down actions as the velocity components will be the same until a move is made. (Only calculated when available to move and not outside of that)
        PointF location = t.getLocation();

        // If circle was pressed and is still held down.
        if (t.isAvailableToMove()) {
            // Setting vector components based off angle passed in inputComponent by Joystick.
            // Just multiplying x/y speed by cos/sin of theta to find those components.
            t.setMovementComponents();
            velocity = t.getMovementComponents();
            // Once, components found - very simple implementation below.
            location.x += velocity.x / fps;
            location.y += velocity.y / fps;

            // Manually setting the below bool variables so our background movement component moves only when player does..
            if (velocity.x > 0) {
                t.headRight();
            }

            else if (velocity. x < 0) {
                t.headLeft();
            }

        }

        // Keeping player constrained to screen size..

        if (location.y > (screenHeight - t.getObjectHeight())) {
            location.y = screenHeight - t.getObjectHeight();
        }

        else if (location.y < 0) {
            location.y = 0;
        }

        if (location.x > (t.getmScreenSize().x - 220)) {
            location.x = t.getmScreenSize().x - 220;
        }

        else if (location.x < 0) {
            location.x = 0;
        }

        t.updateCollider();

        return true;

    }
}
