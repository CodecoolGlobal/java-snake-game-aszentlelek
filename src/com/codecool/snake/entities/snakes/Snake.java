package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.Main;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Snake implements Animatable {
    private float speed = 2;
    private int health = 100;


    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(3);
    }

    public void setSpeed(float speedUp) {
        this.speed = this.speed + speedUp;
    }

    public int getHealth() {
        return health;
    }


    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            Globals.getInstance().display.clear();
            clickableSatan();
            clickableJesus();
            Globals.getInstance().game.setTableBackground(new Image("/purgatory.jpg"));
            System.out.println("Game Over");
            Globals.getInstance().game.getChildren().add(gameOverText("Score: " + (this.body.getList().size() * 10 + 6),70,1250,100));
            Globals.getInstance().game.getChildren().add(gameOverText("Game Over",80,+
                    Globals.WINDOW_WIDTH/2.5,Globals.WINDOW_HEIGHT/3));
            Globals.getInstance().game.getChildren().add(gameOverText("Genesis 3:14\n\n" +
                    " The LORD God said to the serpent, \"Because you have done this,\n  " +
                    "Cursed are you more than all cattle, And more than every beast of the field;\n   " +
                    "On your belly you will go, And dust you will eat All the days of your life...",40,+
                    Globals.WINDOW_WIDTH/4.5,Globals.WINDOW_HEIGHT/2.5));
            Globals.getInstance().stopGame();
        }
        if (this.body.getList().size() * 10 + 6 >= 666) {
            Globals.getInstance().display.clear();
            Globals.getInstance().game.setTableBackground(new Image("/satanwin.png"));
            System.out.println("You win!");
            Globals.getInstance().game.getChildren().add(gameOverText("Score: " + (this.body.getList().size() * 10 + 6),70,1250,100));
            Globals.getInstance().game.getChildren().add(gameOverText("You win!",200,+
                    Globals.WINDOW_WIDTH/3.7,Globals.WINDOW_HEIGHT/2));
            Globals.getInstance().stopGame();
        }
    }

    private Text gameOverText(String txt, int size, double x, double y){
        Text text = new Text();
        text.setX(x);
        text.setY(y);
        text.setText(txt);
        text.setFont(Font.font ("Alex Brush", size));
        text.setFill(Color.LEMONCHIFFON);
        text.setCache(true);

        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(60, 60, 66, 0.3));
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);

        blend.setBottomInput(ds);

        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#505050"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(9);
        is.setChoke(0.8);
        blend2.setBottomInput(is);

        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);

        text.setEffect(blend);

        return text;
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }

    private void clickableSatan() {
        ImageView satan = new ImageView("satan_over.png");
        Tooltip.install(satan, new Tooltip("Click me if you want to try to destroy the humanity again! (RESTART)"));
        satan.setPickOnBounds(true);
        satan.setX(1150);
        satan.setY(650);
        satan.setOnMouseClicked((MouseEvent e) -> Globals.getInstance().game.restart());
        Globals.getInstance().game.getChildren().add(satan);
    }

    private void clickableJesus() {
        ImageView jesus = new ImageView("jesus_over.png");
        Tooltip.install(jesus, new Tooltip("Click me if you want to forgiveness for your sins and enter " +
                "the Heaven's door! (EXIT)"));
        jesus.setPickOnBounds(true);
        jesus.setX(420);
        jesus.setY(40);
        jesus.setOnMouseClicked((MouseEvent e) -> System.exit(0));
        Globals.getInstance().game.getChildren().add(jesus);
    }
}
