package core.pieces;

import players.Player;

/**
 * This class represents a pawn in chess
 */
public class Pawn extends Piece {
    protected boolean down = false; //to estimate direction the pawn is going (for black it is going down)

    public Pawn(Player player) {
        super(player);
        this.name = "Pawn";
    }
    public boolean getDown(){
        return down;
    }
    public void setDown(boolean hasMoved){
        this.down = hasMoved;
    }
}
