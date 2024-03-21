package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.strategy.*;
import java.util.List;

public class MovingStrategyIterator implements ICyclicIterator {
    private int index = 0;
    private final List<IMovingStrategy> collection;

    public MovingStrategyIterator(List<IMovingStrategy> collection) {
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
