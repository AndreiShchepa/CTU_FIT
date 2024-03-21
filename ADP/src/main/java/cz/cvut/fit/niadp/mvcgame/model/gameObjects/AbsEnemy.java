package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {
    protected int lives;
    protected String imagePath;
    protected IGameObjectsFactory goFact;
    protected int cost;


    public int getLives() { return this.lives; }

    public int getCost() { return this.cost; }

    public void damage(int value) { this.lives -= value; }

    public String getImagePath() { return this.imagePath; }

    public abstract AbsCollision createCollision();

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
