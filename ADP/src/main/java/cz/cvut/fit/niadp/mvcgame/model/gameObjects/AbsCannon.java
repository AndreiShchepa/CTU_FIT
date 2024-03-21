package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.iterator.ICyclicIterator;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject {
    protected IGameObjectsFactory goFact;
    protected ICyclicIterator modesIterator;
    protected IShootingMode currShootingMode;
    protected int power;
    protected double angle;
    protected int capacity;

    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void aimUp();
    public abstract void aimDown();
    public abstract void powerUp();
    public abstract void powerDown();
    public abstract void reload();
    public abstract void increaseMissiles();
    public abstract void decreaseMissiles();
    public abstract int getMissilesToShoot();
    public abstract void primitiveShoot();
    public abstract List<AbsMissile> shoot();

    public abstract void changeFactory(IGameObjectsFactory newFactory);

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }

    public abstract void toggleShootingMode();

    public double getAngle() {
        return this.angle;
    }

    public int getPower() { return this.power; }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() { return this.capacity; }

    public IShootingMode getShootingMode() { return this.currShootingMode; }
}
