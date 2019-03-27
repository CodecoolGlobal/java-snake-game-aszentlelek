package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;



public class Jesus extends Enemy implements Animatable, Interactable {

    private Point2D heading;

    public Jesus() {
        super(-100);
        setImage(Globals.getInstance().getImage("Jesus"));

        double direction = Globals.rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        double speed = 1.5;
        double direction;
        double directionToSnakeHead = Utils.angleOfLine(getX(), getY(), Globals.getInstance().getSnakeHeadActualPos().x,
                Globals.getInstance().getSnakeHeadActualPos().y);
        setRotate(directionToSnakeHead);
        direction = directionToSnakeHead;
        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println("AMEN!");
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}