package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.LevelModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameBuilder implements IBuilder {
    private final LevelModel result;
    private final IGameObjectsFactory factory;
    private final int level;

    public GameBuilder(IGameObjectsFactory factory, int level) {
        this.factory = factory;
        this.level = level;
        this.result = new LevelModel();
    }

    @Override
    public void buildCannon() {
        result.setCannon(factory.createCannon());
    }

    @Override
    public void buildBounds() {
        this.result.setBounds(Arrays.asList(
            factory.createBound("UPPER"),
            factory.createBound("LOWER")
        ));
    }

    @Override
    public void buildEnemies(GameObjectsFactoryA factoryA, GameObjectsFactoryB factoryB) {
        List<AbsEnemy> enemies = new ArrayList<>();
        Map<String, Object> levelData = MvcGameConfig.LEVEL_ENEMIES.get(level);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> enemiesList = (List<Map<String, Object>>) levelData.get("enemies");
        for (Map<String, Object> enemy : enemiesList) {
            if ("A".equals(enemy.get("type"))) {
                enemies.add(factoryA.createEnemy(new Position((int) enemy.get("x_pos"), (int) enemy.get("y_pos"))));
            } else {
                enemies.add(factoryB.createEnemy(new Position((int) enemy.get("x_pos"), (int) enemy.get("y_pos"))));
            }
        }

        this.result.setEnemies(enemies);
    }

    @Override
    public void buildGameInfo() {
        this.result.setGameInfo(factory.createGameInfo(this.result.getCannon()));
    }

    @Override
    public void buildEndScene() {
        Map<String, Object> levelData = MvcGameConfig.LEVEL_ENEMIES.get(this.level);
        int expectedScore = (Integer) levelData.get("expectedScore");
        this.result.setEndScene(this.factory.createEndScene(expectedScore));
    }

    @Override
    public void buildMissiles() {
        this.result.setMissiles(new ArrayList<>());
    }

    @Override
    public void buildCollisions() {
        this.result.setCollisions(new ArrayList<>());
    }

    @Override
    public LevelModel getResult() { return this.result; }
}
