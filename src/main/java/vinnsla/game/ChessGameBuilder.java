package vinnsla.game;

import vinnsla.game.pieces.*;
import vinnsla.util.Colors;
import vinnsla.util.Pieces;
import vinnsla.util.Tile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static vinnsla.util.Pieces.*;

public class ChessGameBuilder {
    private Pieces[][] boardState;
    private Map<Tile, Piece> boardMap;
    private List<Piece> activeWhitePieces;
    private List<Piece> activeBlackPieces;

    public ChessGameBuilder(ChessGame game, Pieces[][] board) {
        boardState = board;
        boardMap = new HashMap<>();
        activeWhitePieces = new LinkedList<>();
        activeBlackPieces = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece p = null;
                if (board[i][j] == WHITE_KING)
                    p = new King(game, Tile.getTile(i, j), Colors.WHITE);
                else if (board[i][j] == WHITE_QUEEN)
                    p = new Queen(game, Tile.getTile(i, j), Colors.WHITE);
                else if (board[i][j] == WHITE_ROOK)
                    p = new Rook(game, Tile.getTile(i, j), Colors.WHITE);
                else if (board[i][j] == WHITE_BISHOP)
                    p = new Bishop(game, Tile.getTile(i, j), Colors.WHITE);
                else if (board[i][j] == WHITE_KNIGHT)
                    p = new Knight(game, Tile.getTile(i, j), Colors.WHITE);
                else if (board[i][j] == WHITE_PAWN)
                    p = new Pawn(game, Tile.getTile(i, j), Colors.WHITE);
                else if (board[i][j] == BLACK_KING)
                    p = new King(game, Tile.getTile(i, j), Colors.BLACK);
                else if (board[i][j] == BLACK_QUEEN)
                    p = new Queen(game, Tile.getTile(i, j), Colors.BLACK);
                else if (board[i][j] == BLACK_ROOK)
                    p = new Rook(game, Tile.getTile(i, j), Colors.BLACK);
                else if (board[i][j] == BLACK_BISHOP)
                    p = new Bishop(game, Tile.getTile(i, j), Colors.BLACK);
                else if (board[i][j] == BLACK_KNIGHT)
                    p = new Knight(game, Tile.getTile(i, j), Colors.BLACK);
                else if (board[i][j] == BLACK_PAWN)
                    p = new Pawn(game, Tile.getTile(i, j), Colors.BLACK);

                if (p != null) {
                    boardMap.put(Tile.getTile(i, j), p);
                    if (p.getColor() == Colors.WHITE)
                        activeWhitePieces.add(p);
                    else
                        activeBlackPieces.add(p);
                }
            }
        }
    }

    public ChessGameBuilder(Map<Tile, Piece> board) {
        boardState = new Pieces[8][8];
        activeWhitePieces = new LinkedList<>();
        activeBlackPieces = new LinkedList<>();

        for (Tile tile : board.keySet()) {
            Pieces p = getPieceEnumeration(board.get(tile));
            boardState[tile.getRow()][tile.getCol()] = p;
            if (board.get(tile).getColor() == Colors.WHITE)
                activeWhitePieces.add(board.get(tile));
            else
                activeBlackPieces.add(board.get(tile));
        }
    }

    private Pieces getPieceEnumeration(Piece p) {
        if (p instanceof King)
            return p.getColor() == Colors.WHITE ? Pieces.WHITE_KING : Pieces.BLACK_KING;
        else if (p instanceof Queen)
            return p.getColor() == Colors.WHITE ? Pieces.WHITE_QUEEN : Pieces.BLACK_QUEEN;
        else if (p instanceof Rook)
            return p.getColor() == Colors.WHITE ? Pieces.WHITE_ROOK : Pieces.BLACK_ROOK;
        else if (p instanceof Bishop)
            return p.getColor() == Colors.WHITE ? Pieces.WHITE_BISHOP : Pieces.BLACK_BISHOP;
        else if (p instanceof Knight)
            return p.getColor() == Colors.WHITE ? Pieces.WHITE_KNIGHT : Pieces.BLACK_KNIGHT;
        else if (p instanceof Pawn) {
            Pawn pawn = (Pawn) p;
            if (pawn.wasPromoted())
                return getPieceEnumeration(pawn.getPromotedPiece());
            return p.getColor() == Colors.WHITE ? WHITE_PAWN : BLACK_PAWN;
        }
        throw new IllegalArgumentException("No enumeration exists for the given piece.");
    }

    public Pieces[][] getBoardState() {
        return boardState;
    }

    public Map<Tile, Piece> getBoardMap() {
        return boardMap;
    }

    public List<Piece> getActiveWhitePieces() {
        return activeWhitePieces;
    }

    public List<Piece> getActiveBlackPieces() {
        return activeBlackPieces;
    }
}
