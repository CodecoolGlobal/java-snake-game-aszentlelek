package com.codecool.snake.entities;

import com.codecool.snake.Globals;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.image.ImageView;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Vec2d randomPos;

    protected GameEntity() { Globals.getInstance().display.add(this); }

    protected void destroy() {
        Globals.getInstance().display.remove(this);
    }

    public Vec2d getPosition() {
        return new Vec2d(getX(), getY());
    }

    public void setPosition(Vec2d pos) {
        setX(pos.x);
        setY(pos.y);
    }

    public boolean isOutOfBounds() {
        return getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
                getY() > Globals.WINDOW_HEIGHT || getY() < 0;
    }

    protected void generateRandomXY() {
        double randomX = Globals.rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double randomY = Globals.rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        randomPos = new Vec2d(randomX, randomY);
    }
}
