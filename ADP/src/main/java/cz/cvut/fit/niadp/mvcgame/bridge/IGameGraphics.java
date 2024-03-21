package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import javafx.geometry.Dimension2D;

public interface IGameGraphics {
    void drawImage(String path, Position position, Dimension2D size);
    void fillText(String text, double x, double y);
    void clear();
    // add sounds
}
