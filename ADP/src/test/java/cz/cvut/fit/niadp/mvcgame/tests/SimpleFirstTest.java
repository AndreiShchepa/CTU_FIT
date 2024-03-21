package cz.cvut.fit.niadp.mvcgame.tests;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import org.junit.Assert;
import org.junit.Test;

public class SimpleFirstTest extends AbsTestBase {
    @Test
    public void testSingletonPropertyOfFactories() {
        GameModel model = new GameModel(1);

        IGameObjectsFactory factoryA1 = GameObjectsFactoryA.getInstance(model);
        IGameObjectsFactory factoryA2 = GameObjectsFactoryA.getInstance(model);
        IGameObjectsFactory factoryB1 = GameObjectsFactoryB.getInstance(model);
        IGameObjectsFactory factoryB2 = GameObjectsFactoryB.getInstance(model);

        Assert.assertSame("Factories of type A should be the same instance", factoryA1, factoryA2);
        Assert.assertSame("Factories of type B should be the same instance", factoryB1, factoryB2);
    }

    @Test
    public void testCannonPowerUpIncreasesPowerByConfiguredStep() {
        GameModel model = new GameModel(1);
        IGameObjectsFactory factory = GameObjectsFactoryA.getInstance(model);

        AbsCannon cannon = factory.createCannon();
        int initialPower = cannon.getPower();
        cannon.powerUp();
        int powerAfterIncrease = cannon.getPower();

        Assert.assertEquals("Cannon power should increase by POWER_STEP after powerUp",
                initialPower + MvcGameConfig.POWER_STEP,
                powerAfterIncrease);
    }
}
