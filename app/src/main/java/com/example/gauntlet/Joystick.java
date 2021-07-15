package com.example.gauntlet;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;



public class Joystick
{
    private PointF mCenter;
    private float baseRadius;
    private float hatRadius;
    private PointF hCenter;
    private final int ratio = 5;

    public Joystick(float x, float y, float r)
    {
        mCenter = new PointF(x, y);
        baseRadius = r;
        hatRadius = r / 3;
        hCenter = new PointF(x, y);

    }

    public void drawJoystick(Canvas c, Paint p)
    {
            p.setARGB(255, 50, 50, 50);
            c.drawCircle(mCenter.x, mCenter.y, baseRadius, p);
            p.setARGB(255, 0, 0, 255);
            c.drawCircle(hCenter.x, hCenter.y, hatRadius, p);
    }

    boolean isJoystickTouched(int x, int y) {
        double delta_x = mCenter.x - x;
        double delta_y = mCenter.y - y;
        double totalDisplacement = Math.sqrt(Math.pow(delta_x, 2) + Math.pow(delta_y, 2));

        if (totalDisplacement > baseRadius) {
            return false;
        }

        else {
            hCenter.x = x;
            hCenter.y = y;
            return true;
        }
    }

    double calcAngle(int x, int y) {
        double angleInRad = Math.atan2(y - mCenter.y, x - mCenter.x);

        return angleInRad;
    }

}