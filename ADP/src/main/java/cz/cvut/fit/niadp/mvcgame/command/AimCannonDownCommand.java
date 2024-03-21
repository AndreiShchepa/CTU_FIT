package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class AimCannonDownCommand extends AbsGameCommand{
    public AimCannonDownCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.aimCannonDown();
    }
}
