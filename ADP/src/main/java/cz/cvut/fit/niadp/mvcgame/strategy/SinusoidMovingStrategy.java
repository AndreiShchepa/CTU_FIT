package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class SinusoidMovingStrategy implements IMovingStrategy {
    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        double time = (double)missile.getAge()/300;

        double dX = initVelocity * time/1.5 * Math.cos(initAngle);
        double dY = initVelocity * Math.sin(initAngle + Math.sin(time * initVelocity*2));

        missile.move(new Vector((int)Math.round(dX), (int)Math.round(dY)));
    }

    @Override
    public String getName() {
        return SinusoidMovingStrategy.class.getSimpleName().replace("MovingStrategy", "");
    }
}
