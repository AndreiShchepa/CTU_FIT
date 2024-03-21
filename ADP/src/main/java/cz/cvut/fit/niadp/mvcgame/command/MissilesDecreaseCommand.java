package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class MissilesDecreaseCommand extends AbsGameCommand {
    public MissilesDecreaseCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.decreaseMissiles();
    }
}
