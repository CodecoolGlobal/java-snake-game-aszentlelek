package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.Jesus;
import com.codecool.snake.entities.powerups.Satan;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;
import com.codecool.snake.entities.enemies.Cross;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.layout.Pane;



public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private Text HPtext = new Text();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public Text getHPtext() {
        return HPtext;
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);
        spawnHP();


        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(Globals.WINDOW_WIDTH/2, Globals.WINDOW_HEIGHT/2));
    }

    private void spawnHP(){

        HPtext.setText("Health: "+ snake.getHealth());
        HPtext.setX(100);
        HPtext.setY(100);
        getChildren().add(HPtext);
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new Jesus();
        for(int i = 0; i < numberOfEnemies; ++i) new Cross();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
        for(int i = 0; i < numberOfPowerUps; ++i) new Satan();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
