package com.codecool.snake;

import com.codecool.snake.entities.enemies.Bible;
import com.codecool.snake.entities.powerups.Blood;
import com.codecool.snake.entities.powerups.Penta;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;
import com.codecool.snake.entities.enemies.Cross;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;


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
        spawnEnemies(2);
        spawnPowerUps(2);
        makeRestartBtn();
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

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        HPtext.setEffect(is);
        HPtext.setX(10);
        HPtext.setY(20);
        HPtext.setText("Health: " + snake.getHealth());
        HPtext.setFill(Color.YELLOW);
        HPtext.setFont(Font.font(null, FontWeight.BOLD, 30));

        HPtext.setTranslateX(20);
        HPtext.setTranslateY(10);

        getChildren().add(HPtext);
    }

    private void makeRestartBtn(){
        Button button = new Button("Restart");
        button.setLayoutX(20);
        button.setLayoutY(60);
        button.setOnMouseClicked(event -> restart());
        this.getChildren().add(button);
    }


    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new Bible();
        for(int i = 0; i < numberOfEnemies; ++i) new Cross();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new Penta();
        for(int i = 0; i < numberOfPowerUps; ++i) new Blood();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void restart(){
        Game game = new Game();

        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        mainScene.getStylesheets().add("http://fonts.googleapis.com/css?family=Alex+Brush");

        Main.stage.setTitle("Snake Game");
        Main.stage.setScene(mainScene);
        Main.stage.show();
        game.setTableBackground(new Image("/eden.jpg"));
        game.start();
    }
}
