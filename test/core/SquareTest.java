package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.pieces.Piece;
import core.pieces.Rook;
import enums.Color;
import enums.PlayerType;
import players.Player;
import players.PlayerFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SquareTest {

    private Square square;
    private Piece piece;
    private Player player = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);

    @BeforeEach
    void setUp() {
        // Inicjalizacja obiektu Square przed każdym testem
        piece = new Rook(player); // Przykładowy obiekt Piece
        square = new Square(3, 4, piece);
    }

    @Test
    void testGetX() {
        assertEquals(3, square.getX());
    }

    @Test
    void testGetY() {
        assertEquals(4, square.getY());
    }

    @Test
    void testGetPiece() {
        assertEquals(piece, square.getPiece());
    }

    @Test
    void testSetPiece() {
        Piece newPiece = new Rook(player); // Nowy obiekt Piece
        square.setPiece(newPiece);
        assertEquals(newPiece, square.getPiece());
    }

    @Test
    void testSetX() {
        square.setX(5);
        assertEquals(5, square.getX());
    }

    @Test
    void testSetY() {
        square.setY(6);
        assertEquals(6, square.getY());
    }

    @Test
    void testGetPieceWhenNoPiece() {
        square.setPiece(null);
        assertNull(square.getPiece());
    }
}

