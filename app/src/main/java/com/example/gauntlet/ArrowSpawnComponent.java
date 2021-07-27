package com.example.gauntlet;

import android.graphics.PointF;

class ArrowSpawnComponent implements SpawnComponent {

    @Override
    public void spawn(Transform playerTransform,
                      Transform t) {

        PointF startPosition =
                playerTransform.getFiringLocation(t.getSize().x);

        t.setLocation(playerTransform.getLocation().x, playerTransform.getLocation().y);
        t.setDrawableLocation();

        if(playerTransform.getFacingRight()){
            t.headRight();
        }
        else{
            t.headLeft();
        }

       // ArrowGraphicsComponent.lastToDraw = //true;


    }
}

