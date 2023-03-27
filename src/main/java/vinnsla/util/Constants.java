package vinnsla.util;

public class Constants {
    public static final Pieces WK = Pieces.WHITE_KING, WQ = Pieces.WHITE_QUEEN, WR = Pieces.WHITE_ROOK, WB = Pieces.WHITE_BISHOP,
            Wk = Pieces.WHITE_KNIGHT, WP = Pieces.WHITE_PAWN, BK = Pieces.BLACK_KING, BQ = Pieces.BLACK_QUEEN, BR = Pieces.BLACK_ROOK,
            BB = Pieces.BLACK_BISHOP, Bk = Pieces.BLACK_KNIGHT, BP = Pieces.BLACK_PAWN, NN = null;

    public static final int SIZE_OF_CHESS_BOARD = 8;

    public static final Pieces[][] initialState = {{BR, Bk, BB, BQ, BK, BB, Bk, BR},
                                                    {BP, BP, BP, BP, BP, BP, BP, BP},
                                                    {NN, NN, NN, NN, NN, NN, NN, NN},
                                                    {NN, NN, NN, NN, NN, NN, NN, NN},
                                                    {NN, NN, NN, NN, NN, NN, NN, NN},
                                                    {NN, NN, NN, NN, NN, NN, NN, NN},
                                                    {WP, WP, WP, WP, WP, WP, WP, WP},
                                                    {WR, Wk, WB, WQ, WK, WB, Wk, WR}};
}
