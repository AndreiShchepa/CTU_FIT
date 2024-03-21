package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

public class RealisticMovingStrategy implements IMovingStrategy {
    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        double time = (double) missile.getAge()/300;

        double dX = initVelocity * time * Math.cos(initAngle);
        double dY = initVelocity * time * Math.sin(initAngle) + (0.5*MvcGameConfig.GRAVITY * time * time);

        missile.move(new Vector((int)Math.round(dX), (int)Math.round(dY)));
    }

    @Override
    public String getName() {
        return RealisticMovingStrategy.class.getSimpleName().replace("MovingStrategy", "");
    }
}
