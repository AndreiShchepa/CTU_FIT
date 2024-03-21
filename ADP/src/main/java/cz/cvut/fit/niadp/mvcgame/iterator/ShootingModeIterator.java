package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import java.util.List;

public class ShootingModeIterator implements ICyclicIterator {
    private int index = 0;
    private final List<IShootingMode> collection;

    public ShootingModeIterator(List<IShootingMode> collection) {
        this.collection = collection;
    }

    @Override
    public Object getNext() {
        if (this.hasMore()) {
            return this.collection.get(index++);
        }

        return null;
    }

    @Override
    public boolean hasMore() {
        if (index == this.collection.size()) {
            index = 0;
        }

        return this.collection.get(index) != null;
    }
}
