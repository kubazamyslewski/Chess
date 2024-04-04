package ai.levels;

import core.Chessboard;
import core.Game;
import core.Move;
import core.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Basic AI implementation with simple alghoritm.
 */


public class Level2 implements ai.AI{
    /**
     * This method returns the best move for the AI
     * @param game
     * @param lastMove
     * @return
     */
    public Move getMove(Game game, Move lastMove){
        // TODO: Implement AI logic here
        // Consider evaluating available moves, taking into account the current game state and opponent's moves
        // Don't forget to handle special cases like pawn promotion
        // You may want to implement a scoring system to evaluate moves
        // Feel free to refactor and optimize the code for better performance
        // Consider using heuristics or algorithms like minimax with alpha-beta pruning
        // Add your AI logic here

        // Return a placeholder move for now
        return new Move(null, null);

    }
}
