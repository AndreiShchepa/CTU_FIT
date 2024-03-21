package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

import java.util.Objects;

public class GameObjectsFactoryA implements IGameObjectsFactory {
    private final IGameModel model;
    private static GameObjectsFactoryA instance;

    private GameObjectsFactoryA(IGameModel model) {
        this.model = model;
    }

    public static GameObjectsFactoryA getInstance(IGameModel model) {
        if (instance == null) {
            instance = new GameObjectsFactoryA(model);
        }

        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public MissileA createMissile(double initAngle, int initVelocity) {
        return new MissileA(this.model.getCannonPosition().myclone(), initVelocity, initAngle, this.model.getMovingStrategy());
    }

    @Override
    public BoundA createBound(String type) {
        if (Objects.equals(type, "UPPER")) {
            return new BoundA(new Position(MvcGameConfig.BOUND_POS_X, MvcGameConfig.BOUND_UP_POS_Y));
        }

        return new BoundA(new Position(MvcGameConfig.BOUND_POS_X, MvcGameConfig.BOUND_DOWN_POS_Y));
    }

    @Override
    public GameInfoA createGameInfo(AbsCannon cannon) {
        return new GameInfoA(new Position(MvcGameConfig.GAME_INFO_POS_X, MvcGameConfig.GAME_INFO_POS_Y), cannon, this.model);
    }

    @Override
    public EnemyA createEnemy(Position pos) {
        return new EnemyA(pos, this);
    }

    @Override
    public CollisionA createCollision(Position pos) {
        return new CollisionA(pos, 1500);
    }

    @Override
    public EndSceneA createEndScene(int expectedScore) { return new EndSceneA(expectedScore); }
}
