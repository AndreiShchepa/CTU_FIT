package cz.cvut.fit.niadp.mvcgame.visitor;


import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

public interface IVisitor {
    default void visitCannon(AbsCannon cannon) {}
    default void visitMissile(AbsMissile missile) {}
    default void visitBound(AbsBound bound) {}
    default void visitEnemy(AbsEnemy enemy) {}
    default void visitCollision(AbsCollision collision) {}
    default void visitGameInfo(AbsGameInfo absGameInfo) {}
    default void visitEndScene(AbsEndScene endScene) {}
}
