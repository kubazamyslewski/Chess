package players;

import core.Chessboard;
import core.Square;
import core.pieces.*;
import enums.PieceColor;
import enums.PlayerType;

/**
 * This class represents a player in a networked chess game.
 * It extends the HumanPlayer class and inherits its functionality.
 * This class can be used to handle player interactions in a networked environment.
 */
public class NetworkPlayer implements Player {
    String name;
    PieceColor pieceColor;
    PlayerType playerType;
    boolean goDown;

    /**
     * Constructs a new NetworkPlayer with default values.
     */
    public NetworkPlayer() {}

    /**
     * Constructs a new NetworkPlayer with the specified name and pieceColor.
     *
     * @param name  the name of the network player
     * @param pieceColor the pieceColor of the network player's pieces
     */
    public NetworkPlayer(String name, PieceColor pieceColor) {
    	this.name = name;
        this.pieceColor = pieceColor;
    }

    /**
     * Retrieves the pieceColor associated with this network player.
     *
     * @return the pieceColor of the network player's pieces
     */
    public PieceColor getColor() {
        return pieceColor;
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
        return goDown;
    }

    /**
     * Sets the direction of the network player's pieces on the board (not applicable for network players).
     *
     * @param goDown the direction flag
     */
    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

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