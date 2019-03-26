package com.codecool.snake.entities;

import javafx.scene.text.*;

public class TextEntity extends GameEntity {
    Text t;

    public TextEntity(int x, int y) {
        t = new Text("This is a text sample");
        this.setX(x);
        this.setY(y);

    }
}
