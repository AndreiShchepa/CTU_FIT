package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JavaFxGraphics implements IGameGraphicsImplementor {
    private final GraphicsContext gr;

    public JavaFxGraphics(GraphicsContext gr) {
        this.gr = gr;
    }

    @Override
    public void drawImage(String path, Position position, Dimension2D size) {
        this.gr.drawImage(new Image(path), position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public void drawText(String text, double x, double y) {
        this.gr.fillText(text, x, y);
    }

    @Override
    public void clear() {
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
    }
}
