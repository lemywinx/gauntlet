package com.example.gauntlet;

class SimpleMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps,
                        Transform t,
                        Transform playerTransform) {

        return true;
    }
}
