package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

public class CollisionB extends AbsCollision {
    public CollisionB(Position pos, int deleteTime){
        super(pos);
        this.deleteTime = deleteTime;
        this.imagePath = MvcGameConfig.COLLISION_B_IMAGE_RESOURCE;
    }

    @Override
    public boolean destroy() {
        return this.getAge() >= deleteTime;
    }
}
