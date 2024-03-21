package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

import java.util.List;

public class GameRenderVisitor implements IVisitorRender {
    private IGameGraphics gr;

    public void setGraphicContext(IGameGraphics gr) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        gr.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE,
            cannon.getPosition(),
            MvcGameConfig.CANNON_SIZE);
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        gr.drawImage(missile.getImagePath(),
            missile.getPosition(),
            MvcGameConfig.OBJECT_SIZE);
    }

    @Override
    public void visitBound(AbsBound bound) {
        gr.drawImage(MvcGameConfig.BOUND_IMAGE_RESOURCE,
            bound.getPosition(),
            MvcGameConfig.OBJECT_SIZE);
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        gr.drawImage(enemy.getImagePath(),
            enemy.getPosition(),
            MvcGameConfig.OBJECT_SIZE);
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        gr.drawImage(collision.getImagePath(),
            collision.getPosition(),
            MvcGameConfig.OBJECT_SIZE);
    }

    @Override
    public void visitGameInfo(AbsGameInfo gameInfo) {
        List<String> gameText = gameInfo.getTexts();
        if (gameText == null) {
            return;
        }

        int i = 0;
        for (String text : gameText){
            this.gr.fillText(text, gameInfo.getPosition().getX(),
                gameInfo.getPosition().getY() + MvcGameConfig.GAME_INFO_TEXT_SPACING * i);
            i++;
        }
    }

    @Override
    public void visitEndScene(AbsEndScene endScene) {
        double ratio = endScene.getScore() / (double)endScene.getExpectedScore();
        int stars = ratio > 0.7 ? 3 : (ratio > 0.3 ? 2 : (ratio > 0 ? 1 : 0));

        for (int i = 0; i < stars; i++) {
            gr.drawImage(MvcGameConfig.FILLED_STAR_RESOURCE,
                endScene.getStarsPos().get(i),
                MvcGameConfig.STAR_SIZE);
        }

        for (int i = 0; i < 3 - stars; i++) {
            gr.drawImage(MvcGameConfig.EMPTY_STAR_RESOURCE,
                endScene.getStarsPos().get(stars + i),
                MvcGameConfig.STAR_SIZE);
        }

        this.gr.fillText("Score: " + endScene.getScore(), endScene.getStarsPos().get(1).getX() - 15,
            endScene.getStarsPos().get(1).getY() + 135);
        this.gr.fillText("Expected score: " + endScene.getExpectedScore(), endScene.getStarsPos().get(1).getX() - 15,
                endScene.getStarsPos().get(1).getY() + 150);
    }
}

