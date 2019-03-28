package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.PowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import jdk.nashorn.internal.objects.Global;

import java.util.Random;


public class Blood extends PowerUp implements Interactable {
    private static Random rnd = new Random();



    public Blood() {
        super(25);
        SoundPath = "blood_sound.wav";

        setImage(Globals.getInstance().getImage("Blood"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
