package vinnsla.game.pieces;

import vinnsla.game.ChessGame;
import vinnsla.game.MoveCommand;
import vinnsla.util.Colors;
import vinnsla.util.Tile;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, 3);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        return createStandardMoveCommand(tile);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addPotentiallyAvailableLTiles(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }

    private void addPotentiallyAvailableLTiles(Set<Tile> potentiallyAvailableTiles) {
        int[][] adjacentVectors = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, 1}, {-2, -1}, {2, -1}, {2, 1}};
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
}
