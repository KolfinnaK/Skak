package vinnsla.ai;

import vinnsla.game.ChessGameInterface;
import vinnsla.util.Pair;
import vinnsla.util.Tile;

public interface AIInterface {
    /**
     * Returns the bot's best move as a pair of tiles (key is starting tile, value is ending tile).
     */
    Pair<Tile, Tile> move();

    /**
     * Sets the current game for the bot.
     */
    void setGame(ChessGameInterface game);
}
