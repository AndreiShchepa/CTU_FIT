package cz.cvut.fit.niadp.mvcgame.tests;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA.MissileA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsB.MissileB;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ChangeMissileMockTest extends AbsTestBase {
    private static final int CANNON_POSITION_X = 555;
    private static final int CANNON_POSITION_Y = 666;
    private static final int INIT_POWER = 1;
    private static final double INIT_ANGLE = 0;
    private static final IMovingStrategy INIT_STRATEGY = new SimpleMovingStrategy();

    @Test
    public void testMissileTypeChangesWithFactory() {
        GameModel model = mock(GameModel.class);
        IGameObjectsFactory factoryA = GameObjectsFactoryA.getInstance(model);
        IGameObjectsFactory factoryB = GameObjectsFactoryB.getInstance(model);
        Position cannonPosition = new Position(CANNON_POSITION_X, CANNON_POSITION_Y);

        when(model.getCannonPosition()).thenReturn(cannonPosition);

        AbsCannon cannon = mock(AbsCannon.class);
        when(model.getGameObjects()).thenReturn(Collections.singletonList(cannon));

        AbsMissile missileA = new MissileA(cannonPosition, INIT_POWER, INIT_ANGLE, INIT_STRATEGY);
        when(cannon.shoot()).thenReturn(Collections.singletonList(missileA));
        cannon.changeFactory(factoryA);
        List<AbsMissile> missiles = cannon.shoot();
        Assert.assertTrue("First missile should be an instance of MissileA",
                missiles.get(0) instanceof MissileA);

        AbsMissile missileB = new MissileB(cannonPosition, INIT_POWER, INIT_ANGLE, INIT_STRATEGY);
        when(cannon.shoot()).thenReturn(Collections.singletonList(missileB));
        cannon.changeFactory(factoryB);
        missiles = cannon.shoot();
        Assert.assertTrue("After changing, the missile should be an instance of MissileB",
                missiles.get(0) instanceof MissileB);
    }
}
