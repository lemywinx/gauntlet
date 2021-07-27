package com.example.gauntlet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Rect;

public class GameData {

    // Keeps track of the main bitmap
    public static Bitmap mainBitmap;
    public static Bitmap initialBitmap;

    public static final int ROWS = 32;
    public static final int COLS = 32;

    public static final int IMAGE_RESOLUTION_X = 5120;
    public static final int IMAGE_RESOLUTION_Y = 5120;

    public static final int LOWRES_CONV_FACTOR_X = IMAGE_RESOLUTION_X/ROWS;
    public static final int LOWRES_CONV_FACTOR_Y = IMAGE_RESOLUTION_Y/COLS;

    public static RectF visibleScreenRect = new RectF();
    public static Rect fullMapRect = new Rect();

    static void resetBitmap() {
        Canvas canvas = new Canvas();
        canvas.setBitmap(mainBitmap);

        canvas.drawBitmap(initialBitmap, 0, 0, null);

    }



}
