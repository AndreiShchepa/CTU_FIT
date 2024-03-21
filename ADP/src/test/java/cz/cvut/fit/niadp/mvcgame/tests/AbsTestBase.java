package cz.cvut.fit.niadp.mvcgame.tests;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import org.junit.Before;

import java.lang.reflect.Field;

public abstract class AbsTestBase {
    @Before
    public void setUp() throws Exception {
        Field instanceA = GameObjectsFactoryA.class.getDeclaredField("instance");
        Field instanceB = GameObjectsFactoryB.class.getDeclaredField("instance");
        instanceA.setAccessible(true);
        instanceA.set(null, null);
        instanceB.setAccessible(true);
        instanceB.set(null, null);
    }
}
