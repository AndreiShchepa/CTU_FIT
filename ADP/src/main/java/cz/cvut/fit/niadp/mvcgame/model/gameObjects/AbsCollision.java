package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsCollision extends LifeTimeGameObject {
    protected int deleteTime;
    protected String imagePath;

    protected AbsCollision(Position position) {
        super(position);
    }

    public String getImagePath() { return this.imagePath; }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }

    public abstract boolean destroy();
}
