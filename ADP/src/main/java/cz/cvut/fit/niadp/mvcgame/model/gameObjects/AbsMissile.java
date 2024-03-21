package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends LifeTimeGameObject {
    private final String imagePath;
    private final double initAngle;
    private final int initVelocity;
    private final IMovingStrategy movingStrategy;
    protected int damage;
    protected int cost;
    protected int radiusHit;

    protected AbsMissile(Position position, String imagePath, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(position);
        this.initVelocity = initVelocity;
        this.initAngle = initAngle;
        this.imagePath = imagePath;
        this.movingStrategy = movingStrategy;
    }

    public void move() {
        this.movingStrategy.updatePosition(this);
    }

    public int getCost() { return this.cost; }

    public int getRadiusHit() { return this.radiusHit; }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    public double getInitAngle() {
        return this.initAngle;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getDamage() { return this.damage; }

    public boolean hit(AbsEnemy enemy) {
        int enemyX = enemy.getPosition().getX() + (int)(MvcGameConfig.OBJECT_SIZE.getWidth()/2);
        int enemyY = enemy.getPosition().getY() + (int)(MvcGameConfig.OBJECT_SIZE.getHeight()/2);
        int missileX = this.getPosition().getX() + (int)(MvcGameConfig.OBJECT_SIZE.getWidth()/2);
        int missileY = this.getPosition().getY() + (int)(MvcGameConfig.OBJECT_SIZE.getHeight()/2);

        if (missileX < enemyX + MvcGameConfig.HIT_X_INTERVAL &&
            missileX > enemyX - MvcGameConfig.HIT_X_INTERVAL)
        {
            return missileY > enemyY - this.radiusHit && missileY < enemyY + this.radiusHit;
        }

        return false;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) { visitor.visitMissile(this); }
}
