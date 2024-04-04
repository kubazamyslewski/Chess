package core;

import players.Player;

/**
 * This class has a 8x8 table with all squares
 * It initializes placement of squares and pieces on board
 * It also changes the 8x8 table when the movement is done
 */
public class Chessboard {
    private Square[][] squares;
    public Chessboard(){

    }

    /**
     * Incializes all squares on the board assigns right player for every piece
     * @param playerWhite
     * @param playerBlack
     */
    public void setPiecesAtStart(Player playerWhite, Player playerBlack){

    }

    /**
     * This function is given a move that is already checked and marked as correct
     * It the changes 8x8 table appropriately
     * @param move
     */
    public void makeMove(Move move){

    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }

}
