package com.example.gauntlet;

import android.graphics.PointF;
import android.graphics.RectF;

class Transform {

    // These two members are for scrolling background
    private int mXClip;
    private boolean mReversedFirst = false;

    private RectF mCollider;
    private PointF mLocation;
    private boolean mFacingRight = true;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    private PointF mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private static PointF mScreenSize;
    private boolean availableToMove = false;
    private double mAngle;
    private PointF mVectorComponents;

    Transform(float speed, float objectWidth,
              float objectHeight,
              PointF startingLocation,
              PointF screenSize){

        mCollider = new RectF();
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
        mSpeed = new PointF(mScreenSize.x / speed, mScreenSize.y / speed);
        System.out.println(mScreenSize.x + " " + mScreenSize.y);
        System.out.println(mSpeed.x + " " +  mSpeed.y);
        mVectorComponents = new PointF(0, 0);
    }

    void setMovementAvailability() {
        availableToMove = true;
    }

    void resetMovementAvailability() {
        availableToMove = false;
    }

    void setAngle(double angle) {
        mAngle = angle;
    }

    // Here are some helper methods that the background will use
    boolean getReversedFirst(){
        return mReversedFirst;
    }

    void flipReversedFirst(){
        mReversedFirst = !mReversedFirst;
    }

    int getXClip(){
        return mXClip;
    }

    void setXClip(int newXClip){
        mXClip = newXClip;
    }

    PointF getmScreenSize(){
        return mScreenSize;
    }


    void headUp(){
        mHeadingUp = true;
        mHeadingDown = false;

    }

    void headDown(){
        mHeadingDown = true;
        mHeadingUp = false;
    }

    void headRight(){
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    void headLeft(){
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    boolean headingUp(){
        return mHeadingUp;
    }

    boolean headingDown(){
        return mHeadingDown;
    }

    boolean headingRight(){
        return mHeadingRight;
    }

    boolean headingLeft(){
        return mHeadingLeft;
    }

    boolean isAvailableToMove() {
        return availableToMove;
    }
    void updateCollider(){
        // Pull the borders in a bit (10%)
        mCollider.top = mLocation.y + (mObjectHeight / 10);
        mCollider.left = mLocation.x + (mObjectWidth /10);
        mCollider.bottom = (mCollider.top + mObjectHeight)
                - mObjectHeight/10;

        mCollider.right = (mCollider.left + mObjectWidth)
                -  mObjectWidth/10;
    }

    float getObjectHeight(){
        return mObjectHeight;
    }

    void stopVertical(){
        mHeadingDown = false;
        mHeadingUp = false;
    }

    PointF getSpeed(){
        return mSpeed;
    }

    void setLocation(float horizontal, float vertical){
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }


    PointF getLocation() {

        return mLocation;
    }

    PointF getSize(){
        return new PointF((int)mObjectWidth,
                (int)mObjectHeight);
    }

    void flip(){
        mFacingRight = !mFacingRight;
    }

    boolean getFacingRight(){
        return mFacingRight;
    }

    RectF getCollider(){
        return mCollider;
    }

    PointF getFiringLocation(float arrowLength){
        PointF mFiringLocation = new PointF();

        if(mFacingRight) {
            mFiringLocation.x = mLocation.x
                    + (mObjectWidth / 8f);
        }else
        {
            mFiringLocation.x = mLocation.x
                    + (mObjectWidth / 8f) - (arrowLength);
        }
        // Move the height down a bit of ship height from origin
        mFiringLocation.y = mLocation.y + (mObjectHeight / 1.28f);
        return mFiringLocation;
    }

    void setMovementComponents() {
        mVectorComponents.x = (float)Math.cos(mAngle) * mSpeed.x;
        mVectorComponents.y = (float)Math.sin(mAngle) * mSpeed.y;

    }

    PointF getMovementComponents() {
        return mVectorComponents;
    }

    void resetMovement() {
        mFacingRight = false;
    }



}

