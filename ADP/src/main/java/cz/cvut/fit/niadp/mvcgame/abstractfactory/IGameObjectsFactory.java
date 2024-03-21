package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

public interface IGameObjectsFactory {
    AbsCannon createCannon();
    AbsBound createBound(String type);
    AbsMissile createMissile(double initAngle, int initVelocity);
    AbsGameInfo createGameInfo(AbsCannon cannon);
    AbsEnemy createEnemy(Position pos);
    AbsCollision createCollision(Position pos);
    AbsEndScene createEndScene(int expectedScore);
}

