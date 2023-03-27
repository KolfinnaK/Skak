package vinnsla.game.pieces;

import vinnsla.game.ChessGame;
import vinnsla.game.MoveCommand;
import vinnsla.util.Colors;
import vinnsla.util.Pair;
import vinnsla.util.Tile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static vinnsla.util.Constants.SIZE_OF_CHESS_BOARD;

public abstract class Piece {
    protected ChessGame game;
    private Tile tile;
    private Colors color;
    private boolean wasMoved;
    private int value;

    public Piece(ChessGame game, Tile tile, Colors color, int value) {
        this.game = game;
        this.tile = tile;
        this.color = color;
        this.value = value;
        wasMoved = false;
    }

    public int getValue() {
        return value;
    }

    public Colors getColor() {
        return color;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public boolean hasBeenMoved() {
        return wasMoved;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }

    public Set<Tile> getAvailableTiles() {
        Set<Tile> availableTiles = new HashSet<>();
        Set<Tile> potentiallyAvailableTiles = getPotentiallyAvailableTiles();
        for (Tile tile : potentiallyAvailableTiles)
            if (!moveWouldPutPlayerInCheck(tile))
                availableTiles.add(tile);
        return availableTiles;
    }

    public boolean moveWouldPutPlayerInCheck(Tile tile) {
        MoveCommand moveCommand = createMoveCommand(tile);
        moveCommand.execute();
        game.updateIsWhiteKingInCheckFlag();
        game.updateIsBlackKingInCheckFlag();
        boolean isPlayerInCheck = false;
        if (game.getCurrentPlayersColor() == Colors.WHITE && game.isBlackKingInCheck() || game.getCurrentPlayersColor() == Colors.BLACK && game.isWhiteKingInCheck())
            isPlayerInCheck = true;
        game.pollLastMoveCommand();
        moveCommand.unexecute();
        game.updateIsWhiteKingInCheckFlag();
        game.updateIsBlackKingInCheckFlag();
        return isPlayerInCheck;
    }

    public MoveCommand createStandardMoveCommand(Tile tile) {
        Set<Pair<Tile, Tile>> movements = new HashSet<>();
        Map<Tile, Piece> captures = new HashMap<>();
        if (game.getPieceAt(tile) != null && game.getPieceAt(tile).getColor() != getColor())
            captures.put(tile, game.getPieceAt(tile));
        movements.add(new Pair<>(getTile(), tile));
        return new MoveCommand(game, movements, captures);
    }

    public void addPotentiallyAvailableDiagonalTiles(Set<Tile> potentiallyAvailableTiles) {
        int[][] diagonalVectors = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] diagonalVector : diagonalVectors) {
            int distanceFromStart = 1;
            while (distanceFromStart < SIZE_OF_CHESS_BOARD) {
                Tile diagonalTile;
                try {
                    diagonalTile = Tile.getTile(getTile().getRow() + diagonalVector[0] * distanceFromStart, getTile().getCol() + diagonalVector[1] * distanceFromStart);
                } catch (Exception e) {
                    break;
                }
                Piece p = game.getPieceAt(diagonalTile);
                if (p == null) {
                    potentiallyAvailableTiles.add(diagonalTile);
                    distanceFromStart++;
                } else {
                    if (p.getColor() != getColor())
                        potentiallyAvailableTiles.add(diagonalTile);
                    break;
                }
            }
        }
    }

    public void addPotentiallyAvailableRectangularTiles(Set<Tile> potentiallyAvailableTiles) {
        int[][] rectangularVectors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] rectangularVector : rectangularVectors) {
            int distanceFromStart = 1;
            while (distanceFromStart < SIZE_OF_CHESS_BOARD) {
                Tile rectangularTile;
                try {
                    rectangularTile = Tile.getTile(getTile().getRow() + rectangularVector[0] * distanceFromStart, getTile().getCol() + rectangularVector[1] * distanceFromStart);
                } catch (Exception e) {
                    break;
                }
                Piece p = game.getPieceAt(rectangularTile);
                if (p == null) {
                    potentiallyAvailableTiles.add(rectangularTile);
                    distanceFromStart++;
                } else {
                    if (p.getColor() != getColor())
                        potentiallyAvailableTiles.add(rectangularTile);
                    break;
                }
            }
        }
    }

    public abstract MoveCommand createMoveCommand(Tile tile);

    public abstract Set<Tile> getPotentiallyAvailableTiles();
}
