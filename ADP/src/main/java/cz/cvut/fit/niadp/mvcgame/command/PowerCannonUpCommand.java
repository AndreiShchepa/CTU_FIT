package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class PowerCannonUpCommand extends AbsGameCommand {
    public PowerCannonUpCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.powerCannonUp();
    }
}
