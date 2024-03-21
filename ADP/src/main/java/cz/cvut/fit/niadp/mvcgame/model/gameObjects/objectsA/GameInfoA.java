package cz.cvut.fit.niadp.mvcgame.model.gameObjects.objectsA;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;

import java.util.ArrayList;
import java.util.List;

public class GameInfoA extends AbsGameInfo {
    public GameInfoA(Position pos, AbsCannon cannon, IGameModel model) {
        super(pos, cannon, model);
    }

    @Override
    public List<String> getTexts() {
        List<String> gameInfoTexts = new ArrayList<String>();

        if (this.model != null) {
            gameInfoTexts.add("Level: " + model.getLevel());
            gameInfoTexts.add("Score: " + this.model.getScore());
            gameInfoTexts.add("Alive enemies: " + model.getEnemies().size());
            gameInfoTexts.add("Active collisions: " + model.getCollisions().size());
            gameInfoTexts.add("Moving strategy: " + model.getMovingStrategy().getName());
        }
        if (this.cannon != null) {
            gameInfoTexts.add("Shooting mode: " + cannon.getShootingMode().getName());
            gameInfoTexts.add("Missiles to shoot: " + cannon.getMissilesToShoot());
            gameInfoTexts.add("Angle: " + (double) Math.round(cannon.getAngle() * 100.0) / 100.0);
            gameInfoTexts.add("Power: " + cannon.getPower());
            gameInfoTexts.add("Capacity: " + (cannon.getCapacity() > 0 ? cannon.getCapacity() : "NEED TO RELOAD"));
        }

        return gameInfoTexts;
    }
}
