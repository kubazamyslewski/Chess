package ai;

import core.Game;
import core.Move;

/**
 * This interface represents an AI player
 */
public interface AI {
    public Move getMove(Game game, Move lastMove);
}
