package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

public class EnemyA extends AbsEnemy {
    public EnemyA(Position pos, IGameObjectsFactory goFact) {
        this.position = pos;
        this.lives = MvcGameConfig.ENEMY_A_LIVES;
        this.imagePath = MvcGameConfig.ENEMY_A_IMAGE_RESOURCE;
        this.goFact = goFact;
        this.cost = MvcGameConfig.ENEMY_A_COST;
    }

    public AbsCollision createCollision() {
        return goFact.createCollision(this.position.myclone());
    }
}
