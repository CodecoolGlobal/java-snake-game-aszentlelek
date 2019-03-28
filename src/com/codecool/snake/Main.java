package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setResizable(false);
        Game game = new Game();

        if (Globals.menuIsActive){
            Scene menuScene = new Scene(game, 600, 800);
            menuScene.getStylesheets().add("http://fonts.googleapis.com/css?family=Alex+Brush");
            primaryStage.setTitle("Snake Game - Main menu");
            primaryStage.setScene(menuScene);
            primaryStage.show();
            game.setTableBackground(new Image("/menutree.jpg"));
            game.makeRestartBtn(40, 125, "Start");

            game.makeExitBtn(360, 125, "Exit");
        } else {
            Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
            mainScene.getStylesheets().add("http://fonts.googleapis.com/css?family=Alex+Brush");
            primaryStage.setTitle("Snake Game");
            primaryStage.setScene(mainScene);
            primaryStage.show();
            game.setTableBackground(new Image("/eden.jpg"));
            game.init();
            game.start();
        }
    }

    @Override
    public void stop() {
        System.out.println("Exiting..");
    }
}
