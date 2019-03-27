package com.codecool.snake.entities.enemies;

import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;



public class Jesus extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private long jesusBirthTime = System.currentTimeMillis();
    private int jesusLifeTime = Globals.getInstance().getRandomSpawnTime(3,5);

    public Jesus() {
        super(-150);
        setImage(Globals.getInstance().getImage("Jesus"));

        double direction = Globals.rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);

        if (((System.currentTimeMillis() - jesusBirthTime)/1000) == jesusLifeTime) {
            destroy();
        }
    }

    @Override
    public void step() {
        double speed = 2.5;
        double direction;
        double directionToSnakeHead = Utils.angleOfLine(getX(), getY(), Globals.getInstance().getSnakeHeadActualPos().x,
                Globals.getInstance().getSnakeHeadActualPos().y);
        setRotate(directionToSnakeHead);
        direction = directionToSnakeHead;
        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        if (((System.currentTimeMillis() - jesusBirthTime)/1000) == jesusLifeTime) {
            destroy();
        }
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