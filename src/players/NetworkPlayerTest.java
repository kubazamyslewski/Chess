package players;

import core.Chessboard;
import core.pieces.Piece;
import core.pieces.Queen;
import enums.Color;
import enums.PlayerType;
import org.junit.jupiter.api.Test;

import static enums.Color.BLACK;
import static enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkPlayerTest {

    @Test
    public void testConstructorWithNameAndColor() {

        String expectedName = "Alice";
        Color expectedColor = WHITE;

        NetworkPlayer player = new NetworkPlayer(expectedName, expectedColor);

        assertEquals(expectedName, player.getName());
        assertEquals(expectedColor, player.getColor());
    }

    @Test
    public void testGetName() {

        String expectedName = "Bob";
        Color expectedColor = WHITE;
        NetworkPlayer player = new NetworkPlayer(expectedName, expectedColor);
        player.setName(expectedName);

        String actualName = player.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetColor() {

        Color expectedColor = Color.BLACK;
        NetworkPlayer player = new NetworkPlayer("Carol", expectedColor);

        Color actualColor = player.getColor();

        assertEquals(expectedColor, actualColor);
    }

    @Test
    public void testGetPlayerType() {

        NetworkPlayer player = new NetworkPlayer();

        PlayerType playerType = player.getPlayerType();

        assertEquals(PlayerType.HUMAN, playerType);
    }

    @Test
    public void testIsGoDown() {

        NetworkPlayer player = new NetworkPlayer();

        boolean isGoDown = player.isGoDown();

        assertFalse(isGoDown);
    }

    @Test
    public void testGetPromotionPiece() {
        Player playerWhite = new NetworkPlayer("Jack", WHITE);
        Player playerBlack = new NetworkPlayer("Anna", BLACK);
        Chessboard chessboard = new Chessboard();
        chessboard.setSquares();
        chessboard.setPiecesAtStart(playerWhite, playerBlack);

        Piece promotionPiece = playerWhite.getPromotionPiece(chessboard, playerWhite, "queen");

        assertNotNull(promotionPiece);
        assertTrue(promotionPiece instanceof Queen);
        assertEquals(playerWhite, promotionPiece.getPlayer());
    }
}

