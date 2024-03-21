package cz.cvut.fit.niadp.mvcgame.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class MissilePositionMockTest extends AbsTestBase {
    private static final int CANNON_POSITION_X = 555;
    private static final int CANNON_POSITION_Y = 666;

    @Test
    public void testMissileCreationUsesCannonPosition() {
        IGameModel model = mock(IGameModel.class);
        Position mockedPosition = new Position(CANNON_POSITION_X, CANNON_POSITION_Y);
        when(model.getCannonPosition()).thenReturn(mockedPosition);
        when(model.getMovingStrategy()).thenReturn(new SimpleMovingStrategy());

        IGameObjectsFactory factory = GameObjectsFactoryA.getInstance(model);
        AbsMissile missile = factory.createMissile(0, 0);

        Assert.assertEquals("Missile X position should match the cannon's X position",
                CANNON_POSITION_X,
                missile.getPosition().getX());
        Assert.assertEquals("Missile Y position should match the cannon's Y position",
                CANNON_POSITION_Y,
                missile.getPosition().getY());
    }
}