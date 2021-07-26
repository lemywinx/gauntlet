package com.example.gauntlet;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class GameData {

    // Keeps track of the main bitmap
    public static Bitmap mainBitmap;

    public static final int ROWS = 32;
    public static final int COLS = 32;

    public static final int IMAGE_RESOLUTION_X = 5120;
    public static final int IMAGE_RESOLUTION_Y = 5120;

    public static final int LOWRES_CONV_FACTOR_X = IMAGE_RESOLUTION_X/ROWS;
    public static final int LOWRES_CONV_FACTOR_Y = IMAGE_RESOLUTION_Y/COLS;

    public static RectF visibleScreenRect = new RectF();

}
