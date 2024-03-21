package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA.EndSceneA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB.MissileB;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB.EnemyB;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB.CollisionB;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;


public class GameObjectsFactoryB implements IGameObjectsFactory {
    private final IGameModel model;
    private static GameObjectsFactoryB instance;

    private GameObjectsFactoryB(IGameModel model) {
        this.model = model;
    }

    public static GameObjectsFactoryB getInstance(IGameModel model) {
        if (instance == null) {
            instance = new GameObjectsFactoryB(model);
        }

        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    @Override
    public AbsCannon createCannon() {
        return null;
    }

    @Override
    public MissileB createMissile(double initAngle, int initVelocity) {
        return new MissileB(this.model.getCannonPosition().myclone(), initVelocity, initAngle, this.model.getMovingStrategy());
    }

    @Override
    public AbsBound createBound(String type) {
        return null;
    }
    @Override
    public AbsGameInfo createGameInfo(AbsCannon cannon) { return null; }

    @Override
    public EnemyB createEnemy(Position pos) {
        return new EnemyB(pos, this);
    }

    @Override
    public CollisionB createCollision(Position pos) {
        return new CollisionB(pos, 2000);
    }

    @Override
    public AbsEndScene createEndScene(int expectedScore) { return null; }
}
