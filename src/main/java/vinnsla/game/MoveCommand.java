package vinnsla.game;

import vinnsla.game.pieces.Pawn;
import vinnsla.game.pieces.Piece;
import vinnsla.util.Colors;
import vinnsla.util.Pair;
import vinnsla.util.Tile;

import java.util.Map;
import java.util.Set;

public class MoveCommand {
    private ChessGame game;
    private Set<Pair<Tile, Tile>> movements;
    private Map<Tile, Piece> captures;
    private boolean firstTimeBeingMoved, promotion;

    public MoveCommand(ChessGame game, Set<Pair<Tile, Tile>> movements, Map<Tile, Piece> captures) {
        this.game = game;
        this.movements = movements;
        this.captures = captures;
        firstTimeBeingMoved = isFirstTimeBeingMoved(movements);
        promotion = endsInPromotion(movements);
    }

    public boolean isFirstTimeBeingMoved(Set<Pair<Tile, Tile>> movements) {
        for (Pair<Tile, Tile> movement : movements)
            if (!game.getPieceAt(movement.getKey()).hasBeenMoved())
                return true;
        return false;
    }

    public boolean endsInPromotion(Set<Pair<Tile, Tile>> movements) {
        for (Pair<Tile, Tile> movement : movements) {
            Piece p = game.getPieceAt(movement.getKey());
            int row = movement.getValue().getRow();
            if (p instanceof Pawn && !((Pawn) p).wasPromoted() && (p.getColor() == Colors.BLACK && row == 7 || p.getColor() == Colors.WHITE && row == 0))
                return true;
        }
        return false;
    }

    public void execute() {
        executeCaptures();
        executeMovements();
        game.switchCurrentPlayerColor();
        game.addMoveCommandToPlayedMoves(this);
    }

    public void executeCaptures() {
        for (Tile tile : captures.keySet())
            game.executeCapture(tile);
    }

    public void executeMovements() {
        for (Pair<Tile, Tile> movement : movements) {
            game.executeMovement(movement);
            game.getPieceAt(movement.getValue()).setWasMoved(true);
            if (promotion) {
                Pawn pawn = (Pawn) game.getPieceAt(movement.getValue());
                pawn.promote();
            }
        }
    }

    public void unexecute() {
        unexecuteMovements();
        unexecuteCaptures();
        game.switchCurrentPlayerColor();
    }

    public void unexecuteMovements() {
        for (Pair<Tile, Tile> movement : movements) {
            game.executeMovement(new Pair<>(movement.getValue(), movement.getKey()));
            if (firstTimeBeingMoved)
                game.getPieceAt(movement.getKey()).setWasMoved(false);
            if (promotion) {
                Pawn pawn = (Pawn) game.getPieceAt(movement.getKey());
                pawn.demote();
            }
        }
    }

    public void unexecuteCaptures() {
        for (Tile tile : captures.keySet())
            game.unexecuteCapture(tile, captures.get(tile));
    }

    public boolean pieceWasMovedFirstTime(Tile tile) {
        if (!firstTimeBeingMoved)
            return false;

        for (Pair<Tile, Tile> movement : movements)
            if (movement.getValue() == tile)
                return true;

        return false;
    }
}
