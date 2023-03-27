package vinnsla.game.pieces;

import vinnsla.game.ChessGame;
import vinnsla.game.MoveCommand;
import vinnsla.util.Colors;
import vinnsla.util.Tile;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, 9);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        return createStandardMoveCommand(tile);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addPotentiallyAvailableRectangularTiles(potentiallyAvailableTiles);
        addPotentiallyAvailableDiagonalTiles(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }
}
