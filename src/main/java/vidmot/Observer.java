package vidmot;

public interface Observer {
    /**
     * Updates the state of the observer.
     *
     * Should be called when the state of the observable is changed.
     */
    void update();
}
