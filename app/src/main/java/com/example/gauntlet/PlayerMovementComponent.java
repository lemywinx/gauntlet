package com.example.gauntlet;

import android.content.Context;
import android.graphics.*;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PlayerMovementComponent implements MovementComponent {
    public static PointF XYTracker = new PointF(0, 0);
    Point initRelLoc = new Point(0, 0);
    PointF initXYTracker = new PointF(0, 0);
    PointF initPlayerLoc = new PointF(0, 0);
    public static PointF screenLocation = new PointF();
    GameMap gameMap;
    Context context;

    PlayerMovementComponent(Context c, PointF screenSize) {
       context = c;
        gameMap = new GameMap(c);
        screenLocation.x = screenSize.x / 2;
        screenLocation.y = screenSize.y / 2;
    }


    @Override
    public boolean move(long fps, Transform t,
                        Transform playerTransform){
        // How high is the screen?
        float screenHeight = t.getmScreenSize().y;
        PointF velocity;
        // Resetting movement upon each animation - if finger was lifted. Want to reset info on heading right/left.
        // This does not affect held down actions as the velocity components will be the same until a move is made. (Only calculated when available to move and not outside of that)
        PointF location = t.getLocation();


      //  initXYTracker.x = XYTracker.x;
       // initXYTracker.y = XYTracker.y;
        initPlayerLoc.x = location.x;
        initPlayerLoc.y = location.y;
        initRelLoc.x = Transform.relativePlayerLocation.x;
        initRelLoc.y = Transform.relativePlayerLocation.y;

        // If circle was pressed and is still held down.
        if (t.isAvailableToMove()) {
            // Setting vector components based off angle passed in inputComponent by Joystick.
            // Just multiplying x/y speed by cos/sin of theta to find those components.
            t.setMovementComponents();
            velocity = t.getMovementComponents();
            // Once, components found - very simple implementation below.

            location.x += (velocity.x / fps);
            location.y += velocity.y / fps;

            // Location.x & location.y within 0-5119
            // Put spawn location within the map..




            Transform.relativePlayerLocation.y = (int)Math.round((location.y) / Transform.lowResConversionFactor.y);
            Transform.relativePlayerLocation.x = (int)Math.round((location.x) / Transform.lowResConversionFactor.x);
            System.out.println("CHECK: " + t.getSize().x);
            if ((velocity.x / fps) < 0) {
                Transform.relativePlayerLocation.x = (int)Math.round((location.x - (t.getSize().x * .70)) / Transform.lowResConversionFactor.x);
            }

            else {
                Transform.relativePlayerLocation.x = (int)Math.round((location.x - (t.getSize().x * .30)) / Transform.lowResConversionFactor.x);
            }

            if ((velocity.y / fps) > 0) {
                Transform.relativePlayerLocation.y = (int)Math.round((location.y - (t.getObjectHeight() * .05)) / Transform.lowResConversionFactor.y);
            }

            else {
                Transform.relativePlayerLocation.y = (int)Math.round((location.y - (t.getObjectHeight() * .75)) / Transform.lowResConversionFactor.y);
            }


            /*
                if ((velocity.x / fps) > 0) {
                Transform.relativePlayerLocation.x = (int)Math.round((location.x + 40) / Transform.lowResConversionFactor.x);
            }

            else {
                Transform.relativePlayerLocation.x = (int)Math.round((location.x - 15) / Transform.lowResConversionFactor.x);
            }

            if ((velocity.y / fps) > 0) {
                Transform.relativePlayerLocation.y = (int)Math.round((location.y + t.getObjectHeight() - 15) / Transform.lowResConversionFactor.y);
            }

            else {
                Transform.relativePlayerLocation.y = (int)Math.round((location.y + 15) / Transform.lowResConversionFactor.y);
            }

             */

            t.updateCollider();




            // Use entire large bitmap and track the player's location within it.
            // From rect based off where you are in the entire map.

                Log.d("Error",Transform.relativePlayerLocation.y +" , " + Transform.relativePlayerLocation.x);
                Log.d("location",location.x +" , " + location.y);


                        // Handle Collision

//                        gameMap.obstacleContainer
                    RectF objectRect = gameMap.gameHashTable.get(new Point(Transform.relativePlayerLocation.y,Transform.relativePlayerLocation.x));

                    for (int i = 0; i < gameMap.obstacleContainer.size(); i++) {
                        if (RectF.intersects(gameMap.obstacleContainer.get(i).getLocation(), t.getCollider())) {

                            location.x = initPlayerLoc.x;
                            location.y = initPlayerLoc.y;
                        }
                    }



                    /*
                    if(objectRect != null && RectF.intersects(objectRect,t.getCollider())){
                        location.x = initPlayerLoc.x;
                        location.y = initPlayerLoc.y;
                        Transform.relativePlayerLocation.x = initRelLoc.x;
                        Transform.relativePlayerLocation.y = initRelLoc.y;

                        Log.d("object-rect-t", "" + objectRect.top+ "\t" +  objectRect.left + "\t" +  objectRect.bottom+ "\t" +  objectRect.right );


                        if (gameMap.mMapMatrix[Transform.relativePlayerLocation.y][Transform.relativePlayerLocation.x] == 1) {


                    }
                }
                     */







            // Manually setting the below bool variables so our background movement component moves only when player does..
            if (velocity.x > 0) {
                t.headRight();
            }

            else if (velocity. x < 0) {
                t.headLeft();
            }

        }

        // Keeping player constrained to screen size..
        /*
                if (location.y > (screenHeight - (t.getObjectHeight() / 2))) {
            location.y = screenHeight - (t.getObjectHeight() / 2);
        }

        else if (location.y < 0 + (t.getObjectHeight() / 2)) {
            location.y = 0 + (t.getObjectHeight() / 2);
        }

        if (location.x > (t.getmScreenSize().x)) {
            location.x = t.getmScreenSize().x;
        }

        else if (location.x < 0) {
            location.x = 0;
        }

         */


        t.updateCollider();

        return true;

    }

     void testTransform(){


         String line = "";

         for(int i=0; i<32; i++){
            for(int j=0; i<32; i++){
                line += String.valueOf(gameMap.mMapMatrix[i][j]);
            }
                line+="\n";

         }
            Log.d("matrix",line);
     }



}
