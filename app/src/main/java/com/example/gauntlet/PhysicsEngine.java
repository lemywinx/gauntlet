package com.example.gauntlet;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

class PhysicsEngine {

    // This signature and much more will
    //change later in the project
    boolean update(long fps, ArrayList<GameObject> objects,
                   GameState gs, SoundEngine se){

        // Update all the GameObjects
        for (GameObject object : objects) {
            if (object.checkActive()) {
                object.update(fps, objects.get(Level.PLAYER_INDEX)
                        .getTransform());
            }
        }

        return detectCollisions(gs, objects, se);
    }


    // Collision detection method will go here
    private boolean detectCollisions(
            GameState mGameState,
            ArrayList<GameObject> objects,
            SoundEngine se){

        boolean playerHit = false;
        for(GameObject go1 : objects) {

            if(go1.checkActive()){
                // The ist object is active
                // so worth checking

                for(GameObject go2 : objects) {

                    if(go2.checkActive()){

                        // The 2nd object is active
                        // so worth checking
                        if(RectF.intersects(
                                go1.getTransform().getCollider(),
                                go2.getTransform().getCollider())){

                            // switch goes here
                            // There has been a collision
                            // - but does it matter
                            switch (go1.getTag() + " with " + go2.getTag()){
                                case "Player with Alien Arrow":

                                case "Player with Alien":
                                    playerHit = true;
                                    mGameState.loseLife(se);
                                    break;

                                case "Player with Troll":
                                    playerHit = true;
                                    mGameState.loseLife(se);
                                    break;

                                case "Player Arrow with Alien":
                                    mGameState.increaseScore();
                                    // Respawn the alien
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level
                                            .PLAYER_INDEX).getTransform());

                                    go1.setInactive();
                                    se.playAlienExplode();

                                    break;
                                case "playerArrow with Troll":
                                    mGameState.increaseScore();
                                    //Respawn the Troll
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level
                                            .PLAYER_INDEX).getTransform());
                                    go1.setInactive();
                                    se.playAlienExplode();
                                    break;

                                default:
                                    break;
                            }


                        }
                    }
                }
            }
        }
        return playerHit;
    }


}

