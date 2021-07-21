package com.example.gauntlet;

import android.content.Context;
import android.content.Context;
import android.graphics.*;
import android.util.Log;


// Overall, same idea as BackgroundGraphics component within ScrollingShooter.

public class BackgroundGraphicsComponent implements GraphicsComponent{
    private Bitmap mBitmap;
    private Bitmap wallBitmap;
    private GameMap gameMap;
    public static Bitmap bitmapNew;
    private boolean isFirst = true;
    public static Rect initRect = new Rect();
    public static PointF diff = new PointF();
    int top = 0;
    int left = 0;

    // Draw on top of original bitmap..
    // Use low res map and draw original wall/stationary structure upon initialization.
    // Tracking relative position of player..
    @Override
    public void initialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().getIdentifier(s.getBitmapName(), "drawable", c.getPackageName());
        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);
        wallBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.alien_ship2);


        // Check documentation for filtering...
        mBitmap = Bitmap.createScaledBitmap(mBitmap, 5120, 5120, false);
        // TO DO: Confirm scaling ratio from text map to  full drawn map..
        wallBitmap = Bitmap.createScaledBitmap(wallBitmap, 160, 160, false);
//        bitmapNew = Bitmap.createScaledBitmap(bitmapNew, (int)objectSize.x, (int)objectSize.y, false);
        bitmapNew = Bitmap.createBitmap(5120, 5120, mBitmap.getConfig());
        gameMap = new GameMap(c);

        Canvas canvas = new Canvas(bitmapNew);
        canvas.drawBitmap(mBitmap, 0f, 0f, null);
        //Paint paint = new Paint();

        // Using matrix to update player..

        /*
         Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0, 0, bitmapNew.getWidth(), bitmapNew.getHeight()), new RectF(0, 0, objectSize.x, objectSize.y), Matrix.ScaleToFit.CENTER);
         bitmapNew = Bitmap.createBitmap(bitmapNew, 0, 0, bitmapNew.getWidth(), bitmapNew.getHeight(), matrix, true);
         */


        for (int i = 0; i < gameMap.obstacleContainer.size(); i++) {
           // Rect fromRect = new Rect(0, 0, wallBitmap.getWidth(), wallBitmap.getHeight());
           // Rect toRect = new Rect((int)gameMap.obstacleContainer.get(i).location.left, (int)gameMap.obstacleContainer.get(i).location.top, (int)gameMap.obstacleContainer.get(i).location.right,
                  //  (int)gameMap.obstacleContainer.get(i).location.bottom);

            //canvas.drawBitmap(wallBitmap, fromRect, toRect, paint);
            canvas.drawBitmap(wallBitmap, gameMap.obstacleContainer.get(i).location.left, gameMap.obstacleContainer.get(i).location.top, null);

            System.out.println(gameMap.obstacleContainer.get(i).location.left + "," + gameMap.obstacleContainer.get(i).location.top);
        }


    }

    public void drawObstacles(Canvas c, Bitmap wallBitmap) {

    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform m) {
        RectF backgroundPortionToDraw = m.getCollider();
        int width = (int)m.getmScreenSize().x;
        int height = (int)m.getmScreenSize().y;

        // Conditions below handle drawing full background upon starting game (based off current player location and no spawn)..
        if (backgroundPortionToDraw.top == 0 && backgroundPortionToDraw.bottom == 0) {
            int top = 0;
            int left = 0;
            int right = 5120;
            int bottom = 5120;


            Rect fromRect1 = new Rect(0, 0, 5120, 5120);

            Rect toRect1 = new Rect(0, 0, width, height);

            canvas.drawBitmap(bitmapNew, fromRect1, toRect1, paint);
            //canvas.drawBitmap(mBitmap, fromRect1, toRect1, paint);



        }

        else {
                // Portion to draw..
                Rect fromRect1 = new Rect((int)backgroundPortionToDraw.left, (int)backgroundPortionToDraw.top,
                    (int)backgroundPortionToDraw.right, (int)backgroundPortionToDraw.bottom);

            Rect toRect1 = new Rect(0, 0, width, height);

            // Note: Choose your own size for the map..
            canvas.drawBitmap(bitmapNew,fromRect1, toRect1, paint);



            /*
                 for (int i = 0; i < localObstacleContainer.size(); i++) {
                localObstacleContainer.get(i).location.left += diff.x;
                localObstacleContainer.get(i).location.top += diff.y;

                canvas.drawBitmap(wallBitmap, localObstacleContainer.get(i).location.left, localObstacleContainer.get(i).location.top, paint);

            }
             */


            // localObstacleContainer.get(i).location.left += diff.x

        }


    }
}

