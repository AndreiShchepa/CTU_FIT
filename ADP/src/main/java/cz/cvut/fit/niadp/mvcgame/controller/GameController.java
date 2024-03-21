package cz.cvut.fit.niadp.mvcgame.controller;

import java.util.List;
import java.util.Objects;

import cz.cvut.fit.niadp.mvcgame.command.*;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class GameController {

    private final IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        if (this.model.getActive()) {
            for(String code : pressedKeysCodes) {
                switch(code) {
                    case MvcGameConfig.UP_KEY:
                        this.model.registerCommand(new MoveCannonUpCommand(this.model));
                        break;
                    case MvcGameConfig.DOWN_KEY:
                        this.model.registerCommand(new MoveCannonDownCommand(this.model));
                        break;
                    case MvcGameConfig.AIM_UP_KEY:
                        this.model.registerCommand(new AimCannonUpCommand(this.model));
                        break;
                    case MvcGameConfig.AIM_DOWN_KEY:
                        this.model.registerCommand(new AimCannonDownCommand(this.model));
                        break;
                    case MvcGameConfig.POWER_UP_KEY:
                        this.model.registerCommand(new PowerCannonUpCommand(this.model));
                        break;
                    case MvcGameConfig.POWER_DOWN_KEY:
                        this.model.registerCommand(new PowerCannonDownCommand(this.model));
                        break;
                    case MvcGameConfig.CHANGE_MOVING_STRATEGY_KEY:
                        this.model.registerCommand(new MovingStrategyChangeCommand(this.model));
                        break;
                    case MvcGameConfig.CHANGE_SHOOTING_MODE_KEY:
                        this.model.registerCommand(new ShootingModeChangeCommand(this.model));
                        break;
                    case MvcGameConfig.UNDO_LAST_COMMAND_KEY:
                        this.model.undoLastCommand();
                        break;
                    case MvcGameConfig.SHOOT_KEY:
                        this.model.registerCommand(new CannonShootCommand(this.model));
                        break;
                    case MvcGameConfig.CHANGE_MISSILE_KEY:
                        this.model.registerCommand(new MissileChangeCommand(this.model));
                        break;
                    case MvcGameConfig.INCREASE_MISSILES_KEY:
                        this.model.registerCommand(new MissilesIncreaseCommand(this.model));
                        break;
                    case MvcGameConfig.DECREASE_MISSILES_KEY:
                        this.model.registerCommand(new MissilesDecreaseCommand(this.model));
                        break;
                    case MvcGameConfig.CANNON_RELOAD_KEY:
                        this.model.registerCommand(new CannonReloadCommand(this.model));
                        break;
                }
            }
        }

        for(String code : pressedKeysCodes) {
            if (Objects.equals(code, MvcGameConfig.EXIT_KEY)) {
                System.exit(0);
                break;
            }
        }

        this.model.update();
        pressedKeysCodes.clear();
    }
}
