package core.pieces;

import players.Player;

/**
 * This class represents a rook in chess
 */
public class Rook extends Piece {

    protected boolean hasMoved = false; //for castling
    public Rook(Player player) {
        super(player);
        this.name = "Rook";
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }
}
