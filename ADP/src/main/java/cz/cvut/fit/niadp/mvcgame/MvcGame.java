package cz.cvut.fit.niadp.mvcgame;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.proxy.GameModelProxy;
import cz.cvut.fit.niadp.mvcgame.view.GameView;
import javafx.scene.canvas.GraphicsContext;

public class MvcGame {

    private IGameModel model;
    private GameView view;
    private GameController controller;

    public void init(int level) {
        this.model = new GameModelProxy(new GameModel(level));
        this.view = new GameView(model);
        this.controller = this.view.getController();
        CareTaker.getInstance().setModel(this.model);
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        this.controller.processPressedKeys(pressedKeysCodes);
    }

    public void update() {
        this.model.update();
    }

    public void render(IGameGraphics gr) {
        this.view.setGraphicContext(gr);
    }

    public String getWindowTitle() {
        return MvcGameConfig.GAME_TITLE;
    }

    public int getWindowWidth() {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight() {
        return  MvcGameConfig.MAX_Y;
    }

}