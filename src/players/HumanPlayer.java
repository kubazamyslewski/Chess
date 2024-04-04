package players;

import core.Chessboard;
import core.pieces.Piece;
import enums.Color;
import enums.PlayerType;

/**
 * The {@code HumanPlayer} class represents a human player in a chess game.
 * It implements the {@link Player} interface, providing methods to retrieve player attributes
 * and handle player-specific actions.
 */
public class HumanPlayer implements Player {
    private String name;
    private Color color;
    private PlayerType playerType;
    private boolean goDown;

    /**
     * Constructs a new {@code HumanPlayer} with default values.
     */
    HumanPlayer() {}

    /**
     * Constructs a new {@code HumanPlayer} with the specified name and color.
     *
     * @param name  the name of the human player
     * @param color the color of the human player's pieces
     */
    HumanPlayer(String name, Color color) {}

    /**
     * Retrieves the color associated with this human player.
     *
     * @return the color of the human player's pieces
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves the name of the human player.
     *
     * @return the name of the human player
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the type of the human player (always {@link PlayerType#HUMAN}).
     *
     * @return the type of the human player
     */
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }

    /**
     * Checks if the human player's pieces are moving down the board.
     *
     * @return {@code false} since a human player's direction is not applicable
     */
    public boolean isGoDown() {
        return false;
    }

    /**
     * Sets the direction of the human player's pieces on the board (not applicable for human players).
     *
     * @param goDown the direction flag (ignored for human players)
     */
    public void setGoDown(boolean goDown) {}

    /**
     * Sets the name of the human player.
     *
     * @param name the name to set for the human player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the human player.
     *
     * @param type the type to set for the human player
     */
    public void setType(PlayerType type) {
        this.playerType = type;
    }

    /**
     * Retrieves the piece chosen for promotion by the human player.
     * This method always returns {@code null} since human players don't choose promotion pieces.
     *
     * @param chessboard the current state of the chessboard (not used for human players)
     * @return returns which piece is chosen
     */
    public Piece getPromotionPiece(Chessboard chessboard) {
        return null;
    }
}