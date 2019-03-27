package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{
    private int damage;
    private float speedUp;

    public Enemy(float speedUp) {
        this.speedUp = speedUp;
    }

    public float getSpeedUp() {
        return speedUp;
    }


    Enemy(int damage) {
        double distanceFromSnakeHead;
        do {
            generateRandomXY();
            distanceFromSnakeHead = Globals.getInstance().getSnakeHeadActualPos().distance(randomPos);
        } while (distanceFromSnakeHead > 500 || distanceFromSnakeHead < 300);

        setPosition(randomPos);

        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
