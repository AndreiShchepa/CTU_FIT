package cz.cvut.fit.niadp.mvcgame.tests;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class SimpleSecondTest extends AbsTestBase {
    private IGameModel model;
    private int initialYPosition;

    @Before
    public void setUp() {
        model = new GameModel(1);
        initialYPosition = model.getCannonPosition().getY();
    }

    @Test
    public void commandUndoShouldRevertCannonPosition() {
        model.registerCommand(new MoveCannonUpCommand(model));
        model.update();

        int positionAfterExecution = model.getCannonPosition().getY();
        Assert.assertEquals("Cannon should move up by MOVE_STEP after execution",
                initialYPosition - MvcGameConfig.MOVE_STEP, positionAfterExecution);

        model.undoLastCommand();
        int positionAfterUndo = model.getCannonPosition().getY();
        Assert.assertEquals("Cannon should return to initial position after undo",
                initialYPosition, positionAfterUndo);
    }
}