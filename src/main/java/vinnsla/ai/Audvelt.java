package vinnsla.ai;

import vinnsla.game.ChessGameInterface;
import vinnsla.util.Pair;
import vinnsla.util.Tile;

import java.util.Set;

public class Audvelt implements AIInterface {
    private ChessGameInterface game;

    public Audvelt(ChessGameInterface game) {
        this.game = game;
    }

    private ReturnNodeFromMinimax minimax(Set<Pair<Tile, Tile>> availableMoves, int depth) {
        for (Pair<Tile, Tile> move : availableMoves)
            return new ReturnNodeFromMinimax(move, 0);
        return new ReturnNodeFromMinimax(null, 0);
    }

    @Override
    public Pair<Tile, Tile> move() {
        Set<Pair<Tile, Tile>> availableMoves = game.getAvailableMovesForCurrentPlayer();
        Pair<Tile, Tile> move = minimax(availableMoves, 3).pair;
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
