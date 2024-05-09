package players;

import core.Chessboard;
import core.pieces.Piece;
import core.pieces.Queen;
import enums.Color;
import enums.PlayerType;
import org.junit.jupiter.api.Test;
import players.ComputerPlayer;
import players.Player;

import static enums.Color.BLACK;
import static enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerPlayerTest {

    @Test
    public void testConstructorWithNameAndColor() {

        String expectedName = "Jack";
        Color expectedColor = Color.BLACK;

        ComputerPlayer player = new ComputerPlayer(expectedName, expectedColor);

        assertEquals(expectedName, player.getName());
        assertEquals(expectedColor, player.getColor());
    }

    @Test
    public void testGetName() {

        String expectedName = "Jack";
        ComputerPlayer player = new ComputerPlayer();
        player.setName(expectedName);

        String actualName = player.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetColor() {

        Color expectedColor = Color.WHITE;
        ComputerPlayer player = new ComputerPlayer("LeelaChess", expectedColor);

        Color actualColor = player.getColor();

        assertEquals(expectedColor, actualColor);
    }

    @Test
    public void testGetPlayerType() {
        ComputerPlayer player = new ComputerPlayer();

        PlayerType playerType = player.getPlayerType();

        assertEquals(PlayerType.COMPUTER, playerType);
    }

    @Test
    public void testIsGoDown() {

        ComputerPlayer player = new ComputerPlayer();

        boolean isGoDown = player.isGoDown();

        assertFalse(isGoDown);
    }

    @Test
    public void testGetPromotionPiece() {
        Player playerWhite = new ComputerPlayer("Jack", WHITE);
        Player playerBlack = new ComputerPlayer("Anna", BLACK);
        Chessboard chessboard = new Chessboard();
        chessboard.setSquares();
        chessboard.setPiecesAtStart(playerWhite, playerBlack);

        Piece promotionPiece = playerWhite.getPromotionPiece(chessboard, playerWhite, "queen");

        assertNotNull(promotionPiece);
        assertTrue(promotionPiece instanceof Queen);
        assertEquals(playerWhite, promotionPiece.getPlayer());
    }
}

