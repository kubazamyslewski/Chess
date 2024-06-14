package core.pieces;

import java.io.Serializable;
import players.Player;

/**
 * This is an abstract class that every piece inherits from.
 * It has a String name which is specified while the constructor is initialized.
 * It also has a Player object in it, mainly to determine the color of the piece.
 */
public abstract class Piece implements Serializable {
    protected Player player;
    protected String name;
    protected String color;
    protected boolean hasMoved = false;

    public Piece(Player player){
        this.player = player;
        this.name = this.getClass().getSimpleName();
        this.color = String.valueOf(player.getColor());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public String getColor() {
        return color;
    }
}
