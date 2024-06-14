package core;

import core.*;
import core.pieces.*;
import enums.Color;
import enums.PlayerType;
import players.Player;
import players.PlayerFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PieceBehaviourTest {

    private Square[][] initializeChessboard() {
        Square[][] squares = new Square[8][8];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square(i, j, null); // Initialize each square without a piece
            }
        }
        return squares;
    }

    @Test
    void testKingLegalMoves() {
        Square[][] squares = initializeChessboard();
        Player player = PlayerFactory.createPlayer(PlayerType.HUMAN, "Alice", Color.WHITE);
        Square checkedSquare = squares[3][3];
        checkedSquare.setPiece(new King(player));  // Using player instance directly

        Move[] moves = PieceBehaviour.kingLegalMoves(checkedSquare, squares);
        assertNotNull(moves, "Moves should not be null.");
        assertTrue(moves.length > 0, "Moves should exist for King in center without obstructions.");
    }

    @Test
    void testPawnLegalMoves() {
        Square[][] squares = initializeChessboard();
        Player player = PlayerFactory.createPlayer(PlayerType.HUMAN, "Bob", Color.WHITE);
        Square checkedSquare = squares[3][5];
        checkedSquare.setPiece(new Pawn(player));  // Using player instance directly

        Move[] moves = PieceBehaviour.pawnLegalMoves(checkedSquare, squares);
        assertNotNull(moves, "Moves should not be null.");
        assertTrue(moves.length > 0, "Expected moves for an unblocked pawn on initial move.");
    }
}
