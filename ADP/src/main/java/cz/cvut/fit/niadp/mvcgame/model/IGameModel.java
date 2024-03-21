package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.AbsGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public interface IGameModel extends IObservable {
    void update();

    Position getCannonPosition();

    int getScore();

    int getLevel();

    boolean getActive();

    void moveCannonUp();

    void moveCannonDown();

    void aimCannonUp();

    void aimCannonDown();

    void powerCannonUp();

    void powerCannonDown();

    void increaseMissiles();

    void decreaseMissiles();

    void cannonShoot();

    void changeMissile();

    public void reload();

    void registerObserver(IObserver obs, IObservable.Aspect aspect);

    void unregisterObserver(IObserver obs);

    void notifyObservers(IObservable.Aspect aspect);

    List<GameObject> getGameObjects();

    AbsGameInfo getGameInfo();

    AbsEndScene getEndScene();

    List<AbsCollision> getCollisions();

    List<AbsEnemy> getEnemies();

    IMovingStrategy getMovingStrategy();

    void toggleMovingStrategy();

    void toggleShootingMode();

    Object createMemento();

    void setMemento(Object memento);

    void registerCommand(AbsGameCommand command);

    void undoLastCommand();
}
