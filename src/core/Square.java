package core;

import java.io.Serializable;
import core.pieces.Piece;

/**
 * This class contains x, y to determine position on the board and Piece object
 * Basic tile on the chess board with a piece standing on it
 */
public class Square implements Serializable {
    private int x, y;
    private Piece piece;

    public Square(int y, int x, Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
