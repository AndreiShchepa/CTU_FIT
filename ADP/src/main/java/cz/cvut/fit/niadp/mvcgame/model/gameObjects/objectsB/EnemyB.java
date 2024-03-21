package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

public class EnemyB extends AbsEnemy {
    public EnemyB(Position pos, IGameObjectsFactory gofact) {
        this.position = pos;
        this.lives = MvcGameConfig.ENEMY_B_LIVES;
        this.imagePath = MvcGameConfig.ENEMY_B_IMAGE_RESOURCE;
        this.goFact = gofact;
        this.cost = MvcGameConfig.ENEMY_B_COST;
    }

    public AbsCollision createCollision() {
        return goFact.createCollision(this.position.myclone());
    }
}
