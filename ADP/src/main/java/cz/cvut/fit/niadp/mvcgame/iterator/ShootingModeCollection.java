package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;

import java.util.Arrays;
import java.util.List;

public class ShootingModeCollection implements IterableCollection {
    private final List<IShootingMode> shootingModes;

    public ShootingModeCollection() {
        this.shootingModes = Arrays.asList(
            new SingleShootingMode(),
            new DoubleShootingMode(),
            new DynamicShootingMode()
        );
    }

    @Override
    public ICyclicIterator createIterator() {
        return new ShootingModeIterator(this.shootingModes);
    }
}
