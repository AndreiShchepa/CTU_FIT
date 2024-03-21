package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

public abstract class LifeTimeGameObject extends GameObject {
    private final LocalDateTime bornAt;

    protected LifeTimeGameObject(Position position) {
        this.position = position;
        this.bornAt = LocalDateTime.now();
    }

    public long getAge() {
        return ChronoUnit.MILLIS.between(this.bornAt, LocalDateTime.now());
    }
}