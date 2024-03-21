package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.iterator.ShootingModeCollection;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

import java.util.ArrayList;
import java.util.List;


public class CannonA extends AbsCannon {
    private final List<AbsMissile> shootingBatch;

    public CannonA (Position initialPosition, IGameObjectsFactory goFact) {
        this.goFact = goFact;
        this.position = initialPosition;
        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
        this.capacity = MvcGameConfig.INIT_CAPACITY;
        this.shootingBatch = new ArrayList<>();

        this.modesIterator = new ShootingModeCollection().createIterator();
        this.currShootingMode = (IShootingMode) this.modesIterator.getNext();
    }

    public void moveUp() { this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP)); }

    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void aimUp() {
        if (this.angle - MvcGameConfig.ANGLE_STEP < MvcGameConfig.MIN_ANGLE) {
            this.angle = MvcGameConfig.MIN_ANGLE;
        } else {
            this.angle -= MvcGameConfig.ANGLE_STEP;
        }
    }

    @Override
    public void aimDown() {
        if (this.angle + MvcGameConfig.ANGLE_STEP > MvcGameConfig.MAX_ANGLE) {
            this.angle = MvcGameConfig.MAX_ANGLE;
        } else {
            this.angle += MvcGameConfig.ANGLE_STEP;
        }
    }

    @Override
    public void powerUp() {
        if (this.power + MvcGameConfig.POWER_STEP > MvcGameConfig.MAX_POWER) {
            this.power = MvcGameConfig.MAX_POWER;
        } else {
            this.power += MvcGameConfig.POWER_STEP;
        }
    }

    @Override
    public void powerDown() {
        if (this.power - MvcGameConfig.POWER_STEP < MvcGameConfig.MIN_POWER) {
            this.power = MvcGameConfig.MIN_POWER;
        } else {
            this.power -= MvcGameConfig.POWER_STEP;
        }
    }

    @Override
    public void increaseMissiles() {
        if (this.currShootingMode instanceof DynamicShootingMode) {
            ((DynamicShootingMode) this.currShootingMode).increaseMissiles();
        }
    }

    @Override
    public void decreaseMissiles() {
        if (this.currShootingMode instanceof DynamicShootingMode) {
            ((DynamicShootingMode) this.currShootingMode).decreaseMissiles();
        }
    }

    @Override
    public int getMissilesToShoot() {
        return this.currShootingMode.getMissilesToShoot();
    }

    @Override
    public void reload() {
        this.capacity = MvcGameConfig.INIT_CAPACITY;
    }

    @Override
    public List<AbsMissile> shoot() {
        this.shootingBatch.clear();
        this.currShootingMode.shoot(this);
        return this.shootingBatch;
    }

    @Override
    public void primitiveShoot() {
        this.shootingBatch.add(
            this.goFact.createMissile(this.angle, this.power)
        );
        this.capacity--;
    }

    @Override
    public void changeFactory(IGameObjectsFactory newFactory) {
        this.goFact = newFactory;
    }

    @Override
    public void toggleShootingMode() {
        this.currShootingMode = (IShootingMode)this.modesIterator.getNext();
    }
}
