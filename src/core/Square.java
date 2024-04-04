package core;

import core.pieces.Piece;

/**
 * This class contains x, y to determine position on the board and Piece object
 * Basic tile on the chess board with a piece standing on it
 */
public class Square {
    //this class may be good for direct gui implementation
    private int x;
    private int y;

    private Piece piece;

    public Square(int x, int y, Piece piece){
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
