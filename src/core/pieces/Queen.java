package core.pieces;

import players.Player;

/**
 * This class represents a queen in chess
 */
public class Queen extends Piece {
    public Queen(Player player) {
        super(player);
        this.name = "Queen";
    }
}
