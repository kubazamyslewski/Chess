package players;

import enums.PieceColor;
import enums.PlayerType;

public class PlayerFactory {

    /**
     * Creates a new player with the specified type, name, and pieceColor.
     *
     * @param playerType the type of player to create (HUMAN, COMPUTER, NETWORK_PLAYER, etc.)
     * @param name       the name of the player
     * @param pieceColor      the pieceColor of the player's pieces
     * @return a new instance of the specified player type
     *
     */
    public static Player createPlayer(PlayerType playerType, String name, PieceColor pieceColor) {
        return switch (playerType) {
            case HUMAN -> new HumanPlayer(name, pieceColor);
            case COMPUTER -> new ComputerPlayer(name, pieceColor);
            case NETWORK_USER -> new NetworkPlayer(name, pieceColor);
            default -> null;
        };
    }
}
