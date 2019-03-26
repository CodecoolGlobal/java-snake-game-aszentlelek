package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.image.Image;
import java.util.Random;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1600;
    public static final double WINDOW_HEIGHT = 900;

    public static Random rnd = new Random();
    private Vec2d snakeHeadActualPos = new Vec2d(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setSnakeHeadActualPos(Vec2d vec2d) {
        snakeHeadActualPos = vec2d;
    }

    public Vec2d getSnakeHeadActualPos() {
        return snakeHeadActualPos;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("Jesus", new Image("jesus.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
