
package core.pieces;
import players.Player;

/**
 * This class represents a king in chess.
 */
public class King extends Piece {

    protected boolean hasMoved = false; //for castling
    public King(Player player) {
        super(player);
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }
}