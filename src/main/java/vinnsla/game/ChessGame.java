package vinnsla.game;

import com.chess.model.game.pieces.*;
import com.chess.model.util.Colors;
import com.chess.model.util.Pair;
import com.chess.model.util.Pieces;
import com.chess.model.util.Tile;

import java.util.*;

import static com.chess.model.util.Constants.*;
import static com.chess.model.util.Pieces.*;
import static com.chess.model.util.Pieces.BLACK_KNIGHT;

public class ChessGame implements ChessGameInterface {
    private int materialHeuristic, positionalHeuristic;
    private Colors currentPlayersColor;
    private List<Piece> activeWhitePieces, activeBlackPieces, capturedWhitePieces, capturedBlackPieces;
    private Map<Tile, Piece> board;
    private ArrayDeque<MoveCommand> playedMoves;
    private Map<Piece, Set<Tile>> availableTilesCache;
    private boolean isBlackKingInCheckFlag, isWhiteKingInCheckFlag, currentPlayerHasMovesFlag;
    private Map<Pieces, int[][]> pieceTables;

    private int[][] PAWN_PIECE_SQUARE =        {{0,     0,      0,      0,      0,      0,      0,      0},
            {50,    50,     50,     50,     50,     50,     50,     50},
            {10,    10,     20,     30,     30,     20,     10,     10},
            {5,     5,      10,     25,     25,     10,     5,      5},
            {0,     0,      0,      20,     20,     0,      0,      0},
            {5,     -5,     -10,    0,      0,      -10,    -5,     5},
            {5,     10,     10,     -20,    -20,    10,     10,     5},
            {0,     0,      0,      0,      0,      0,      0,      0}};

    private int[][] KNIGHT_PIECE_SQUARE =      {{-50,   -40,    -30,    -30,    -30,    -30,    -40,    -50},
            {-40,   -20,    0,      0,      0,      0,      -20,    -40},
            {-30,   0,      10,     15,     15,     10,     0,      -30},
            {-30,   5,      15,     20,     20,     15,     5,      -30},
            {-30,   0,      15,     20,     20,     15,     0,      -30},
            {-30,   5,      10,     15,     15,     10,     5,      -30},
            {-40,   -20,    0,      5,      5,      0,      -20,    -40},
            {-50,   -40,    -30,    -30,    -30,    -30,    -40,    -50}};

    private int[][] BISHOP_PIECE_SQUARE =      {{-20,   -10,    -10,    -10,    -10,    -10,    -10,    -20},
            {-10,   0,      0,      0,      0,      0,      0,      -10},
            {-10,   0,      5,      10,     10,     5,      0,      -10},
            {-10,   5,      5,      10,     10,     5,      5,      -10},
            {-10,   0,      10,     10,     10,     10,     0,      -10},
            {-10,   10,     10,     10,     10,     10,     10,     -10},
            {-10,   5,      0,      0,      0,      0,      5,      -10},
            {-20,   -10,    -10,    -10,    -10,    -10,    -10,    -20}};

    private int[][] ROOK_PIECE_SQUARE =        {{0,     0,      0,      0,      0,      0,      0,      0},
            {5,     10,     10,     10,     10,     10,     10,     5},
            {-5,    0,      0,      0,      0,      0,      0,      -5},
            {-5,    0,      0,      0,      0,      0,      0,      -5},
            {-5,    0,      0,      0,      0,      0,      0,      -5},
            {-5,    0,      0,      0,      0,      0,      0,      -5},
            {-5,    0,      0,      0,      0,      0,      0,      -5},
            {0,     0,      0,      5,      5,      0,      0,      0}};

