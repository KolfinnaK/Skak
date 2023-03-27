package vinnsla.ai;

import vinnsla.game.ChessGameInterface;
import vinnsla.util.Colors;
import vinnsla.util.Pair;
import vinnsla.util.Tile;

import java.util.Iterator;
import java.util.Set;

public class Cheddar implements AIInterface {
    private ChessGameInterface game;

    public Cheddar(ChessGameInterface game) {
        this.game = game;
    }

    private Pair<Tile, Tile> minimax(int depth) {
        if (game.getCurrentPlayerColor() == Colors.WHITE)
            return max(depth, Integer.MIN_VALUE, Integer.MAX_VALUE).pair;
        return min(depth, Integer.MIN_VALUE, Integer.MAX_VALUE).pair;
    }

    private ReturnNodeFromMinimax min(int depth, int alpha, int beta) {
        if (depth < 0)
            throw new IllegalArgumentException("Depth cannot be less than 0 for minimax.");
        else if (depth == 0)
            return new ReturnNodeFromMinimax(null, evaluate(game));
        else if (game.isGameInStalemate() || game.isCurrentPlayerInCheckmate())
            return new ReturnNodeFromMinimax(null, Integer.MAX_VALUE);

        ReturnNodeFromMinimax min = null;
        Set<Pair<Tile, Tile>> availableMoves = game.getAvailableMovesForCurrentPlayer();
        for (Iterator<Pair<Tile, Tile>> it = availableMoves.iterator(); it.hasNext(); ) {
            Pair<Tile, Tile> move = it.next();
            if (min == null)
                min = new ReturnNodeFromMinimax(move, Integer.MAX_VALUE);
            game.move(move.getKey().getRow(), move.getKey().getCol(), move.getValue().getRow(), move.getValue().getCol());
            ReturnNodeFromMinimax returned = max(depth - 1, alpha, beta);
            if (returned.value < min.value) {
                min.pair = move;
                min.value = returned.value;
            }
            game.undoLastMove();
            if (returned.value < alpha)
                break;
            beta = Math.min(beta, returned.value);
        }

        return min;
    }

    private ReturnNodeFromMinimax max(int depth, int alpha, int beta) {
        if (depth < 0)
            throw new IllegalArgumentException("Depth cannot be less than 0 for minimax.");
        else if (depth == 0)
            return new ReturnNodeFromMinimax(null, evaluate(game));
        else if (game.isGameInStalemate() || game.isCurrentPlayerInCheckmate())
            return new ReturnNodeFromMinimax(null, Integer.MIN_VALUE);

        ReturnNodeFromMinimax max = null;
        Set<Pair<Tile, Tile>> availableMoves = game.getAvailableMovesForCurrentPlayer();
        for (Iterator<Pair<Tile, Tile>> it = availableMoves.iterator(); it.hasNext(); ) {
            Pair<Tile, Tile> move = it.next();
            if (max == null)
                max = new ReturnNodeFromMinimax(move, Integer.MIN_VALUE);
            game.move(move.getKey().getRow(), move.getKey().getCol(), move.getValue().getRow(), move.getValue().getCol());
            ReturnNodeFromMinimax returned = min(depth - 1, alpha, beta);
            if (returned.value > max.value) {
                max.pair = move;
                max.value = returned.value;
            }
            game.undoLastMove();
            if (returned.value > beta)
                break;
            alpha = Math.max(alpha, returned.value);
        }

        return max;
    }

    private int evaluate(ChessGameInterface game) {
        return game.getMaterialHeuristic() + game.getPositionalHeuristic() * 5;
    }

    @Override
    public Pair<Tile, Tile> move() {
        Pair<Tile, Tile> move = minimax(2);
        return move;
    }

    @Override
    public void setGame(ChessGameInterface game) {
        this.game = game;
    }

    class ReturnNodeFromMinimax {
        Pair<Tile, Tile> pair;
        int value;

        ReturnNodeFromMinimax(Pair<Tile, Tile> pair, int value) {
            this.pair = pair;
            this.value = value;
        }
    }
}
