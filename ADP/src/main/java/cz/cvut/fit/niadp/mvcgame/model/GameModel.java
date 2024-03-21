package cz.cvut.fit.niadp.mvcgame.model;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryB;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.builder.GameBuilder;
import cz.cvut.fit.niadp.mvcgame.builder.IBuilder;
import cz.cvut.fit.niadp.mvcgame.builder.IDirector;
import cz.cvut.fit.niadp.mvcgame.builder.LevelDirector;
import cz.cvut.fit.niadp.mvcgame.command.AbsGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.iterator.MovingStrategiesCollection;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.prototype.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.*;
import cz.cvut.fit.niadp.mvcgame.visitor.GameSoundsVisitor;
import cz.cvut.fit.niadp.mvcgame.iterator.ICyclicIterator;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

public class GameModel implements IGameModel {
    private final AbsCannon cannon;
    private final AbsGameInfo gameInfo;
    private final List<AbsBound> bounds;
    private List<AbsEnemy> enemies;
    private List<AbsCollision> collisions;
    private final AbsEndScene endScene;
    private final List<AbsMissile> missiles;


    private final List<IGameObjectsFactory> factories = new ArrayList<>();
    private Iterator<IGameObjectsFactory> factoryIterator;

    private final Map<Aspect, Set<IObserver>> observers;
    private final IVisitor visitorSounds;

    private final ICyclicIterator strategiesIterator;
    private IMovingStrategy currMovingStrategy;

    private int score;
    private final int level;
    private boolean active;

    private final Queue<AbsGameCommand> unExecutedCmds;
    private final Stack<AbsGameCommand> executedCmds;

    public GameModel(int level) {
        IGameObjectsFactory factoryA = GameObjectsFactoryA.getInstance(this);
        IGameObjectsFactory factoryB = GameObjectsFactoryB.getInstance(this);
        this.factories.addAll(Arrays.asList(factoryA, factoryB));
        this.factoryIterator = this.factories.iterator();

        this.strategiesIterator = new MovingStrategiesCollection().createIterator();
        this.currMovingStrategy = (IMovingStrategy)this.strategiesIterator.getNext();

        this.unExecutedCmds = new LinkedBlockingQueue<>( );
        this.executedCmds = new Stack<>();

        this.observers = new HashMap<>();
        this.visitorSounds = new GameSoundsVisitor();
        this.score = 0;
        this.active = true;
        this.level = level;

        IBuilder builder = new GameBuilder(factoryIterator.next(), level);
        IDirector director = new LevelDirector(builder, this);
        director.make();
        this.cannon = builder.getResult().getCannon();
        this.bounds = builder.getResult().getBounds();
        this.enemies = builder.getResult().getEnemies();
        this.gameInfo = builder.getResult().getGameInfo();
        this.endScene = builder.getResult().getEndScene();
        this.missiles = builder.getResult().getMissiles();
        this.collisions = builder.getResult().getCollisions();
    }

    @Override
    public void update() {
        this.executeCommands();
        this.moveMissiles();
        this.checkHits();
        this.destroyCollisions();
        this.checkEnd();
    }

