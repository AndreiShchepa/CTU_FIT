package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.AbsGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

import java.util.List;

public class LevelModel {
    private AbsCannon cannon;
    private AbsGameInfo gameInfo;
    private List<AbsBound> bounds;
    private List<AbsEnemy> enemies;
    private List<AbsCollision> collisions;
    private AbsEndScene endScene;
    private List<AbsMissile> missiles;

    public AbsCannon getCannon() {
        return cannon;
    }

    public AbsEndScene getEndScene() {
        return endScene;
    }

    public AbsGameInfo getGameInfo() {
        return gameInfo;
    }

    public List<AbsBound> getBounds() {
        return bounds;
    }

    public List<AbsCollision> getCollisions() {
        return collisions;
    }

    public List<AbsEnemy> getEnemies() {
        return enemies;
    }

    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    public void setBounds(List<AbsBound> bounds) {
        this.bounds = bounds;
    }

    public void setCannon(AbsCannon cannon) {
        this.cannon = cannon;
    }

    public void setCollisions(List<AbsCollision> collisions) {
        this.collisions = collisions;
    }

    public void setEndScene(AbsEndScene endScene) {
        this.endScene = endScene;
    }

    public void setEnemies(List<AbsEnemy> enemies) {
        this.enemies = enemies;
    }

    public void setGameInfo(AbsGameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void setMissiles(List<AbsMissile> missiles) {
        this.missiles = missiles;
    }
}
