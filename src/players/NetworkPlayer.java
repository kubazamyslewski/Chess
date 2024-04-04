package players;

import core.Chessboard;
import core.pieces.Piece;
import enums.Color;
import enums.PlayerType;

/**
 * This class represents a player in a networked chess game.
 * It extends the HumanPlayer class and inherits its functionality.
 * This class can be used to handle player interactions in a networked environment.
 */
public class NetworkPlayer extends HumanPlayer {
    String name;
    Color color;
    PlayerType playerType;
    boolean goDown;

    /**
     * Constructs a new NetworkPlayer with default values.
     */
    NetworkPlayer() {}

    /**
     * Constructs a new NetworkPlayer with the specified name and color.
     *
     * @param name  the name of the network player
     * @param color the color of the network player's pieces
     */
    NetworkPlayer(String name, Color color) {}

    /**
     * Retrieves the color associated with this network player.
     *
     * @return the color of the network player's pieces
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves the name of the network player.
     *
     * @return the name of the network player
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the type of the network player (here always human )
     *
     * @return the type of the network player
     */
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }

    /**
     * Checks if the network player's pieces are moving down the board.
     *
     * @return  the direction flag
     */
    public boolean isGoDown() {
        return false;
    }

    /**
     * Sets the direction of the network player's pieces on the board (not applicable for network players).
     *
     * @param goDown the direction flag
     */
    public void setGoDown(boolean goDown) {}

    /**
     * Sets the name of the network player.
     *
     * @param name the name to set for the network player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the network player.
     *
     * @param type the type to set for the network player
     */
    public void setType(PlayerType type) {
        this.playerType = type;
    }

    /**
     * Retrieves the piece chosen for promotion by the network player.
     * This method always returns {@code null} since network players don't choose promotion pieces.
     *
     * @param chessboard the current state of the chessboard (not used for network players)
     * @return returns which piece is chosen
     */
    public Piece getPromotionPiece(Chessboard chessboard) {
        return null;
    }
}
