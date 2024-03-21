package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class MovingStrategyChangeCommand extends AbsGameCommand {
    public MovingStrategyChangeCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.toggleMovingStrategy();
    }
}
