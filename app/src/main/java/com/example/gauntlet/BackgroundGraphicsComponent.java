package com.example.gauntlet;

import android.content.Context;
import android.content.Context;
import android.graphics.*;

// Overall, same idea as BackgroundGraphics component within ScrollingShooter.

public class BackgroundGraphicsComponent implements GraphicsComponent{
    private Bitmap mBitmap;

    // Draw on top of original bitmap..
    // Use low res map and draw original wall/stationary structure upon initialization.
    // Tracking relative position of player..
    @Override
    public void initialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().getIdentifier(s.getBitmapName(), "drawable", c.getPackageName());
        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)objectSize.x, (int)objectSize.y, false);

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
            int right = width;
            int bottom = height;

            Rect fromRect1 = new Rect(left, top, right, bottom);

            Rect toRect1 = new Rect(0, 0, width, height);
            canvas.drawBitmap(mBitmap, fromRect1, toRect1, paint);

        }

        else {
            Rect fromRect1 = new Rect((int)backgroundPortionToDraw.left, (int)backgroundPortionToDraw.top,
                    (int)backgroundPortionToDraw.right, (int)backgroundPortionToDraw.bottom);

            Rect toRect1 = new Rect(0, 0, width, height);
            canvas.drawBitmap(mBitmap, fromRect1, toRect1, paint);
        }


    }
}

