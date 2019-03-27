package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public abstract class PowerUp extends GameEntity{
    private int damage;

    PowerUp(int damage) {
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
