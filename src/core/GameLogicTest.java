package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.pieces.Pawn;
import enums.Color;
import enums.PlayerType;
import players.Player;
import players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameLogicTest {
	
	

    @Test
    void testIsTurnCompilantWithColor_WhitePlayerTurn() {
        // Arrange
        Game game = new Game();
        Player playerWhite = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);
        Player playerBlack = PlayerFactory.createPlayer(PlayerType.getPlayerType(1), "2", Color.WHITE); 
        Player whitePlayer = playerWhite;
        Player blackPlayer = playerBlack;
        Square[][] squares = new Square[8][8];
        for (int x=0; x<8; x++) {
    		for (int y=0; y<8; y++) {
    			squares[x][y] = new Square(x, y, new Pawn(playerWhite));
    		}
    	}

        // Act
        boolean result = GameLogic.isTurnCompilantWithColor(game, whitePlayer, blackPlayer, squares);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsTurnCompilantWithColor_BlackPlayerTurn() {
        // Arrange
        Game game = new Game();
        Player playerWhite = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);
        Player playerBlack = PlayerFactory.createPlayer(PlayerType.getPlayerType(1), "2", Color.WHITE);
        Player whitePlayer = playerWhite;
        Player blackPlayer = playerBlack;
        Square[][] squares = new Square[8][8];
        game.setWhitePlayerTurn(false);

        // Act
        boolean result = GameLogic.isTurnCompilantWithColor(game, blackPlayer, whitePlayer, squares);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsCheckmate_CheckmateScenario() {
        // Arrange
        Game game = new Game();
        Player playerWhite = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);
        Player playerBlack = PlayerFactory.createPlayer(PlayerType.getPlayerType(1), "2", Color.WHITE);
        Player whitePlayer = playerWhite;
        Player blackPlayer = playerBlack;
        Square[][] squares = new Square[8][8];

        // Act
        boolean result = GameLogic.isCheckmate(game, blackPlayer, whitePlayer, squares);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsStalemate_StalemateScenario() {
        Game game = new Game();
        Player playerWhite = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);
        Player playerBlack = PlayerFactory.createPlayer(PlayerType.getPlayerType(1), "2", Color.WHITE);
        Player whitePlayer = playerWhite;
        Player blackPlayer = playerBlack;
        Square[][] squares = new Square[8][8];

        // Act
        boolean result = GameLogic.isStalemate(game, whitePlayer, blackPlayer, squares);

        // Assert
        assertTrue(result);
    }
}

