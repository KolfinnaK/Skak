package vinnsla.game.pieces;

import com.chess.model.game.ChessGame;
import com.chess.model.game.MoveCommand;
import com.chess.model.util.Colors;
import com.chess.model.util.Pair;
import com.chess.model.util.Tile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class King extends Piece {
    public King(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, Integer.MAX_VALUE);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        Tile startingTile = super.getTile();
        if (Math.abs(startingTile.getRow() - tile.getRow()) <= 1 && Math.abs(startingTile.getCol() - tile.getCol()) <= 1)
            return createStandardMoveCommand(tile);
        return createMoveCommandForCastle(tile);
    }

    private MoveCommand createMoveCommandForCastle(Tile tile) {
        Set<Pair<Tile, Tile>> movements = new HashSet<>();
        Map<Tile, Piece> captures = new HashMap<>();
        movements.add(new Pair<>(getTile(), tile));
        if (tile.getRow() == 7 && tile.getCol() == 6)
            movements.add(new Pair<>(Tile.getTile(7, 7), Tile.getTile(7, 5)));
        else if (tile.getRow() == 7 && tile.getCol() == 2)
            movements.add(new Pair<>(Tile.getTile(7, 0), Tile.getTile(7, 3)));
        else if (tile.getRow() == 0 && tile.getCol() == 6)
            movements.add(new Pair<>(Tile.getTile(0, 7), Tile.getTile(0, 5)));
        else if (tile.getRow() == 0 && tile.getCol() == 2)
            movements.add(new Pair<>(Tile.getTile(0, 0), Tile.getTile(0, 3)));
        return new MoveCommand(game, movements, captures);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addPotentiallyAvailableAdjacentTiles(potentiallyAvailableTiles);
        addPotentiallyAvailableCastleTiles(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }

    private void addPotentiallyAvailableAdjacentTiles(Set<Tile> potentiallyAvailableTiles) {
        int[][] adjacentVectors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] adjacentVector : adjacentVectors) {
            Tile adjacentTile;
            try {
                adjacentTile = Tile.getTile(getTile().getRow() + adjacentVector[0], getTile().getCol() + adjacentVector[1]);
            } catch (Exception e) {
                continue;
            }
            Piece p = game.getPieceAt(adjacentTile);
            if (p == null || p.getColor() != getColor())
                potentiallyAvailableTiles.add(adjacentTile);
        }
    }

    private void addPotentiallyAvailableCastleTiles(Set<Tile> potentiallyAvailableTiles) {
        if (hasBeenMoved() || getColor() == Colors.WHITE && game.isWhiteKingInCheck() || getColor() == Colors.BLACK && game.isBlackKingInCheck())
            return;

        Piece rightPiece = game.getPieceAt(Tile.getTile(getTile().getRow(), 7));
        if (rightPiece != null && !rightPiece.hasBeenMoved() && rightTilesAreEmpty())
            potentiallyAvailableTiles.add(Tile.getTile(getTile().getRow(), getTile().getCol() + 2));

        Piece leftPiece = game.getPieceAt(Tile.getTile(getTile().getRow(), 0));
        if (leftPiece != null && !leftPiece.hasBeenMoved() && leftTilesAreEmpty())
            potentiallyAvailableTiles.add(Tile.getTile(getTile().getRow(), getTile().getCol() - 2));
    }

    private boolean rightTilesAreEmpty() {
        try {
            return game.getPieceAt(Tile.getTile(getTile().getRow(), getTile().getCol() + 1)) == null &&
                    game.getPieceAt(Tile.getTile(getTile().getRow(), getTile().getCol() + 2)) == null;
        } catch (Exception e) {}
        return false;
    }

    private boolean leftTilesAreEmpty() {
        try {
            return game.getPieceAt(Tile.getTile(getTile().getRow(), getTile().getCol() - 1)) == null &&
                    game.getPieceAt(Tile.getTile(getTile().getRow(), getTile().getCol() - 2)) == null &&
                    game.getPieceAt(Tile.getTile(getTile().getRow(), getTile().getCol() - 3)) == null;
        } catch (Exception e) {}
        return false;
    }
}
