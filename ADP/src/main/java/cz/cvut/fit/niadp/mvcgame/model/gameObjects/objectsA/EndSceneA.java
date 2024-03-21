package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEndScene;

import java.util.Arrays;

public class EndSceneA extends AbsEndScene {
    public EndSceneA(int expectedScore) {
        this.score = 0;
        this.expectedScore = expectedScore;
        this.starsPos.addAll(Arrays.asList(
            new Position(MvcGameConfig.STAR1_POS_X, MvcGameConfig.STAR1_POS_Y),
            new Position(MvcGameConfig.STAR2_POS_X, MvcGameConfig.STAR2_POS_Y),
            new Position(MvcGameConfig.STAR3_POS_X, MvcGameConfig.STAR3_POS_Y)
        ));
    }

    public void setScore(int score) {
        this.score = score;
    }
}
