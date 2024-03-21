package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

public class CollisionA extends AbsCollision {
    public CollisionA(Position pos, int deleteTime){
        super(pos);
        this.deleteTime = deleteTime;
        this.imagePath = MvcGameConfig.COLLISION_A_IMAGE_RESOURCE;
    }

    @Override
    public boolean destroy() {
        return this.getAge() >= deleteTime;
    }
}
