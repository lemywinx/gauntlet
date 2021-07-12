package com.example.gauntlet;

import android.graphics.Rect;
import android.util.Pair;
import android.view.MotionEvent;
import java.util.ArrayList;

interface InputObserver {

    void handleInput(MotionEvent event, GameState gs,
                     Pair<ArrayList<Rect>, Joystick> controls);
}
