package com.example.gauntlet;

interface MovementComponent {

    boolean move(long fps,
                 Transform t,
                 Transform playerTransform);
}
