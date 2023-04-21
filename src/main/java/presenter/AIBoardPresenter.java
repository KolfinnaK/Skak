package presenter;

import vinnsla.ai.AIInterface;
import vinnsla.ai.Audvelt;
import vinnsla.ai.Erfitt;
import vinnsla.util.Colors;
import vinnsla.util.Pair;
import vinnsla.util.Tile;
import vidmot.Observer;
import vidmot.hnutar.BoardNode;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;


public class AIBoardPresenter extends BoardPresenter {
    private AIInterface bot;

    public AIBoardPresenter(GameMediator gameMediator, String botName) {
        super(gameMediator);
        if (botName.equals("audvelt"))
            this.bot = new Audvelt(game);
        else if (botName.equals("erfitt"))
            this.bot = new Erfitt(game);
        else
            throw new IllegalArgumentException("Invalid botName.");
    }

    @Override
    public void click(int row, int col) {
        if (animationIsPlaying || super.isGameInStalemate() || super.isPlayerInCheckmate())
            return;

        if (aTileIsSelectedAndGivenTileIsAvailable(row, col)) {
            animationIsPlaying = true;
            for (Observer observer : observers)
                ((BoardNode) observer).movePieceAnimation(selected.getRow(), selected.getCol(), row, col);
            movements.add(new Movement(selected.getRow(), selected.getCol(), row, col));
        } else if (game.doesTileContainPieceOfCurrentPlayersColor(row, col)) {
            if (available != null)
                resetAvailableTiles();
            selected = tilePresenters[row][col];
            available = game.getAvailableMovesForTile(row, col);
            setAvailableTiles();
        }
    }

    @Override
    public void executeQueuedMovement() {
        if (game.getCurrentPlayerColor() == Colors.WHITE) {
            super.executeQueuedMovement();
            animationIsPlaying = true;

            Task<Pair<Tile, Tile>> task = new Task<>() {
                @Override
                protected Pair<Tile, Tile> call() throws Exception {
                    if (isGameInStalemate() || isPlayerInCheckmate())
                        return null;
                    Pair<Tile, Tile> move = bot.move();
                    return move;
                }
            };

            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {
                    if (task.getValue() == null)
                        return;

                    Pair<Tile, Tile> move = task.getValue();
                    boardState = game.getBoardState();  // update the boardState
                    for (Observer observer : observers)
                        ((BoardNode) observer).movePieceAnimation(move.getKey().getRow(), move.getKey().getCol(), move.getValue().getRow(), move.getValue().getCol());
                    movements.add(new Movement(move.getKey().getRow(), move.getKey().getCol(), move.getValue().getRow(), move.getValue().getCol()));
                }
            });

            new Thread(task).start();
        } else {
            super.executeQueuedMovement();
        }
    }
}
