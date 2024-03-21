package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEndScene;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class GameSoundsVisitor implements IVisitorSounds {
    private MediaPlayer mediaPlayer;

    @Override
    public void visitCannon(AbsCannon cannon) {
        Media sound = new Media(new File(MvcGameConfig.CANNON_SHOOT_SOUND_RESOURCE).toURI().toString());
        this.mediaPlayer = new MediaPlayer(sound);
        this.mediaPlayer.play();

        this.mediaPlayer.setOnEndOfMedia(() -> this.mediaPlayer.dispose());
    }

    @Override
    public void visitEndScene(AbsEndScene endScene) {
        Media sound = new Media(new File(MvcGameConfig.END_GAME_SOUND_RESOURCE).toURI().toString());
        this.mediaPlayer = new MediaPlayer(sound);
        this.mediaPlayer.play();

        this.mediaPlayer.setOnEndOfMedia(() -> this.mediaPlayer.dispose());
    }
}
