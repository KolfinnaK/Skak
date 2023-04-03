package presenter;

import vinnsla.game.ChessGame;
import vinnsla.game.ChessGameInterface;
import vinnsla.util.Colors;
import vinnsla.util.Pieces;
import vinnsla.util.Tile;
import vidmot.Observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public abstract class BoardPresenter implements Observable {
    protected GameMediator gameMediator;
    protected ChessGameInterface game;
    protected TilePresenter[][] tilePresenters;
    protected Pieces[][] boardState;
    protected TilePresenter selected;
    protected Set<Tile> available;
    protected List<Observer> observers;
    protected boolean animationIsPlaying;
    protected Queue<Movement> movements;
    protected List<String> movementNotations;
    private boolean isGameOver, isGameInStalemate, isPlayerInCheckmate, didWhiteWin, whiteIsOutOfTime, blackIsOutOfTime;

    public BoardPresenter(GameMediator gameMediator) {
        this.gameMediator = gameMediator;
        game = new ChessGame();
        setUpState();
        initializeTilePresenters();
        updateFlags();
    }

    public BoardPresenter() {
        this(null);
    }

    private void setUpState() {
        boardState = game.getBoardState();
        selected = null;
        available = null;
        isGameOver = false;
        isGameInStalemate = false;
        isPlayerInCheckmate = false;
        didWhiteWin = false;
        whiteIsOutOfTime = false;
        blackIsOutOfTime = false;
        observers = new LinkedList<>();
        movements = new LinkedList<>();
        movementNotations = new LinkedList<>();
    }

    private void initializeTilePresenters() {
        tilePresenters = new TilePresenter[8][8];
        for (int i = 0; i < tilePresenters.length; i++)
            for (int j = 0; j < tilePresenters[i].length; j++)
                tilePresenters[i][j] = new TilePresenter(i, j, boardState[i][j], game.doesTileContainPieceOfCurrentPlayersColor(i, j));
    }

    protected void updateFlags() {
        updateIsGameInStalemateFlag();
        updateIsPlayerInCheckmateFlag();
        updateDidWhiteWinFlag();
    }

    private void updateIsGameInStalemateFlag() {
        if (this.isGameInStalemate == game.isGameInStalemate())
            return;
        this.isGameInStalemate = game.isGameInStalemate();
        notifyObservers();
    }

    private void updateIsPlayerInCheckmateFlag() {
        if (this.isPlayerInCheckmate == game.isCurrentPlayerInCheckmate())
            return;
        this.isPlayerInCheckmate = game.isCurrentPlayerInCheckmate();
        notifyObservers();
    }

    private void updateDidWhiteWinFlag() {
        boolean didWhiteWin = game.getCurrentPlayerColor() == Colors.BLACK && game.isCurrentPlayerInCheckmate();
        if (this.didWhiteWin == didWhiteWin)
            return;
        this.didWhiteWin = didWhiteWin;
        notifyObservers();
    }

    /*
     *  Getters
     */

    public TilePresenter getChessBoardTilePresenter(int row, int col) {
        return tilePresenters[row][col];
    }

    public boolean isGameInStalemate() {
        return isGameInStalemate;
    }

    public boolean isPlayerInCheckmate() {
        return isPlayerInCheckmate;
    }

    public boolean isWhiteOutOfTime() {
        return whiteIsOutOfTime;
    }

    public boolean isBlackOutOfTime() {
        return blackIsOutOfTime;
    }

    public Colors getCurrentPlayersColor() {
        return game.getCurrentPlayerColor();
    }

    public List<String> getMovementNotations() {
        return movementNotations;
    }

    /*
     *  hoverInTo, hoverOutOf, and click are called when events are triggered in the view.
     */

    public void hoverInTo(int row, int col) {
        if (animationIsPlaying || isGameOver)
            return;
        tilePresenters[row][col].setHoveredOver(true);
    }

    public void hoverOutOf(int row, int col) {
        if (animationIsPlaying || isGameOver)
            return;
        tilePresenters[row][col].setHoveredOver(false);
    }

    public abstract void click(int row, int col);

    protected void setAvailableTiles() {
        for (Tile availableTile : available)
            tilePresenters[availableTile.getRow()][availableTile.getCol()].setAvailable(true);
    }

    public void resetAvailableTiles() {
        selected = null;
        if (available == null)
            return;
        for (Tile availableSpace : available)
            tilePresenters[availableSpace.getRow()][availableSpace.getCol()].setAvailable(false);
        available = null;
    }

    protected void updateTilePresenterPieces() {
        for (int i = 0; i < boardState.length; i++)
            for (int j = 0; j < boardState.length; j++)
                tilePresenters[i][j].setPiece(boardState[i][j]);
    }

    protected void updateTilePresenterSelectability() {
        for (int i = 0; i < boardState.length; i++)
            for (int j = 0; j < boardState.length; j++)
                tilePresenters[i][j].setContainsPieceOfCurrentPlayersColor(game.doesTileContainPieceOfCurrentPlayersColor(i, j));
    }

    protected boolean aTileIsSelectedAndGivenTileIsAvailable(int row, int col) {
        return selected != null && available.contains(Tile.getTile(row, col));
    }

    public void resetHoveredOverTiles() {
        for (int i = 0; i < boardState.length; i++)
            for (int j = 0; j < boardState.length; j++)
                tilePresenters[i][j].setHoveredOver(false);
    }

    public void timerRanOutForColor(Colors color) {
        if (color == Colors.WHITE)
            whiteIsOutOfTime = true;
        else
            blackIsOutOfTime = true;
        notifyObservers();
    }

    public void executeQueuedMovement() {
        while (movements.size() > 0) {
            Movement movement = movements.poll();
            String notation = game.getAlgebraicNotationForMove(Tile.getTile(movement.startRow, movement.startCol), Tile.getTile(movement.endRow, movement.endCol));
            game.move(movement.startRow, movement.startCol, movement.endRow, movement.endCol);
            resetHoveredOverTiles();
            resetAvailableTiles();              // reset available spaces
            boardState = game.getBoardState();  // update the boardState
            updateTilePresenterPieces();        // update the state of each of the tiles (which in turn updates the view)
            updateTilePresenterSelectability(); // updates the state of each tile's select-ability
            updateFlags();                      // checks if the game is over
            movementNotations.add(notation);
        }
        animationIsPlaying = false;
        if (gameMediator != null)
            gameMediator.notify(this);
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

    protected class Movement {
        int startRow, endRow, startCol, endCol;

        public Movement(int startRow, int startCol, int endRow, int endCol) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
        }
    }
}
