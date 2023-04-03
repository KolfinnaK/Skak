package presenter;

public interface Mediator {
    /**
     * Notifies the mediator that the state of the given object has changed.
     * @param o
     */
    void notify(Object o);
}
