package core;

import java.io.Serializable;

import core.pieces.*;
import players.Player;

/**
 * This class has a 8x8 table with all squares
 * It initializes placement of squares and pieces on board
 * It also changes the 8x8 table when the movement is done
 */
public class Chessboard implements Serializable{
    private Square[][] squares;

    /**
     * Make a board full of sqares
     */
    public void setSquares() {
    	squares = new Square[8][8];
    	for (int x=0; x<8; x++) {
    		for (int y=0; y<8; y++) {
    			squares[x][y] = new Square(x, y, null);
    		}
    	}
    }
    
    /**
     * Incializes all squares on the board assigns right player for every piece
     * @param playerWhite
     * @param playerBlack
     */
    public void setPiecesAtStart(Player playerWhite, Player playerBlack) {
    	//initialize Black rooks
    	Rook westernBlackRook = new Rook(playerBlack);
    	squares[0][0].setPiece(westernBlackRook);
    	Rook easternBlackRook = new Rook(playerBlack);
    	squares[7][0].setPiece(easternBlackRook);
    	
    	//Black knights
    	Knight westernBlackKnight = new Knight(playerBlack);
    	squares[1][0].setPiece(westernBlackKnight);
    	Knight easternBlackKnight = new Knight(playerBlack);
    	squares[6][0].setPiece(easternBlackKnight);
    	
    	//Black bishops
    	Bishop westernBlackBishop = new Bishop(playerBlack);
    	squares[2][0].setPiece(westernBlackBishop);
    	Bishop easternBlackBishop = new Bishop(playerBlack);
    	squares[5][0].setPiece(easternBlackBishop);
    	
    	//Black queen
    	Queen blackQueen = new Queen(playerBlack);
    	squares[3][0].setPiece(blackQueen);
    	
    	//Black king
    	King blackKing = new King(playerBlack);
    	squares[4][0].setPiece(blackKing);
    	
    	
    	//initialize White rooks
    	Rook westernWhiteRook = new Rook(playerWhite);
    	squares[0][7].setPiece(westernWhiteRook);
    	Rook easternWhiteRook = new Rook(playerWhite);
    	squares[7][7].setPiece(easternWhiteRook);
    	
    	//White knights
    	Knight westernWhiteKnight = new Knight(playerWhite);
    	squares[1][7].setPiece(westernWhiteKnight);
    	Knight easternWhiteKnight = new Knight(playerWhite);
    	squares[6][7].setPiece(easternWhiteKnight);
    	
    	//White bishops
    	Bishop westernWhiteBishop = new Bishop(playerWhite);
    	squares[2][7].setPiece(westernWhiteBishop);
    	Bishop easternWhiteBishop = new Bishop(playerWhite);
    	squares[5][7].setPiece(easternWhiteBishop);
    	
    	//White queen
    	Queen WhiteQueen = new Queen(playerWhite);
    	squares[3][7].setPiece(WhiteQueen);
    	
    	//White king
    	King WhiteKing = new King(playerWhite);
    	squares[4][7].setPiece(WhiteKing);
    	
    	
    }

    /**
     * This function is given a move that is already checked and marked as correct
     * It the changes 8x8 table appropriately
     * @param move
     */
    public void makeMove(Move move){
    	Square startSquare = move.getStartSquare();
    	Square endSquare = move.getEndSquare();
    	
    	endSquare.setPiece(null);
    	endSquare.setPiece(startSquare.getPiece());
    	startSquare.setPiece(null);
    	
    	if (move.isPromotion()) {
    		endSquare.setPiece(move.getPromotionPiece());
		}
    	
    }

    

    public Square[][] getSquares() {
        return squares;
    }

}
