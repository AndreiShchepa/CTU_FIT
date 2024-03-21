package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import cz.cvut.fit.niadp.mvcgame.model.LevelModel;


public interface IBuilder {
    void buildCannon();
    void buildBounds();
    void buildEnemies(GameObjectsFactoryA factoryA, GameObjectsFactoryB factoryB);
    void buildGameInfo();
    void buildEndScene();
    void buildMissiles();
    void buildCollisions();
    LevelModel getResult();
}
