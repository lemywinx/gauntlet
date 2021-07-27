package com.example.gauntlet;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class Obstacle {
    RectF location;
    public float width;
    public float height;

    Obstacle(RectF location) {
        this.location = location;
        width = this.location.width();
        height = this.location.height();
    }

    RectF getLocation() {
        return location;
    }


}
