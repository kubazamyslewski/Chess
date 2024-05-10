package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Color;
import enums.PlayerType;
import players.Player;
import players.PlayerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    private FileHandler fileHandler;

    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler("testFolder");
    }

    @Test
    void testMakeAFolder() {
        String folderName = "testFolder";
        fileHandler.makeAFolder(folderName);
        File folder = new File(folderName);
        assertTrue(folder.exists(), "Folder should exist after creating it.");
    }

		@Test
        void testSaveandLoadGameFile() {
            List<Move> allMoves = new ArrayList<>();
            Chessboard board = new Chessboard();
            Player playerWhite = PlayerFactory.createPlayer(PlayerType.getPlayerType(0), "1", Color.WHITE);
            Player playerBlack = PlayerFactory.createPlayer(PlayerType.getPlayerType(1), "2", Color.WHITE);
            boolean isWhitePlayerTurn = true;
            boolean isEnPassant = false;
            Square whereEnPassant = null;

            fileHandler.saveGameToFile(allMoves, board, playerWhite, playerBlack, isWhitePlayerTurn, isEnPassant, whereEnPassant);

            File allMovesFile = new File("testFolder/allMoves.ser");
            File boardFile = new File("testFolder/board.ser");
            File playerWhiteFile = new File("testFolder/playerWhite.ser");
            File playerBlackFile = new File("testFolder/playerBlack.ser");

            assertTrue(allMovesFile.exists(), "allMoves.ser should exist after saving game data.");
            assertTrue(boardFile.exists(), "board.ser should exist after saving game data.");
            assertTrue(playerWhiteFile.exists(), "playerWhite.ser should exist after saving game data.");
            assertTrue(playerBlackFile.exists(), "playerBlack.ser should exist after saving game data.");
            
            File isWhitePlayerTurnFile = new File("testFolder/isWhitePlayerTurn.ser");
            File isEnPassantFile = new File("testFolder/isEnPassant.ser");
            File whereEnPassantFile = new File("testFolder/whereEnPassant.ser");
            assertTrue(isWhitePlayerTurnFile.exists(), "isWhitePlayerTurn.ser should exist after saving game data.");
            assertTrue(isEnPassantFile.exists(), "isEnPassant.ser should exist after saving game data.");
            assertTrue(whereEnPassantFile.exists(), "whereEnPassant.ser should exist after saving game data.");
            
//            board = (Chessboard) fileHandler.loadGameFromFile("board.ser");
//            allMoves = (ArrayList<Move>) fileHandler.loadGameFromFile("allMoves.ser");
//            playerWhite = (Player) fileHandler.loadGameFromFile("playerWhite.ser");
//            playerBlack = (Player) fileHandler.loadGameFromFile("playerBlack.ser");
//            isWhitePlayerTurn = (boolean) fileHandler.loadGameFromFile("isWhitePlayerTurn.ser");
//            isEnPassant = (boolean) fileHandler.loadGameFromFile("isEnPassant.ser");
//            whereEnPassant = (Square) fileHandler.loadGameFromFile("whereEnPassant.ser");
        }

}

