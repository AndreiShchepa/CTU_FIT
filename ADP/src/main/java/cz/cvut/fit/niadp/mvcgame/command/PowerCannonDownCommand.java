package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class PowerCannonDownCommand extends AbsGameCommand {
    public PowerCannonDownCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.powerCannonDown();
    }
}
