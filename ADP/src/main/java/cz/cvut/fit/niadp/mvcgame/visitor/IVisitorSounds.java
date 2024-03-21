package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEndScene;

public interface IVisitorSounds extends IVisitor {
    @Override
    void visitCannon(AbsCannon cannon);

    @Override
    void visitEndScene(AbsEndScene endScene);
}
