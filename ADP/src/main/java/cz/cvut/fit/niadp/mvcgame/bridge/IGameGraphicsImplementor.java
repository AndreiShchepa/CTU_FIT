package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import javafx.geometry.Dimension2D;

public interface IGameGraphicsImplementor {
    void drawImage(String path, Position position, Dimension2D size);
    void drawText(String text, double x, double y);
    void clear();
}
