package core.pieces;

import players.Player;

/**
 * This is an abstract class that every piece inherits from
 * It has String name which is specified while the constructor is initialized
 * Also it has a Player object in it, mainly to determine Color
 */
public abstract class Piece {
    protected Player player;
    protected String name;

    public Piece(Player player){
        this.player = player;
        this.name = this.getClass().getSimpleName();
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
}
