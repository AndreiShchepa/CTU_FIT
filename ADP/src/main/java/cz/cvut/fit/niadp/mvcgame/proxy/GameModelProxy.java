package cz.cvut.fit.niadp.mvcgame.proxy;

import cz.cvut.fit.niadp.mvcgame.command.AbsGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public class GameModelProxy implements IGameModel {
    private final IGameModel subject;

    public GameModelProxy(IGameModel model) {
        this.subject = model;
    }

    @Override
    public void update() {
        this.subject.update();
    }

    @Override
    public Position getCannonPosition() {
        return this.subject.getCannonPosition();
    }

    @Override
    public int getScore() {
        return this.subject.getScore();
    }

    @Override
    public int getLevel() {
        return this.subject.getLevel();
    }

    @Override
    public boolean getActive() { return this.subject.getActive(); }

    @Override
    public void moveCannonUp() {
        this.subject.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        this.subject.moveCannonDown();
    }

    @Override
    public void aimCannonUp() {
        this.subject.aimCannonUp();
    }

    @Override
    public void aimCannonDown() {
        this.subject.aimCannonDown();
    }

    @Override
    public void powerCannonUp() {
        this.subject.powerCannonUp();
    }

    @Override
    public void powerCannonDown() {
        this.subject.powerCannonDown();
    }

    @Override
    public void increaseMissiles() {
        this.subject.increaseMissiles();
    }

    @Override
    public void decreaseMissiles() {
        this.subject.decreaseMissiles();
    }

    @Override
    public void cannonShoot() {
        this.subject.cannonShoot();
    }

    @Override
    public void changeMissile() {
        this.subject.changeMissile();
    }

    @Override
    public void reload() {
        this.subject.reload();
    }

    @Override
    public void registerObserver(IObserver obs, Aspect aspect) {
        this.subject.registerObserver(obs, aspect);
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        this.subject.unregisterObserver(obs);
    }

    @Override
    public void notifyObservers(Aspect aspect) {
        this.subject.notifyObservers(aspect);
    }

    @Override
    public List<GameObject> getGameObjects() {
        return this.subject.getGameObjects();
    }

    @Override
    public AbsGameInfo getGameInfo() {
        return this.subject.getGameInfo();
    }

    @Override
    public AbsEndScene getEndScene() { return this.subject.getEndScene(); }

    @Override
    public List<AbsCollision> getCollisions() { return this.subject.getCollisions(); }

    @Override
    public List<AbsEnemy> getEnemies() { return this.subject.getEnemies(); }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.subject.getMovingStrategy();
    }

    @Override
    public void toggleMovingStrategy() {
        this.subject.toggleMovingStrategy();
    }

    @Override
    public void toggleShootingMode() {
        this.subject.toggleShootingMode();
    }

    @Override
    public Object createMemento() {
        return this.subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
        this.subject.setMemento(memento);
    }

    @Override
    public void registerCommand(AbsGameCommand command) {
        this.subject.registerCommand(command);
    }

    @Override
    public void undoLastCommand() {
        this.subject.undoLastCommand();
    }
}
