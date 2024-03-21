package cz.cvut.fit.niadp.mvcgame.observer;

public interface IObservable {

    public void registerObserver(IObserver obs, Aspect aspect);
    public void unregisterObserver(IObserver obs);
    public void notifyObservers(Aspect aspect);

    public enum Aspect {
        RERENDER_SCENE,
        SOUND_PLAYED
    }

}
