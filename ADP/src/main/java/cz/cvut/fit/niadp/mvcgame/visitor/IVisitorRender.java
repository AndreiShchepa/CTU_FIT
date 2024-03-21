package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

public interface IVisitorRender extends IVisitor {
    @Override
    void visitCannon(AbsCannon cannon);

    @Override
    void visitMissile(AbsMissile missile);

    @Override
    void visitBound(AbsBound bound);

    @Override
    void visitEnemy(AbsEnemy enemy);

    @Override
    void visitCollision(AbsCollision collision);

    @Override
    void visitGameInfo(AbsGameInfo gameInfo);

    @Override
    void visitEndScene(AbsEndScene endScene);
}
