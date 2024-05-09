package players;

import core.Chessboard;
import core.Square;
import core.pieces.*;
import enums.Color;
import enums.PlayerType;

/**
 * The ComputerPlayer class represents a computer-controlled player in a chess game.
 * It implements the Player interface, providing methods to retrieve player attributes
 * and handle player-specific actions.
 */
public class ComputerPlayer implements Player {
    private String name;
    private Color color;
    private PlayerType playerType;
    private boolean goDown;

    /**
     * Constructor with default values
     */
    public ComputerPlayer() {}

    /**
     * Constructs a new ComputerPlayer with the specified name and color.
     *
     * @param name  the name of the computer player
     * @param color the color of the computer player's pieces (WHITE or BLACK)
     */
    public ComputerPlayer(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Retrieves the color associated with this computer player.
     *
     * @return the color of the computer player's pieces
     */
    public Color getColor() {

        return color;
    }

    /**
     * Retrieves the name of the computer player.
     *
     * @return the name of the computer player
     */
    public String getName() {

        return name;
    }

    /**
     * Retrieves the type of the computer player (here always COMPUTER)
     *
     * @return the type of the computer player
     */
    public PlayerType getPlayerType() {

        return PlayerType.COMPUTER;
    }

    /**
     * Checks if the computer player's pieces are moving down the board.
     *
     * @return false if up true if down
     */
    public boolean isGoDown() {
        return goDown;
    }

    /**
     * Sets the direction of the computer player's pieces on the board
     *
     * @param goDown the direction flag
     */
    public void setGoDown(boolean goDown) {
        this.goDown = goDown;

    }

    /**
     * Sets the name of the computer player.
     *
     * @param name the name to set for the computer player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the computer player.
     *
     * @param type the type to set for the computer player
     */
    public void setType(PlayerType type) {
        this.playerType = type;
    }

    /**
     * Retrieves the piece chosen for promotion by the computer player.
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
