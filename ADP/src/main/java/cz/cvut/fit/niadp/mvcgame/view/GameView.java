package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameRenderVisitor;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

public class GameView implements IObserver {
    private final GameController controller;
    private final IGameModel model ;
    private IGameGraphics gr;
    private final GameRenderVisitor renderer;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        this.gr = null;
        this.model.registerObserver(this, IObservable.Aspect.RERENDER_SCENE);
        this.renderer = new GameRenderVisitor( );
    }

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        this.gr.clear();

        for (GameObject go : this.model.getGameObjects()) {
            go.acceptVisitor(this.renderer);
        }

        this.model.getGameInfo().acceptVisitor(this.renderer);
        if (!this.model.getActive()) {
            this.model.getEndScene().acceptVisitor(this.renderer);
        }
    }

    public void setGraphicContext(IGameGraphics gr) {
        this.gr = gr;
        this.renderer.setGraphicContext(this.gr);
        this.update();
    }

    @Override
    public void update() {
        this.render();
    }
}
