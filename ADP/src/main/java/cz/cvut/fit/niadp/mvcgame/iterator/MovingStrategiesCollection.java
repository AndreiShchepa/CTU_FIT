package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.strategy.*;

import java.util.Arrays;
import java.util.List;

public class MovingStrategiesCollection implements IterableCollection {
    private final List<IMovingStrategy> movingStrategies;

    public MovingStrategiesCollection() {
        this.movingStrategies = Arrays.asList(
            new SimpleMovingStrategy(),
            new RealisticMovingStrategy(),
            new SinusoidMovingStrategy(),
            new RandomMovingStrategy()
        );
    }

    @Override
    public ICyclicIterator createIterator() {
        return new MovingStrategyIterator(this.movingStrategies);
    }
}
