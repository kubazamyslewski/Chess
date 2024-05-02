package ai.levels;
/**
 * Basic AI implementation with random moves.
 * This AI randomly selects a move from available moves for its pieces.
 */
import ai.AI;
import core.Chessboard;
import core.Game;
import core.Move;
import core.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level1 implements AI {
    @Override
    public Move getMove(Game game, Move lastMove) {
        // TODO: Implement AI logic here
        // This AI randomly selects a move from available moves for its pieces.
        // Special cases like pawn promotion are not handled.
        // Consider adding more sophisticated logic for better performance.

        return new Move(null, null);
    }
}
