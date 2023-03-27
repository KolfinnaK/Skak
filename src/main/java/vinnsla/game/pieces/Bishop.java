package vinnsla.game.pieces;

import vinnsla.game.ChessGame;
import vinnsla.game.MoveCommand;
import vinnsla.util.Colors;
import vinnsla.util.Tile;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, 3);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        return createStandardMoveCommand(tile);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addPotentiallyAvailableDiagonalTiles(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }
}
