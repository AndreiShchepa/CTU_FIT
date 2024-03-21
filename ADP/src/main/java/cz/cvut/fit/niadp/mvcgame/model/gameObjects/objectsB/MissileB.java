package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileB extends AbsMissile {
    public MissileB(Position initialPosition, int initVelocity, double initAngle, IMovingStrategy movingStrategy) {
        super(initialPosition, MvcGameConfig.MISSILE_B_IMAGE_RESOURCE, initAngle, initVelocity, movingStrategy);
        this.damage = MvcGameConfig.MISSILE_B_DAMAGE;
        this.radiusHit = MvcGameConfig.MISSILE_B_RADIUS_HIT;
        this.cost = MvcGameConfig.MISSILE_B_COST;
    }
}
