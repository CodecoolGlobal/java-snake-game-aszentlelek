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
    private long startTimeBible = System.currentTimeMillis();
    private long startTimeCross = System.currentTimeMillis();
    private long startTimeJesus = System.currentTimeMillis();
    private long startTimeBlood = System.currentTimeMillis();
    private long startTimePenta = System.currentTimeMillis();
    private long startTimeSatan = System.currentTimeMillis();
    GameLoop(Snake snake) { this.snake = snake; }

    void start() {
        running = true;
    }

    void stop() {
        running = false;
    }

    void step() {
        if(running) {
            snake.step();
            if (((System.currentTimeMillis() - startTimeBible)/1000) == Globals.getInstance().getRandomSpawnTime(10,15)) {
                new Bible();
                startTimeBible = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimeCross)/1000) == Globals.getInstance().getRandomSpawnTime(5,10)) {
                new Cross();
                startTimeCross = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimeJesus)/1000) == Globals.getInstance().getRandomSpawnTime(13,20)) {
                new Jesus();
                startTimeJesus = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimeBlood)/1000) == Globals.getInstance().getRandomSpawnTime(7,10)) {
                new Blood();
                startTimeBlood = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimePenta)/1000) == Globals.getInstance().getRandomSpawnTime(3,5)) {
                new Penta();
                startTimePenta = System.currentTimeMillis();
            }
            if (((System.currentTimeMillis() - startTimeSatan)/1000) == Globals.getInstance().getRandomSpawnTime(13,20)) {
                new Satan();
                startTimeSatan = System.currentTimeMillis();
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
