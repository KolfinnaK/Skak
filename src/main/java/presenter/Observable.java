package presenter;

import vidmot.Observer;

public interface Observable {
    /**
     * Attaches the given observer to this observable.
     *
     * Each observer should be updated on a call to notifyObservers.
     * @param observer
     */
    void attach(Observer observer);

    /**
     * Notifies all the observable's observers that the state has been changed.
     */
    void notifyObservers();

}