    private void checkEnd() {
        if (!this.enemies.isEmpty() || !this.collisions.isEmpty()) {
            return;
        }

        this.endScene.setScore(this.score);
        if (this.active) {
            this.endScene.acceptVisitor(this.visitorSounds);
        }

        this.active = false;
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    private void destroyCollisions() {
        List<AbsCollision> collisionsToRemove = new ArrayList<>();
        for (AbsCollision collision : this.collisions) {
            if (collision.destroy()) {
                collisionsToRemove.add(collision);
            }
        }

        this.collisions.removeAll(collisionsToRemove);
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    private void checkHits() {
        List<AbsEnemy> enemiesToRemove = new ArrayList<>();
        List<AbsMissile> missilesToRemove = new ArrayList<>();

        for (AbsMissile missile : this.missiles) {
            for (AbsEnemy enemy : this.enemies) {
                if (missile.hit(enemy)) {
                    if (enemy.getLives() > missile.getDamage()) {
                        enemy.damage(missile.getDamage());
                    }
                    else
                    {
                        this.collisions.add(enemy.createCollision());
                        this.score += enemy.getCost();
                        enemiesToRemove.add(enemy);
                    }

                    missilesToRemove.add(missile);
                    break;
                }
            }
        }

        this.missiles.removeAll(missilesToRemove);
        this.enemies.removeAll(enemiesToRemove);
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    private void executeCommands() {
        while( !this.unExecutedCmds.isEmpty( ) ){
            AbsGameCommand cmd = this.unExecutedCmds.poll();
            cmd.doExecute();
            this.executedCmds.push(cmd);
        }
    }

    private void moveMissiles() {
        for (AbsMissile missile : this.missiles) {
            missile.move();
        }

        this.destroyMissiles();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    private void destroyMissiles() {
        List<AbsMissile> missilesToRemove = new ArrayList<>();
        for (AbsMissile missile : this.missiles) {
            if (missile.getPosition().getX() > MvcGameConfig.MAX_X + MvcGameConfig.OBJECT_SIZE.getWidth() ||
                missile.getPosition().getY() > MvcGameConfig.MAX_Y + MvcGameConfig.OBJECT_SIZE.getHeight() ||
                missile.getPosition().getX() < -MvcGameConfig.OBJECT_SIZE.getWidth() ||
                missile.getPosition().getY() < -MvcGameConfig.OBJECT_SIZE.getHeight())
            {
                missilesToRemove.add(missile);
            }
        }

        this.missiles.removeAll(missilesToRemove);
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public Position getCannonPosition() {
        return this.cannon.getPosition();
    }

    @Override
    public int getScore() { return this.score; }
    private void addScore(int points) { this.score += points; }

    @Override
    public int getLevel() { return this.level; }

    @Override
    public boolean getActive() { return this.active; }

    @Override
    public void moveCannonUp() {
        if (this.cannon.getPosition().getY() - MvcGameConfig.MOVE_STEP < MvcGameConfig.BOUND_UP_POS_Y + MvcGameConfig.OBJECT_SIZE.getHeight()) {
            return;
        }

        this.cannon.moveUp();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void moveCannonDown() {
        if (this.cannon.getPosition().getY() + MvcGameConfig.MOVE_STEP + MvcGameConfig.CANNON_SIZE.getHeight() > MvcGameConfig.BOUND_DOWN_POS_Y) {
            return;
        }

        this.cannon.moveDown();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void aimCannonUp() {
        this.cannon.aimUp();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void reload() {
        this.cannon.reload();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void aimCannonDown() {
        this.cannon.aimDown();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void powerCannonUp() {
        this.cannon.powerUp();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void powerCannonDown() {
        this.cannon.powerDown();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void increaseMissiles() {
        this.cannon.increaseMissiles();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void decreaseMissiles() {
        this.cannon.decreaseMissiles();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void cannonShoot() {
        if (this.cannon.getCapacity() < this.cannon.getMissilesToShoot()) {
            return;
        }

        this.cannon.acceptVisitor(this.visitorSounds);
        List<AbsMissile> missiles = cannon.shoot();
        this.score -= missiles.size() * missiles.get(0).getCost();

        this.missiles.addAll(missiles);
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void changeMissile() {
        if (!this.factoryIterator.hasNext()) {
            this.factoryIterator = this.factories.iterator();
        }

        this.cannon.changeFactory(this.factoryIterator.next());
    }

    @Override
    public void registerObserver(IObserver obs, Aspect aspect) {
        this.observers
            .computeIfAbsent(aspect, k -> new HashSet<>())
            .add(obs);
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        for (Set<IObserver> modelObservers : this.observers.values()) {
            modelObservers.remove(obs);
        }
    }

    @Override
    public void notifyObservers(Aspect aspect) {
        Set<IObserver> modelObservers = this.observers.get(aspect);
        if (modelObservers != null) {
            for (IObserver observer : modelObservers) {
                observer.update();
            }
        }
    }

    @Override
    public List<GameObject> getGameObjects() {
        List<GameObject> go = new ArrayList<>();
        go.add(this.cannon);
        go.addAll(this.missiles);
        go.addAll(this.bounds);
        go.addAll(this.enemies);
        go.addAll(this.collisions);
        return go;
    }

    @Override
    public AbsGameInfo getGameInfo() { return this.gameInfo; }

    @Override
    public AbsEndScene getEndScene() { return this.endScene; }

    @Override
    public List<AbsCollision> getCollisions() { return this.collisions; }

    @Override
    public List<AbsEnemy> getEnemies() { return this.enemies; }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.currMovingStrategy;
    }

    @Override
    public void toggleMovingStrategy() {
        this.currMovingStrategy = (IMovingStrategy)this.strategiesIterator.getNext();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    @Override
    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
        this.notifyObservers(Aspect.RERENDER_SCENE);
    }

    private class Memento {
        private int cannonPositionY;
        private int score;
        private List<AbsEnemy> enemies;
        private List<AbsCollision> collisions;
        private double angle;
        private int power;
        private int capacity;
    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionY = this.getCannonPosition().getY();
        gameModelSnapshot.score = this.score;
        gameModelSnapshot.enemies = new ArrayList<>(this.enemies);
        gameModelSnapshot.collisions = new ArrayList<>(this.collisions);
        gameModelSnapshot.angle = this.cannon.getAngle();
        gameModelSnapshot.power = this.cannon.getPower();
        gameModelSnapshot.capacity = this.cannon.getCapacity();
        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        this.cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
        this.score = gameModelSnapshot.score;
        this.cannon.setAngle(gameModelSnapshot.angle);
        this.cannon.setPower(gameModelSnapshot.power);
        this.enemies = new ArrayList<>(gameModelSnapshot.enemies);
        this.collisions = new ArrayList<>(gameModelSnapshot.collisions);
        this.cannon.setCapacity(gameModelSnapshot.capacity);
    }

    @Override
    public void registerCommand(AbsGameCommand command) {
        this.unExecutedCmds.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCmds.isEmpty()) {
            this.executedCmds.pop().unExecute();
            this.notifyObservers(Aspect.RERENDER_SCENE);
        }
    }
}