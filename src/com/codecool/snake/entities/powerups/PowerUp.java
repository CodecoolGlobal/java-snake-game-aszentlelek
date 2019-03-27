package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;

public abstract class PowerUp extends GameEntity{
    private int damage;
    private float speedUp;
    Snake snake;

    PowerUp(int damage) {
        double distanceFromSnakeHead;
        do {
            generateRandomXY();
            distanceFromSnakeHead = Globals.getInstance().getSnakeHeadActualPos().distance(randomPos);
        } while (distanceFromSnakeHead > 500 || distanceFromSnakeHead < 300);

        setPosition(randomPos);

        this.damage = damage;
    }

    PowerUp(float speedUp) {
        this.speedUp = speedUp;
    }

    public float getSpeedUp() {
        return speedUp;
    }

    public int getDamage() {
        return damage;
    }
}
