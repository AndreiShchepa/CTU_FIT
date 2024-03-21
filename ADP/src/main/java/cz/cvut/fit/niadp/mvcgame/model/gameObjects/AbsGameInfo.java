package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

import java.util.List;

public abstract class AbsGameInfo {
    protected AbsCannon cannon;
    protected IGameModel model;
    protected int spacing;
    protected Position pos;

    protected AbsGameInfo(Position pos, AbsCannon cannon, IGameModel model) {
        this.pos = pos;
        this.cannon = cannon;
        this.model = model;
    }

    public abstract List<String> getTexts();

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }

    public Position getPosition() { return this.pos; }
}
