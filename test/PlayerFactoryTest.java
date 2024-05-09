import enums.Color;
import enums.PlayerType;
import org.junit.Test;
import players.Player;
import players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerFactoryTest {

    @Test
    public void testCreatePlayer() {

        String playerName = "John";
        Color playerColor = Color.WHITE;


        Player humanPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN, playerName, playerColor);
        Player computerPlayer = PlayerFactory.createPlayer(PlayerType.COMPUTER, playerName, playerColor);
        Player networkPlayer = PlayerFactory.createPlayer(PlayerType.NETWORK_USER, playerName, playerColor);


        assertNotNull(humanPlayer);
        assertNotNull(computerPlayer);
        assertNotNull(networkPlayer);

        assertEquals(PlayerType.HUMAN, humanPlayer.getPlayerType());
        assertEquals(PlayerType.COMPUTER, computerPlayer.getPlayerType());
        assertEquals(PlayerType.HUMAN, networkPlayer.getPlayerType());

        assertEquals(playerName, humanPlayer.getName());
        assertEquals(playerName, computerPlayer.getName());
        assertEquals(playerName, networkPlayer.getName());

        assertEquals(playerColor, humanPlayer.getColor());
        assertEquals(playerColor, computerPlayer.getColor());
        assertEquals(playerColor, networkPlayer.getColor());
    }
}