    private int[][] QUEEN_PIECE_SQUARE =        {{-20,  -10,    -10,    -5,     -5,     -10,    -10,    -20},
            {-10,  0,      0,      0,      0,      0,      0,      -10},
            {-10,  0,      5,      5,      5,      5,      0,      -10},
            {-5,   0,      5,      5,      5,      5,      0,      -5},
            {0,    0,      5,      5,      5,      5,      0,      -5},
            {-10,  5,      5,      5,      5,      5,      0,      -10},
            {-10,  0,      5,      0,      0,      0,      0,      -10},
            {-20,  -10,    -10,    -5,     -5,     -10,    -10,    -20}};

    public ChessGame(Colors color, Pieces[][] board) {
        setUpState(color, board);
        updateIsWhiteKingInCheckFlag();
        updateIsBlackKingInCheckFlag();
        updateAvailableTilesCache();
        updateCurrentPlayerHasMovesFlag();
    }

    public ChessGame(Pieces[][] board) {
        this(Colors.WHITE, board);
    }

    public ChessGame(Colors color) {
        this(color, initialState);
    }

    public ChessGame() {
        this(Colors.WHITE, initialState);
    }

    private void setUpState(Colors color, Pieces[][] board) {
        currentPlayersColor = color;
        ChessGameBuilder builder = new ChessGameBuilder(this, board);
        activeWhitePieces = builder.getActiveWhitePieces();
        activeBlackPieces = builder.getActiveBlackPieces();
        capturedWhitePieces = new LinkedList<>();
        capturedBlackPieces = new LinkedList<>();
        this.board = builder.getBoardMap();
        playedMoves = new ArrayDeque<>();
        setUpPieceTablesMap();
        initializeMaterialHeuristic();
        initializePositionalHeuristic();
    }

    private void initializePositionalHeuristic() {
        positionalHeuristic = 0;
        Pieces[][] boardState = getBoardState();
        for (int i = 0; i < boardState.length; i++)
            for (int j = 0; j < boardState.length; j++)
                positionalHeuristic += getPositionalValue(i, j, boardState[i][j]);
    }

    private int getPositionalValue(int row, int col, Pieces piece) {
        if (piece == null || piece == WHITE_KING || piece == BLACK_KING)
            return 0;

        return pieceTables.get(piece)[row][col];
    }

    private void setUpPieceTablesMap() {
        pieceTables = new HashMap<>();

        pieceTables.put(WHITE_QUEEN, QUEEN_PIECE_SQUARE);
        pieceTables.put(BLACK_QUEEN, reverse(QUEEN_PIECE_SQUARE));

        pieceTables.put(WHITE_ROOK, ROOK_PIECE_SQUARE);
        pieceTables.put(BLACK_ROOK, reverse(ROOK_PIECE_SQUARE));

        pieceTables.put(WHITE_BISHOP, BISHOP_PIECE_SQUARE);
        pieceTables.put(BLACK_BISHOP, reverse(BISHOP_PIECE_SQUARE));

        pieceTables.put(WHITE_KNIGHT, KNIGHT_PIECE_SQUARE);
        pieceTables.put(BLACK_KNIGHT, reverse(KNIGHT_PIECE_SQUARE));

        pieceTables.put(WHITE_PAWN, PAWN_PIECE_SQUARE);
        pieceTables.put(BLACK_PAWN, reverse(PAWN_PIECE_SQUARE));
    }

