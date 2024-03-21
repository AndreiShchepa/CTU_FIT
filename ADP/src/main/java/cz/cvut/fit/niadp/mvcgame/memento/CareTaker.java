package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.ArrayDeque;
import java.util.Deque;

public class CareTaker {
    private IGameModel model;
    private Deque<Object> mementos = new ArrayDeque<>();

    private CareTaker() {

    }

    private static class SingletonHolder {
        private static final CareTaker INSTANCE = new CareTaker();
    }

    public static CareTaker getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setModel(IGameModel model) {
        this.model = model;
    }

    public void createMemento() {
        if (this.model != null) {
            this.mementos.push(this.model.createMemento());
        }
    }

    public void setMemento() {
        if (this.model != null && !this.mementos.isEmpty()) {
            this.model.setMemento(this.mementos.pop());
        }
    }
}