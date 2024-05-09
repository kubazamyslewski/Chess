import core.Chessboard;
import core.Square;
import core.pieces.Pawn;
import core.pieces.Piece;
import core.pieces.Queen;
import enums.Color;
import enums.PlayerType;
import org.junit.jupiter.api.Test;
import players.HumanPlayer;
import players.Player;

import static enums.Color.BLACK;
import static enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

public class HumanPlayerTest {

    @Test
    public void testConstructorWithNameAndColor() {
        String expectedName = "Alice";
        Color expectedColor = Color.WHITE;

        HumanPlayer player = new HumanPlayer(expectedName, expectedColor);

        assertEquals(expectedName, player.getName());
        assertEquals(expectedColor, player.getColor());
    }

    @Test
    public void testGetName() {

        String expectedName = "Bob";
        HumanPlayer player = new HumanPlayer();
        player.setName(expectedName);

        String actualName = player.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetColor() {
        Color expectedColor = Color.BLACK;
        HumanPlayer player = new HumanPlayer("Carol", expectedColor);

        Color actualColor = player.getColor();

        assertEquals(expectedColor, actualColor);
    }

    @Test
    public void testGetPlayerType() {

        HumanPlayer player = new HumanPlayer();

        PlayerType playerType = player.getPlayerType();

        assertEquals(PlayerType.HUMAN, playerType);
    }

    @Test
    public void testIsGoDown() {

        HumanPlayer player = new HumanPlayer();

        boolean isGoDown = player.isGoDown();

        assertFalse(isGoDown);
    }

    @Test
    public void testGetPromotionPiece() {
        Player playerWhite = new HumanPlayer("Jack", WHITE);
        Player playerBlack = new HumanPlayer("Anna", BLACK);
        Chessboard chessboard = new Chessboard();
        chessboard.setSquares();
        chessboard.setPiecesAtStart(playerWhite, playerBlack);
        for (int x=0; x<8; x++) {
    		for (int y=0; y<8; y++) {
    			chessboard.getSquares()[x][y] = new Square(x, y, new Pawn(playerWhite));
    		}
    	}

        Piece promotionPiece = playerWhite.getPromotionPiece(chessboard, playerWhite, "queen");

        assertNotNull(promotionPiece);
        assertTrue(promotionPiece instanceof Queen);
        assertEquals(playerWhite, promotionPiece.getPlayer());
    }
}

