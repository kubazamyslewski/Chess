package core.pieces;

import players.Player;

/**
 * When a pawn moves 2 squares it generates a Passant behind itself.
 */
public class Passant extends Piece {
    private boolean enPassantAllowed;

    public Passant(Player player) {
        super(player);
        this.name = "EnPassant";
    }

    public boolean isEnPassantAllowed() {
        return enPassantAllowed;
    }

    public void setEnPassantAllowed(boolean enPassantAllowed) {
        this.enPassantAllowed = enPassantAllowed;
    }
}
