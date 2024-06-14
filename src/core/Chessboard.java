package core;

import java.io.Serializable;

import core.pieces.*;
import enums.PieceColor;
import players.HumanPlayer;
import players.Player;

/**
 * This class has a 8x8 table with all squares
 * It initializes placement of squares and pieces on board
 * It also changes the 8x8 table when the movement is done
 */
public class Chessboard implements Serializable{
    private Square[][] squares;

	// test main
	public static void main(String[] args) {
		Chessboard board = new Chessboard();
		board.setSquares();
		board.setPiecesAtStart(new HumanPlayer(), new HumanPlayer());
		Move[] movesArray = PieceBehaviour.queenLegalMoves(board.getSquare(3, 0), board.getSquares());
		for (Move m : movesArray) {
			System.out.println("x:" + m.getEndSquare().getX() + " y:" + m.getEndSquare().getY());
		}
	}

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
	public Square getSquare(int x, int y) {
		if (x >= 0 && x < squares.length && y >= 0 && y < squares[0].length) {
			return squares[x][y];
		}
		return null; //
	}
    
    /**
     * Incializes all squares on the board assigns right player for every piece
     * @param playerWhite
     * @param playerBlack
     */
    public void setPiecesAtStart(Player playerWhite, Player playerBlack) {
    	//initialize Black rooks
    	Rook topBlackRook = new Rook(playerBlack);
    	squares[0][0].setPiece(topBlackRook);
    	Rook bottomBlackRook = new Rook(playerBlack);
    	squares[7][0].setPiece(bottomBlackRook);
    	
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
    	Rook topWhiteRook = new Rook(playerWhite);
    	squares[0][7].setPiece(topWhiteRook);
    	Rook bottomWhiteRook = new Rook(playerWhite);
    	squares[7][7].setPiece(bottomWhiteRook);
    	
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

			//White pawns
			for (int i = 0; i <= 7; i++) {
				Pawn WhitePawn = new Pawn(playerWhite);
				squares[i][6].setPiece(WhitePawn);
			}

			//Black pawns
			for (int i = 0; i <= 7; i++) {
				Pawn BlackPawn = new Pawn(playerBlack);
				squares[i][1].setPiece(BlackPawn);
			}
    }

    /**
     * This function is given a move that is already checked and marked as correct
     * It the changes 8x8 table appropriately
     * @param move
     */
    public boolean makeMove(Move move){
    	Square startSquare = move.getStartSquare();
    	Square endSquare = move.getEndSquare();

		if(startSquare.getPiece() instanceof King || startSquare.getPiece() instanceof Rook){
			startSquare.getPiece().setHasMoved(true);
		}

    	endSquare.setPiece(null);
    	endSquare.setPiece(startSquare.getPiece());
    	startSquare.setPiece(null);
    	
    	if (move.isPromotion()) {
    		endSquare.setPiece(move.getPromotionPiece());
			}

			if (GameLogic.isMoveCheckingOpponentsKing(endSquare.getPiece().getPlayer().getColor(), squares)) {
				return true;
			}

			return false;
    }



    public Square[][] getSquares() {
        return squares;
    }
}
