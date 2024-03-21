package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbsMissile {
    public MissileA(Position initialPosition, int initVelocity, double initAngle, IMovingStrategy movingStrategy) {
        super(initialPosition, MvcGameConfig.MISSILE_A_IMAGE_RESOURCE, initAngle, initVelocity, movingStrategy);
        this.damage = MvcGameConfig.MISSILE_A_DAMAGE;
        this.radiusHit = MvcGameConfig.MISSILE_A_RADIUS_HIT;
        this.cost = MvcGameConfig.MISSILE_A_COST;
    }
}
