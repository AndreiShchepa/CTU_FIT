package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import javafx.geometry.Dimension2D;

public class GameGraphics implements IGameGraphics {
    private final IGameGraphicsImplementor implementor;

    public GameGraphics(IGameGraphicsImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void drawImage(String path, Position position, Dimension2D size) {
        this.implementor.drawImage(path, position, size);
    }

    @Override
    public void fillText(String text, double x, double y) {
        this.implementor.drawText(text, x, y);
    }

    @Override
    public void clear() {
        this.implementor.clear();
    }
}
