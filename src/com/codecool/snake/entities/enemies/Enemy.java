package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{
    private final int damage;

    Enemy(int damage) {
        double distanceFromSnakeHead;
        do {
            generateRandomXY();
            distanceFromSnakeHead = Globals.getInstance().getSnakeHeadActualPos().distance(randomPos);
        } while (distanceFromSnakeHead > 200 || distanceFromSnakeHead < 100);

        setPosition(randomPos);

        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