    private int[][] reverse(int[][] table) {
        int[][] reversed = new int[table.length][table[0].length];
        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table.length; j++)
                reversed[i][j] = -table[table.length - 1 - i][j];
        return reversed;
    }

    private void initializeMaterialHeuristic() {
        materialHeuristic = 0;
        for (Pieces piece : convertListToEnumeration(activeWhitePieces))
            materialHeuristic += getPieceValue(piece);
        for (Pieces piece : convertListToEnumeration(activeBlackPieces))
            materialHeuristic -= getPieceValue(piece);
    }

    private int getPieceValue(Pieces piece) {
        if (piece == null)
            throw new IllegalArgumentException("Piece cannot be null if we want to get it's value.");

        if (piece == WHITE_KING || piece == BLACK_KING)
            return 20000;
        else if (piece == WHITE_QUEEN || piece == BLACK_QUEEN)
            return 900;
        else if (piece == WHITE_ROOK || piece == BLACK_ROOK)
            return 500;
        else if (piece == WHITE_BISHOP || piece == BLACK_BISHOP)
            return 330;
        else if (piece == WHITE_KNIGHT || piece == BLACK_KNIGHT)
            return 320;
        else
            return 100;
    }

    @Override
    public boolean doesTileContainPieceOfCurrentPlayersColor(int row, int col) {
        Piece piece = board.getOrDefault(Tile.getTile(row, col), null);
        return piece != null && piece.getColor() == currentPlayersColor;
    }


    @Override
    public Set<Tile> getAvailableMovesForTile(int row, int col) {
        if (isGameInStalemate() || isCurrentPlayerInCheckmate())
            throw new IllegalArgumentException("Game is over!");
        else if (!doesTileContainPieceOfCurrentPlayersColor(row, col))
            throw new IllegalArgumentException("Specified tile does not contain a piece of the current player's color.");

        return availableTilesCache.get(board.get(Tile.getTile(row, col)));
    }

    @Override
    public void move(int startRow, int startCol, int endRow, int endCol) {
        Tile startingTile = Tile.getTile(startRow, startCol), endingTile = Tile.getTile(endRow, endCol);
        isMovementLegal(startingTile, endingTile);
        resolveMovement(startingTile, endingTile);
        updateIsWhiteKingInCheckFlag();
        updateIsBlackKingInCheckFlag();
        updateAvailableTilesCache();
        updateCurrentPlayerHasMovesFlag();
    }

    private boolean isMovementLegal(Tile startingTile, Tile endingTile) {
        if (!doesTileContainPieceOfCurrentPlayersColor(startingTile.getRow(), startingTile.getCol()))
            throw new IllegalArgumentException("Starting tile does not contain a piece of the current player's color.");
        Piece p = board.get(startingTile);
        if (!availableTilesCache.get(p).contains(endingTile))
            throw new IllegalArgumentException("Ending tile is not available to be moved to.");
        if (isCurrentPlayerInCheckmate() || isGameInStalemate())
            throw new IllegalArgumentException("Cannot move because game is over.");
        return true;
    }

    private void resolveMovement(Tile startingTile, Tile endingTile) {
        MoveCommand moveCommand = board.get(startingTile).createMoveCommand(endingTile);
        moveCommand.execute();
    }

    @Override
    public void undoLastMove() {
        if (playedMoves.size() == 0)
            throw new IllegalArgumentException("No previous moves have been played!");

        MoveCommand moveCommand = playedMoves.pollLast();
        moveCommand.unexecute();

        updateIsWhiteKingInCheckFlag();
        updateIsBlackKingInCheckFlag();
        updateAvailableTilesCache();
        updateCurrentPlayerHasMovesFlag();
    }

    @Override
    public boolean isCurrentPlayerInCheck() {
        return currentPlayersColor == Colors.BLACK ? isBlackKingInCheckFlag : isWhiteKingInCheckFlag;
    }

    @Override
    public boolean isCurrentPlayerInCheckmate() {
        return isCurrentPlayerInCheck() && !currentPlayerHasMovesFlag;
    }

    @Override
    public boolean isGameInStalemate() {
        return !isCurrentPlayerInCheck() && !currentPlayerHasMovesFlag;
    }

    @Override
    public Pieces[][] getBoardState() {
        ChessGameBuilder builder = new ChessGameBuilder(board);
        return builder.getBoardState();
    }

    @Override
    public List<Pieces> getActiveWhitePieces() {
        return convertListToEnumeration(activeWhitePieces);
    }

    @Override
    public List<Pieces> getActiveBlackPieces() {
        return convertListToEnumeration(activeBlackPieces);
    }

    @Override
    public List<Pieces> getCapturedWhitePieces() {
        return convertListToEnumeration(capturedWhitePieces);
    }

    @Override
    public List<Pieces> getCapturedBlackPieces() {
        return convertListToEnumeration(capturedBlackPieces);
    }

    @Override
    public Colors getCurrentPlayerColor() {
        return currentPlayersColor;
    }

    @Override
    public Set<Pair<Tile, Tile>> getAvailableMovesForCurrentPlayer() {
        Set<Pair<Tile, Tile>> availableMovesForCurrentPlayer = new TreeSet<>(new Comparator<Pair<Tile, Tile>>() {
            @Override
            public int compare(Pair<Tile, Tile> o1, Pair<Tile, Tile> o2) {
                int move1CapturedValue = getPieceAt(o1.getValue()) == null ? 0 : getPieceAt(o1.getValue()).getValue();
                int move2CapturedValue = getPieceAt(o2.getValue()) == null ? 0 : getPieceAt(o2.getValue()).getValue();
                int row1 = o1.getValue().getRow(), col1 = o1.getValue().getCol();
                int row2 = o2.getValue().getRow(), col2 = o2.getValue().getCol();
                return row1 * 8 + col1 + row2 * 8 + col2 + 100 * (move1CapturedValue - move2CapturedValue);
            }
        });
        for (Piece piece : availableTilesCache.keySet())
            for (Tile tile : availableTilesCache.get(piece))
                availableMovesForCurrentPlayer.add(new Pair<>(piece.getTile(), tile));
        return availableMovesForCurrentPlayer;
    }

    @Override
    public int getMaterialHeuristic() {
        return materialHeuristic;
    }

    @Override
    public int getPositionalHeuristic() {
        return positionalHeuristic;
    }

    @Override
    public String getAlgebraicNotationForMove(Tile start, Tile end) {
        // throw an exception if the move is invalid
        if (!availableTilesCache.containsKey(getPieceAt(start)))
            throw new IllegalArgumentException("Move is invalid.");

        String notation = "";
        // see if the move is "ambiguous"
        // make a mapping of all the pieces that can land on a space
        int occurrences = 0;
        for (Piece key : availableTilesCache.keySet())
            for (Tile value : availableTilesCache.get(key))
                if (value == end)
                    occurrences++;
        if (occurrences > 1)
            notation += getPieceNotation(getPieceAt(start));

        // see if the move will put the other player in check
        move(start.getRow(), start.getCol(), end.getRow(), end.getCol());
        boolean putsItInCheck = !isCurrentPlayerInCheckmate() && isCurrentPlayerInCheck();
        undoLastMove();

        // check for promotion
        boolean wasPromoted = getPieceAt(start) instanceof Pawn && !((Pawn) getPieceAt(start)).wasPromoted() && (end.getRow() == 0 || end.getRow() == 7);

        // check for castling
        if (getPieceAt(start) instanceof King && Math.abs(start.getCol() - end.getCol()) > 1) {
            if (end.getCol() > start.getCol())
                return "0-0";
            return "0-0-0";
        }

        // check for checkmate
        move(start.getRow(), start.getCol(), end.getRow(), end.getCol());
        boolean putsItInCheckmate = isCurrentPlayerInCheckmate();
        undoLastMove();

        // check to see if there was a capture
        if (getPieceAt(start) instanceof Pawn && !((Pawn) getPieceAt(start)).wasPromoted() && getPieceAt(end) != null)
            notation += getFile(start);
        if (getPieceAt(end) != null)
            notation += "x";

        notation += getFile(end);
        notation += getRank(end);

        if (wasPromoted)
            notation += "Q";
        if (putsItInCheck)
            notation += "+";
        if (putsItInCheckmate)
            notation += "X";
        return notation;
    }

    private char getFile(Tile tile) {
        return (char) (tile.getCol() + 'a');
    }

    private char getRank(Tile tile) {
        return (char) (8 - tile.getRow() + '0');
    }

    private String getPieceNotation(Piece pieceAt) {
        if (pieceAt instanceof King)
            return "K";
        else if (pieceAt instanceof Queen)
            return "Q";
        else if (pieceAt instanceof Rook)
            return "R";
        else if (pieceAt instanceof Bishop)
            return "B";
        else if (pieceAt instanceof Knight)
            return "N";
        else if (pieceAt instanceof Pawn && !((Pawn) pieceAt).wasPromoted())
            return "P";
        else if (pieceAt instanceof Pawn)
            return getPieceNotation(((Pawn) pieceAt).getPromotedPiece());
        throw new IllegalArgumentException("Piece does not exist.");
    }

    private List<Pieces> convertListToEnumeration(List<Piece> pieces) {
        List<Pieces> converted = new LinkedList<>();
        for (Piece p : pieces)
            converted.add(getPieceEnumeration(p));
        return converted;
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
            return p.getColor() == Colors.WHITE ? Pieces.WHITE_PAWN : Pieces.BLACK_PAWN;
        }
        throw new IllegalArgumentException("No enumeration exists for the given piece.");
    }

    private void updateAvailableTilesCache() {
        Map<Piece, Set<Tile>> updatedAvailableTilesCache = new HashMap<>();
        for (Piece p : currentPlayersColor == Colors.WHITE ? activeWhitePieces : activeBlackPieces)
            updatedAvailableTilesCache.put(p, p.getAvailableTiles());
        availableTilesCache = updatedAvailableTilesCache;
    }

    private void updateFlags() {
        updateIsWhiteKingInCheckFlag();
        updateIsBlackKingInCheckFlag();
        updateCurrentPlayerHasMovesFlag();
    }

    public void updateIsWhiteKingInCheckFlag() {
        for (Piece p : activeWhitePieces)
            if (p instanceof King) {
                isWhiteKingInCheckFlag = isKingInCheck(p.getTile());
                return;
            }
    }

    public void updateIsBlackKingInCheckFlag() {
        for (Piece p : activeBlackPieces)
            if (p instanceof King) {
                isBlackKingInCheckFlag = isKingInCheck(p.getTile());
                return;
            }
    }

    private void updateCurrentPlayerHasMovesFlag() {
        currentPlayerHasMovesFlag = true;
        for (Set<Tile> tiles : availableTilesCache.values())
            if (tiles.size() != 0)
                return;
        currentPlayerHasMovesFlag = false;
    }

    private boolean isKingInCheck(Tile tile) {
        return adjacentTilesContainKingOfOppositeColor(tile) || diagonalTilesContainQueenOrBishopOfOppositeColor(tile) ||
                rectangleTilesContainQueenOrRookOfOppositeColor(tile) || tilesInLContainKnightOfOppositeColor(tile) ||
                diagonalTilesContainPawnOfOppositeColor(tile);
    }

    private boolean adjacentTilesContainKingOfOppositeColor(Tile tile) {
        Piece pieceUnderAttack = board.get(tile);
        int[][] adjacentVectors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] adjacentVector : adjacentVectors) {
            Tile adjacentTile;
            // if the row and column are out of range, an exception is thrown, and we move on to the next vector
            try {
                adjacentTile = Tile.getTile(tile.getRow() + adjacentVector[0], tile.getCol() + adjacentVector[1]);
            } catch (Exception e) {
                continue;
            }
            Piece piece = board.getOrDefault(adjacentTile, null);
            if (isPieceKingOfOppositeColor(piece, pieceUnderAttack.getColor()))
                return true;
        }
        return false;
    }

    private boolean diagonalTilesContainQueenOrBishopOfOppositeColor(Tile tile) {
        Piece pieceUnderAttack = board.get(tile);
        int[][] diagonalVectors = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] diagonalVector : diagonalVectors) {
            int tilesAwayFromStart = 1;
            while (tilesAwayFromStart < SIZE_OF_CHESS_BOARD) {
                Tile diagonalTile;
                // if the row and column are out of range, an exception is thrown, and we move on to the next vector
                try {
                    diagonalTile = Tile.getTile(tile.getRow() + diagonalVector[0] * tilesAwayFromStart, tile.getCol() + diagonalVector[1] * tilesAwayFromStart);
                } catch (Exception e) {
                    break;
                }
                Piece piece = board.getOrDefault(diagonalTile, null);
                if (isPieceQueenOfOppositeColor(piece, pieceUnderAttack.getColor()) || isPieceBishopOfOppositeColor(piece, pieceUnderAttack.getColor()))
                    return true;
                else if (piece != null)
                    break;
                tilesAwayFromStart++;
            }
        }
        return false;
    }

    private boolean rectangleTilesContainQueenOrRookOfOppositeColor(Tile tile) {
        Piece pieceUnderAttack = board.get(tile);
        int[][] rectangularVectors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] rectangularVector : rectangularVectors) {
            int tilesAwayFromStart = 1;
            while (tilesAwayFromStart < SIZE_OF_CHESS_BOARD) {
                Tile rectangularTile;
                // if the row and column are out of range, an exception is thrown, and we move on to the next vector
                try {
                    rectangularTile = Tile.getTile(tile.getRow() + rectangularVector[0] * tilesAwayFromStart, tile.getCol() + rectangularVector[1] * tilesAwayFromStart);
                } catch (Exception e) {
                    break;
                }
                Piece piece = board.getOrDefault(rectangularTile, null);
                if (isPieceQueenOfOppositeColor(piece, pieceUnderAttack.getColor()) || isPieceRookOfOppositeColor(piece, pieceUnderAttack.getColor()))
                    return true;
                else if (piece != null)
                    break;
                tilesAwayFromStart++;
            }
        }
        return false;
    }

    private boolean tilesInLContainKnightOfOppositeColor(Tile tile) {
        Piece pieceUnderAttack = board.get(tile);
        int[][] lShapeVectors = {{-1, 2}, {-1, -2}, {1, 2}, {1, -2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
        for (int[] lShapeVector : lShapeVectors) {
            Tile adjacentTile;
            // if the row and column are out of range, an exception is thrown, and we move on to the next vector
            try {
                adjacentTile = Tile.getTile(tile.getRow() + lShapeVector[0], tile.getCol() + lShapeVector[1]);
            } catch (Exception e) {
                continue;
            }
            Piece piece = board.getOrDefault(adjacentTile, null);
            if (isPieceKnightOfOppositeColor(piece, pieceUnderAttack.getColor()))
                return true;
        }
        return false;
    }

    private boolean diagonalTilesContainPawnOfOppositeColor(Tile tile) {
        Piece pieceUnderAttack = board.get(tile);
        int[][] diagonalVectors = pieceUnderAttack.getColor() == Colors.WHITE ? new int[][] {{-1, -1}, {-1, 1}} : new int[][] {{1, -1}, {1, 1}};

        for (int[] diagonalVector : diagonalVectors) {
            Tile diagonalTile;
            // if the row and column are out of range, an exception is thrown, and we move on to the next vector
            try {
                diagonalTile = Tile.getTile(tile.getRow() + diagonalVector[0], tile.getCol() + diagonalVector[1]);
            } catch (Exception e) {
                continue;
            }
            Piece piece = board.getOrDefault(diagonalTile, null);
            if (isPiecePawnOfOppositeColor(piece, pieceUnderAttack.getColor()))
                return true;
        }
        return false;
    }

    private boolean isPieceKingOfOppositeColor(Piece piece, Colors color) {
        return piece != null && piece instanceof King && piece.getColor() != color;
    }

    private boolean isPieceQueenOfOppositeColor(Piece piece, Colors color) {
        return piece != null && piece.getColor() != color && (piece instanceof Queen || piece instanceof Pawn && ((Pawn) piece).getPromotedPiece() instanceof Queen);
    }

    private boolean isPieceRookOfOppositeColor(Piece piece, Colors color) {
        return piece != null && piece.getColor() != color && (piece instanceof Rook || piece instanceof Pawn && ((Pawn) piece).getPromotedPiece() instanceof Rook);
    }

    private boolean isPieceBishopOfOppositeColor(Piece piece, Colors color) {
        return piece != null && piece.getColor() != color && (piece instanceof Bishop || piece instanceof Pawn && ((Pawn) piece).getPromotedPiece() instanceof Bishop);
    }

    private boolean isPieceKnightOfOppositeColor(Piece piece, Colors color) {
        return piece != null && piece.getColor() != color && (piece instanceof Knight || piece instanceof Pawn && ((Pawn) piece).getPromotedPiece() instanceof Knight);
    }

    private boolean isPiecePawnOfOppositeColor(Piece piece, Colors color) {
        return piece != null && piece.getColor() != color && piece instanceof Pawn && !((Pawn) piece).wasPromoted();
    }

    public void executeCapture(Tile tile) {
        Piece piece = getPieceAt(tile);
        if (piece.getColor() == Colors.WHITE) {
            activeWhitePieces.remove(piece);
            capturedWhitePieces.add(piece);
            materialHeuristic -= getPieceValue(getPieceEnumeration(piece));
        } else {
            activeBlackPieces.remove(piece);
            capturedBlackPieces.add(piece);
            materialHeuristic += getPieceValue(getPieceEnumeration(piece));
        }
        positionalHeuristic -= getPositionalValue(tile.getRow(), tile.getCol(), getPieceEnumeration(piece));
        board.remove(tile);
        piece.setTile(null);
    }

    public void executeMovement(Pair<Tile, Tile> movement) {
        Piece p = getPieceAt(movement.getKey());
        board.remove(movement.getKey());
        if (board.containsKey(movement.getValue()))
            throw new IllegalArgumentException("Cannot move piece to an occupied tile.");
        board.put(movement.getValue(), p);
        p.setTile(movement.getValue());
        positionalHeuristic -= getPositionalValue(movement.getKey().getRow(), movement.getKey().getCol(), getPieceEnumeration(p));
        positionalHeuristic += getPositionalValue(movement.getValue().getRow(), movement.getValue().getCol(), getPieceEnumeration(p));
    }

    public void unexecuteCapture(Tile tile, Piece piece) {
        if (board.containsKey(tile))
            throw new IllegalArgumentException("Cannot reverse capture if a piece is in the captured piece's spot.");
        board.put(tile, piece);
        piece.setTile(tile);
        if (piece.getColor() == Colors.WHITE) {
            capturedWhitePieces.remove(piece);
            activeWhitePieces.add(piece);
            materialHeuristic += getPieceValue(getPieceEnumeration(piece));
        } else {
            capturedBlackPieces.remove(piece);
            activeBlackPieces.add(piece);
            materialHeuristic -= getPieceValue(getPieceEnumeration(piece));
        }
        positionalHeuristic += getPositionalValue(tile.getRow(), tile.getCol(), getPieceEnumeration(piece));
    }

    public void switchCurrentPlayerColor() {
        if (currentPlayersColor == Colors.WHITE)
            currentPlayersColor = Colors.BLACK;
        else
            currentPlayersColor = Colors.WHITE;
    }

    public void addMoveCommandToPlayedMoves(MoveCommand moveCommand) {
        playedMoves.add(moveCommand);
    }

    public boolean isWhiteKingInCheck() {
        return isWhiteKingInCheckFlag;
    }

    public boolean isBlackKingInCheck() {
        return isBlackKingInCheckFlag;
    }

    public Colors getCurrentPlayersColor() {
        return currentPlayersColor;
    }

    public Piece getPieceAt(Tile tile) {
        return board.get(tile);
    }

    public void pollLastMoveCommand() {
        playedMoves.pollLast();
    }

    public boolean movedFirstTimeLastTurn(Tile tile) {
        return playedMoves.getLast().pieceWasMovedFirstTime(tile);
    }
}
