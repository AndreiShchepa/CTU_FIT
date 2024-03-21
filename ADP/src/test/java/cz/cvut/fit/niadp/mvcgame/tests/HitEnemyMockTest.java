package cz.cvut.fit.niadp.mvcgame.tests;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class HitEnemyMockTest extends AbsTestBase {
    private static final int ENEMY_POSITION_X = 456;
    private static final int ENEMY_POSITION_Y = 456;

    @Test
    public void testMissileHitsEnemy() {
        GameModel model = mock(GameModel.class);
        AbsEnemy enemy = mock(AbsEnemy.class);

        Position enemyPosition = new Position(ENEMY_POSITION_X, ENEMY_POSITION_Y);
        IGameObjectsFactory factory = GameObjectsFactoryA.getInstance(model);

        when(model.getCannonPosition()).thenReturn(enemyPosition);
        when(enemy.getPosition()).thenReturn(enemyPosition);

        double initAngle = MvcGameConfig.INIT_ANGLE;
        int power = MvcGameConfig.INIT_POWER;
        AbsMissile missile = factory.createMissile(initAngle, power);

        Assert.assertTrue("Missile should hit the enemy", missile.hit(enemy));
    }
}
