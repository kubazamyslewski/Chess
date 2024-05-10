package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.pieces.Knight;
import core.pieces.Queen;
import core.pieces.Rook;
import enums.Color;
import enums.PlayerType;
import players.Player;
import players.PlayerFactory;

class ChessboardTest {
    private Chessboard chessboard;
    private Player playerWhite;
    private Player playerBlack;

    @BeforeEach
    void setUp() {
        chessboard = new Chessboard();
        playerWhite = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);
        playerBlack = PlayerFactory.createPlayer(PlayerType.getPlayerType(1), "2", Color.WHITE);
        chessboard.setSquares();
        chessboard.setPiecesAtStart(playerWhite, playerBlack);
    }

    @Test
    void testGetSquare_validCoordinates_returnsSquare() {
        Square square = chessboard.getSquare(0, 0);
        assertNotNull(square);
        assertEquals(0, square.getX());
        assertEquals(0, square.getY());
    }

    @Test
    void testGetSquare_invalidCoordinates_returnsNull() {
        Square square = chessboard.getSquare(-1, 0);
        assertNull(square);
    }

    @Test
    void testSetPiecesAtStart_initializesPieces() {
        // Check if Pieces can be placed
        assertEquals(Rook.class, chessboard.getSquare(0, 0).getPiece().getClass());
    }
    
    @Test
    void testMakeMove_validMove_updatesBoard() {
        // Create a valid move (you can adjust the coordinates as needed)
        Square startSquare = chessboard.getSquare(1, 0);
        Square endSquare = chessboard.getSquare(2, 2);
        Move validMove = new Move(startSquare, endSquare);

        // Execute the move
        chessboard.makeMove(validMove);

        // Check if the pieces were correctly moved
        assertNull(startSquare.getPiece());
        assertEquals(Knight.class, endSquare.getPiece().getClass());
    }
}
