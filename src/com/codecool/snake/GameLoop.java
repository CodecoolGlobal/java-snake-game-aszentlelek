package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.enemies.*;
import com.codecool.snake.entities.powerups.*;

import java.util.List;

public class GameLoop {
    private Snake snake;
    private boolean running = false;
    private long startTimeEnemy, startTimePowerUp, startTimeJesus = System.currentTimeMillis();

    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.step();
            if (((System.currentTimeMillis() - startTimeEnemy)/1000) == Globals.getInstance().getRandomSpawnTime(2,5)) {
                new Bible();
                startTimeEnemy = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimePowerUp)/1000) == Globals.getInstance().getRandomSpawnTime(1,3)) {
                new Penta();
                startTimePowerUp = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimeJesus)/1000) == Globals.getInstance().getRandomSpawnTime(12,20)) {
                new Jesus();
                startTimeJesus = System.currentTimeMillis();
            }

            Globals.getInstance().game.getHPtext().setText("Health: "+snake.getHealth());

            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
        }

        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
