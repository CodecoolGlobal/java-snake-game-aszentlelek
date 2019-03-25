package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public SimpleEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        int speed = 1;
        double direction = ThreadLocalRandom.current().nextDouble(0, 360);

        if (isOutOfBounds()) {
            if (getX() > Globals.WINDOW_WIDTH - 35 ){
                heading = Utils.directionToVector(direction, speed);
                setX(getX() - 5);
            }
            else if(getX() < 0){
                heading = Utils.directionToVector(direction, speed);
                setX(getX() + 5);

            }
            else if(getY() > Globals.WINDOW_HEIGHT - 35){
                heading = Utils.directionToVector(direction, speed);
                setY(getY() - 5);
            }
            else if(getY() < 0){
                heading = Utils.directionToVector(direction, speed);
                setY(getY() + 5);
            }

        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
