package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class LevelDirector implements IDirector {
    private final IBuilder builder;
    private final IGameModel model;

    public LevelDirector(IBuilder builder, IGameModel model) {
        this.builder = builder;
        this.model = model;
    }

    @Override
    public void make() {
        builder.buildCannon();
        builder.buildBounds();
        builder.buildEnemies(
            GameObjectsFactoryA.getInstance(this.model),
            GameObjectsFactoryB.getInstance(this.model)
        );
        builder.buildGameInfo();
        builder.buildMissiles();
        builder.buildCollisions();
        builder.buildEndScene();
    }
}
