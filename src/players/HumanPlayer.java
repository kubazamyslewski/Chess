package players;

import core.Chessboard;
import core.Square;
import core.pieces.Piece;
import core.pieces.*;
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
    public HumanPlayer() {}

    /**
     * Constructs a new {@code HumanPlayer} with the specified name and color.
     *
     * @param name  the name of the human player
     * @param color the color of the human player's pieces
     */
    public HumanPlayer(String name, Color color) {
        this.name = name;
        this.color = color;
    }

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
        return goDown;
    }

    /**
     * Sets the direction of the human player's pieces on the board (not applicable for human players).
     *
     * @param goDown the direction flag (ignored for human players)
     */
    public void setGoDown(boolean goDown) {
        this.goDown=goDown;
    }

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
     * @param chessboard the current state of the chessboard
     * @return returns which piece is chosen
     */
    public Piece getPromotionPiece(Chessboard chessboard, Player player, String promotionPieceType) {
        for (int i = 0; i < chessboard.getSquares().length; i++) {
            for (int j = 0; j < chessboard.getSquares()[i].length; j++) {
                Square square = chessboard.getSquares()[i][j];
                Piece piece = square.getPiece();
                if (piece instanceof Pawn && piece.getPlayer() == player && i == 7) {
                    square.setPiece(null);

                    Piece promotionPiece = null;
                    switch (promotionPieceType.toLowerCase()) {
                        case "queen":
                            promotionPiece = new Queen(player);
                            break;
                        case "rook":
                            promotionPiece = new Rook(player);
                            break;
                        case "bishop":
                            promotionPiece = new Bishop(player);
                            break;
                        case "knight":
                            promotionPiece = new Knight(player);
                            break;
                        default:

                            promotionPiece = new Queen(player);
                            break;
                    }

                    square.setPiece(promotionPiece);
                    return promotionPiece;
                }
            }
        }
        return null;
    }
}