package presenter;

import vidmot.Observer;

import java.util.LinkedList;
import java.util.List;

public class NotationPresenter implements Observable {
    private List<String> movementNotations;
    private List<Observer> observers;
    private boolean gameIsOver;

    public NotationPresenter() {
        movementNotations = new LinkedList<>();
        observers = new LinkedList<>();
        gameIsOver = false;
    }

    public boolean isGameOver() {
        return gameIsOver;
    }

    public void addToMovementNotations(String movementNotation) {
        movementNotations.add(movementNotation);
        notifyObservers();
    }

    public int getSizeOfMovementNotations() {
        return movementNotations.size();
    }

    public String getLastMovement() {
        return movementNotations.get(movementNotations.size() - 1);
    }

    public void gameIsOver() {
        gameIsOver = true;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
}
