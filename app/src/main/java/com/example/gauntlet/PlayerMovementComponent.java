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
    GameMap gameMap;
    Context context;

    PlayerMovementComponent(Context c) {
       context = c;
        gameMap = new GameMap(c);
        testTransform();
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

            location.x += velocity.x / fps;
            location.y += velocity.y / fps;

            // Location.x & location.y within 0-5119
            // Put spawn location within the map..

            Transform.relativePlayerLocation.x = (int)Math.floor((location.x) / Transform.lowResConversionFactor.x);
            Transform.relativePlayerLocation.y = (int)Math.floor((location.y) / Transform.lowResConversionFactor.y) - 1;

            Log.d("Test-1-top", String.valueOf(BackgroundMovementComponent.backgroundPortionToDraw1.top));
            Log.d("Test-1-left", String.valueOf(BackgroundMovementComponent.backgroundPortionToDraw1.left));

           // XYTracker.x += (velocity.x / fps) / Transform.lowResConversionFactor.x;
            //XYTracker.y += (velocity.y / fps) / Transform.lowResConversionFactor.y;
            /*
            if (XYTracker.x >= 1){
                Transform.relativePlayerLocation.x++;
                XYTracker.x = 0;
            }

            else if (XYTracker.x <= -1) {
                Transform.relativePlayerLocation.x--;
                XYTracker.x = 0;
            }

            if (XYTracker.y >= 1) {
                Transform.relativePlayerLocation.y++;
                XYTracker.y = 0;
            }


             */

            // Use entire large bitmap and track the player's location within it.
            // From rect based off where you are in the entire map.

                Log.d("Error",Transform.relativePlayerLocation.x +" , " + Transform.relativePlayerLocation.y);
                System.out.println("COORD: " + Transform.relativePlayerLocation.x + "," + Transform.relativePlayerLocation.y +
                        " - " + GameMap.mMapMatrix[Transform.relativePlayerLocation.x][Transform.relativePlayerLocation.y]);

                /*
                    if (gameMap.mMapMatrix[Transform.relativePlayerLocation.y][Transform.relativePlayerLocation.x] == 0) {
                    location.x = initPlayerLoc.x;
                    location.y = initPlayerLoc.y;
                    Transform.relativePlayerLocation.x = initRelLoc.x;
                    Transform.relativePlayerLocation.y = initRelLoc.y;
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



        t.updateCollider();

        return true;

    }

     void testTransform(){


         String line;
         int currentRow = 0;
         try {
             BufferedReader reader = new BufferedReader(new InputStreamReader(
                     context.getAssets().open(
                             Level.dungeonMaps[1])));

             while( (line = reader.readLine()) != null){
                 String[] stringArray = line.split(" ");
                 for( int i=0; i<stringArray.length; i++ ){
                     gameMap.mMapMatrix[currentRow][i] = Integer.parseInt(stringArray[i]);
                     if (gameMap.mMapMatrix[currentRow][i] !=
                             gameMap.mMapMatrix[Transform.relativePlayerLocation.x][Transform.relativePlayerLocation.y]
                     ) {
                         Log.d("game-test",
                                 "File:" + gameMap.mMapMatrix[currentRow][i] + "Read:"
                                         + gameMap.mMapMatrix[Transform.relativePlayerLocation.x][Transform.relativePlayerLocation.y]
                                        + "\tIndex:"+ "(" + currentRow + "," + i + ")");
                     }

                 }


                 currentRow++;

//                 Log.d("map-row", String.valueOf(currentRow));

             }

         } catch (IOException ex) {
             ex.printStackTrace();
         }


     }

}
