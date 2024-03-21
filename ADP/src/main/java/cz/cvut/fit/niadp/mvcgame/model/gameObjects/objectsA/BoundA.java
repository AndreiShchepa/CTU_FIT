package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsBound;

public class BoundA extends AbsBound {
    public BoundA(Position initialPosition) {
        this.position = initialPosition;
    }
}
