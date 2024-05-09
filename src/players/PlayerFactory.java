package players;

import enums.Color;
import enums.PlayerType;

public class PlayerFactory {

    /**
     * Creates a new player with the specified type, name, and color.
     *
     * @param playerType the type of player to create (HUMAN, COMPUTER, NETWORK_PLAYER, etc.)
     * @param name       the name of the player
     * @param color      the color of the player's pieces
     * @return a new instance of the specified player type
     *
     */
    public static Player createPlayer(PlayerType playerType, String name, Color color) {
        switch (playerType) {
            case HUMAN:
                return new HumanPlayer(name, color);
            case COMPUTER:
                return new ComputerPlayer(name, color);
            case NETWORK_USER:
                return new NetworkPlayer(name, color);
            default:
                return null;
        }
    }
}
