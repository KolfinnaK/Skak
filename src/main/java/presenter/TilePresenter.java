package presenter;

import vinnsla.util.Pieces;
import vidmot.Observer;

import java.util.LinkedList;
import java.util.List;

public class TilePresenter implements Observable {
    private int row, col;
    private boolean hoveredOver, available, containsPieceOfCurrentPlayersColor;
    private Pieces piece;
    private List<Observer> observers;

    public TilePresenter(int row, int col, Pieces piece, boolean containsPieceOfCurrentPlayersColor) {
        setUpState(row, col, piece, containsPieceOfCurrentPlayersColor);
        notifyObservers();
    }

    private void setUpState(int row, int col, Pieces piece, boolean containsPieceOfCurrentPlayersColor) {
        this.row = row;
        this.col = col;
        this.piece = piece;
        this.containsPieceOfCurrentPlayersColor = containsPieceOfCurrentPlayersColor;
        hoveredOver = false;
        available = false;
        observers = new LinkedList<>();
    }

    /*
     *  Getters
     */

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Pieces getPiece() {
        return piece;
    }

    public String getStyle() {
        if (hoveredOver && (available || containsPieceOfCurrentPlayersColor))
            return "-fx-background-color: #69a35d;"; //dekkri grænn
        if (row % 2 == col % 2) {
            if (available)
                return "-fx-background-color: #80c971;"; //ljósari grænn
            else
                return "-fx-background-color: #828583;"; //Grár
        } else {
            if (available)
                return "-fx-background-color: #92e681;"; //ljósgrænn
            else
                return "-fx-background-color: rgb(255, 249, 248);";
        }
    }

    /*
     *  Setters
     */

    public void setPiece(Pieces piece) {
        if (this.piece == piece)
            return;
        this.piece = piece;
        notifyObservers();
    }

    public void setAvailable(boolean available) {
        if (this.available == available)
            return;
        this.available = available;
        notifyObservers();
    }

    public void setHoveredOver(boolean hoveredOver) {
        if (this.hoveredOver == hoveredOver)
            return;
        this.hoveredOver = hoveredOver;
        notifyObservers();
    }

    public void setContainsPieceOfCurrentPlayersColor(boolean containsPieceOfCurrentPlayersColor) {
        if (this.containsPieceOfCurrentPlayersColor == containsPieceOfCurrentPlayersColor)
            return;
        this.containsPieceOfCurrentPlayersColor = containsPieceOfCurrentPlayersColor;
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
