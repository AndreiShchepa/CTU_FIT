package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.Random;

public class RandomMovingStrategy implements IMovingStrategy {
    @Override
    public void updatePosition(AbsMissile missile) {
        double initAngle = missile.getInitAngle();
        int initVelocity = missile.getInitVelocity();
        double time = (double) missile.getAge()/300;
        Random random = new Random();

        double randomFactor = 10;

        double randomAdjustmentX = (random.nextDouble() * 2 - 1) * randomFactor;
        double randomAdjustmentY = (random.nextDouble() * 2 - 1) * randomFactor;

        double velocityX = initVelocity * Math.cos(initAngle) + randomAdjustmentX;
        double velocityY = initVelocity * Math.sin(initAngle) + randomAdjustmentY;

        double dX = velocityX * time;
        double dY = velocityY * time;

        missile.move(new Vector((int) Math.round(dX), (int) Math.round(dY)));
    }

    @Override
    public String getName() {
        return RandomMovingStrategy.class.getSimpleName().replace("MovingStrategy", "");
    }
}
