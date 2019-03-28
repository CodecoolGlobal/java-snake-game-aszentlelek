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
        Game game = new Game();

        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        mainScene.getStylesheets().add("http://fonts.googleapis.com/css?family=Alex+Brush");
        stage.setResizable(false);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        game.setTableBackground(new Image("/eden.jpg"));
        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }

}
