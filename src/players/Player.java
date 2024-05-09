package players;

import core.Chessboard;
import core.pieces.Piece;
import enums.Color;
import enums.PlayerType;

import java.awt.*;
import java.io.Serializable;
/**
 * The Player interface represents a player in a chess game.
 * This interface defines methods to get and set player attributes, such as color, name, and player type,
 * as well as methods to handle player-specific actions like determining the promotion piece for a pawn.
 * implementation of the interface depends on the type of  player.
 */
public interface Player extends Serializable {

    /**
     * Retrieves the color associated with this player.
     *
     * @return the color of the player WHITE or BLACK
     */
    public Color getColor();

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player
     */
    public String getName();

    /**
     * Retrieves the type of the player
     *
     * @return the type of the player
     */
    public PlayerType getPlayerType();

    /**
     * Checks if the player's pieces are moving down the board (towards higher row indices).
     *
     * @return true if the player's pieces are moving down, false otherwise
     */
    public boolean isGoDown();

    /**
     * Sets the direction of the player's pieces on the board.
     *
     * @param goDown true to set the pieces moving down, false otherwise
     */
    public void setGoDown(boolean goDown);

    /**
     * Sets the name of the player.
     *
     * @param name the name to set for the player
     */
    public void setName(String name);

    /**
     * Sets the type of the player
     *
     * @param type the type to set for the player
     */
    public void setType(PlayerType type);

    /**
     * Retrieves the piece chosen for promotion by the player.
     *
     * @param chessboard the current state of the chessboard
     * @return returns which piece is chosen
     */
    public Piece getPromotionPiece(Chessboard chessboard, Player player, String promotionPieceType);
}
