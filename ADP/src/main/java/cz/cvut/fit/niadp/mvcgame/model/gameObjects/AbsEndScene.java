package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

import java.util.ArrayList;

public abstract class AbsEndScene {
    protected int score;
    protected int expectedScore;
    protected ArrayList<Position> starsPos = new ArrayList<>();

    public int getScore() { return this.score; }
    public int getExpectedScore() { return this.expectedScore; }
    public ArrayList<Position> getStarsPos() { return this.starsPos; }
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEndScene(this);
    }

    public abstract void setScore(int score);
}
