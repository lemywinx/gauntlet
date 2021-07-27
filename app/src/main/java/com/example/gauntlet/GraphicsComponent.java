package com.example.gauntlet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

interface GraphicsComponent {

    void initialize(Context c,
                    ObjectSpec s,
                    PointF screenSize);

    void setBitmap(Bitmap bitmap);
    void draw(Canvas canvas,
              Paint paint,
              Transform t);
}
          //test for push