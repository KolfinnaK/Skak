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

public class Pawn extends Piece {
    private int direction;
    private boolean promoted;
    private Piece promotedPiece;

    public Pawn(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, 1);
        direction = color == Colors.WHITE ? -1 : 1;
        promoted = false;
        promotedPiece = null;
    }

    @Override
    public int getValue() {
        if (promoted)
            return promotedPiece.getValue();
        return super.getValue();
    }

    public boolean wasPromoted() {
        return promoted;
    }

    public Piece getPromotedPiece() {
        return promotedPiece;
    }

    public void promote() {
        promoted = true;
        promotedPiece = new Queen(game, getTile(), getColor());
        promotedPiece.setWasMoved(true);
    }

    public void demote() {
        promoted = false;
        promotedPiece = null;
    }

    @Override
    public void setTile(Tile tile) {
        super.setTile(tile);
        if (promoted)
            promotedPiece.setTile(tile);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        if (promoted)
            return promotedPiece.createMoveCommand(tile);
        if (tile.getCol() != getTile().getCol() && game.getPieceAt(tile) == null)
            return createEnPassantMoveCommand(tile);
        return createStandardMoveCommand(tile);
    }

    private MoveCommand createEnPassantMoveCommand(Tile tile) {
        Set<Pair<Tile, Tile>> movements = new HashSet<>();
        Map<Tile, Piece> captures = new HashMap<>();
        movements.add(new Pair<>(getTile(), tile));
        Tile capturedTile = Tile.getTile(getTile().getRow(), tile.getCol());
        captures.put(capturedTile, game.getPieceAt(capturedTile));
        return new MoveCommand(game, movements, captures);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        if (promoted)
            return promotedPiece.getPotentiallyAvailableTiles();
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addOneTileForward(potentiallyAvailableTiles);
        addTwoTilesForward(potentiallyAvailableTiles);
        addDiagonalCaptures(potentiallyAvailableTiles);
        addEnPassant(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }

    private void addOneTileForward(Set<Tile> potentiallyAvailableTiles) {
        Tile oneTileForward;
        try {
            oneTileForward = Tile.getTile(getTile().getRow() + direction, getTile().getCol());
        } catch (Exception e) {
            return;
        }

        Piece p = game.getPieceAt(oneTileForward);
        if (p == null)
            potentiallyAvailableTiles.add(oneTileForward);
    }

    private void addTwoTilesForward(Set<Tile> potentiallyAvailableTiles) {
        Tile oneTileForward, twoTilesForward;
        try {
            oneTileForward = Tile.getTile(getTile().getRow() + direction, getTile().getCol());
            twoTilesForward = Tile.getTile(getTile().getRow() + direction * 2, getTile().getCol());
        } catch (Exception e) {
            return;
        }

        Piece p1 = game.getPieceAt(oneTileForward), p2 = game.getPieceAt(twoTilesForward);
        if (p1 == null && p2 == null && !hasBeenMoved())
            potentiallyAvailableTiles.add(twoTilesForward);
    }

    private void addDiagonalCaptures(Set<Tile> potentiallyAvailableTiles) {
        Tile leftDiagonal, rightDiagonal;
        try {
            leftDiagonal = Tile.getTile(getTile().getRow() + direction, getTile().getCol() - 1);

            if (game.getPieceAt(leftDiagonal).getColor() != getColor())
                potentiallyAvailableTiles.add(leftDiagonal);
        } catch (Exception e) {}

        try {
            rightDiagonal = Tile.getTile(getTile().getRow() + direction, getTile().getCol() + 1);

            if (game.getPieceAt(rightDiagonal).getColor() != getColor())
                potentiallyAvailableTiles.add(rightDiagonal);
        } catch (Exception e) {}
    }

    private void addEnPassant(Set<Tile> potentiallyAvailableTiles) {
        try {
            Tile leftDiagonal = Tile.getTile(getTile().getRow() + direction, getTile().getCol() - 1);
            Tile leftAdjacentTile = Tile.getTile(getTile().getRow(), getTile().getCol() - 1);

            if (game.getPieceAt(leftDiagonal) == null && game.getPieceAt(leftAdjacentTile) instanceof Pawn && game.movedFirstTimeLastTurn(leftAdjacentTile))
                potentiallyAvailableTiles.add(leftDiagonal);
        } catch (Exception e) {}

        try {
            Tile rightDiagonal = Tile.getTile(getTile().getRow() + direction, getTile().getCol() + 1);
            Tile rightAdjacentTile = Tile.getTile(getTile().getRow(), getTile().getCol() + 1);

            if (game.getPieceAt(rightDiagonal) == null && game.getPieceAt(rightAdjacentTile) instanceof Pawn && game.movedFirstTimeLastTurn(rightAdjacentTile))
                potentiallyAvailableTiles.add(rightDiagonal);
        } catch (Exception e) {}
    }
}
