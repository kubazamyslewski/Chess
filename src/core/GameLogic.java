package core;

import core.pieces.Piece;
import players.Player;

import java.awt.*;
import java.util.ArrayList;

import core.pieces.King;
import core.pieces.Piece;
import enums.Color;

/**
 * This class contains static methods that are checking game state using 8x8 table from chessboard
 */
public class GameLogic {
	
	protected boolean isEnPassant = false;
	
	protected Square whereEnPassant = new Square(0, 0, null);
	
	public boolean isEnPassant() {
        return isEnPassant;
    }
	
	public Square whereEnPassant()  {
		return whereEnPassant;
	}

    public void setIsEnPassant(boolean enPassant) {
        this.isEnPassant = enPassant;
    }
    
    public void setWhereEnPassant(Square whereEnPassant) {
        this.whereEnPassant = whereEnPassant;
    }
	
    public static boolean isTurnCompilantWithColor(Game game, Player playerChecked, Player playerChecking, Square[][] squares) {
    	if ((game.isWhitePlayerTurn() && (playerChecked.getColor() == Color.WHITE)) || (!game.isWhitePlayerTurn() && (playerChecked.getColor() == Color.BLACK))) {
    		return true;
    	}
		return false;
    	
    }
    /**
     * checks if a given player is checkmated
     * @param player
     * @param squares
     * @return
     */
    public static boolean isCheckmate(Game game, Player playerChecked, Player playerChecking, Square[][] squares){
    	// Jeśli jest tak, że jest szach i nie jest aktualnie ruch gracza który jest szachowany to jest mat
    	if (!isTurnCompilantWithColor(game, playerChecked, playerChecking, squares) && isCheck(playerChecked, playerChecking, squares)) {
    		return true;
    	}
        return false;
    }

    /**
     * Checks if there is a stalemate
     * @param player
     * @param squares
     * @return
     */
    public static boolean isStalemate(Game game, Player playerChecked, Player playerChecking, Square[][] squares){
    	if (!isTurnCompilantWithColor(game, playerChecked, playerChecking, squares) && !isCheck(playerChecked, playerChecking, squares)) {
    		for (Square[] checkingRow : squares) {
        		for (Square checkingSquare: checkingRow) {
        			for (Move move : PieceBehaviour.whateverLegalMovesLookup(checkingSquare, squares)) {
        				if (move != null) {
        					return false;
        				}
        				else return true; 
        			}
        		}
        	}
    	}
        return false;
    }

    /**
     * Checks if given player's king is in check
     * @param player
     * @param squares
     * @return
     */
    public static boolean isCheck(Player playerChecked, Player playerChecking, Square[][] squares){
//    	Color colorChecked = playerChecked.getColor();
// colorChecking = playerChecking.getColor();
    	for (Square[] checkingRow : squares) {
    		for (Square checkingSquare: checkingRow) {
//    			Piece checkingPiece = checkingSquare.getPiece();
    			for (Move move : PieceBehaviour.whateverLegalMovesLookup(checkingSquare, squares)) {
    				if ((move.getEndSquare().getPiece().getName() == "King") && (move.getEndSquare().getPiece().getPlayer() == playerChecked) && (move.getStartSquare().getPiece().getPlayer() == playerChecking)) {
    					return true;
    				}
    			}
    		}
    		
    	}
        return false;
    }

    public static boolean isMoveCausingCheck(Move move, Square[][] squares) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();
        Piece movingPiece = startSquare.getPiece();
        Piece tempPiece = endSquare.getPiece();

        // Perform the move
        endSquare.setPiece(movingPiece);
        startSquare.setPiece(null);

        // Check if the player's own king is in check after the move
        boolean causingCheck = isCheck(movingPiece.getPlayer(), movingPiece.getPlayer(), squares);

        // Undo the move
        startSquare.setPiece(movingPiece);
        endSquare.setPiece(tempPiece);

        return causingCheck;
    }


    public static Square findKingSquare(Square[][] squares, String color) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Square square = squares[i][j];
                if (square.getPiece() != null &&
                        square.getPiece().getName().equals("King") &&
                        square.getPiece().getColor().equals(color)) {
                    return square;
                }
            }
        }
        return null;
    }

    /**
     * Checks if latest move is en Passant and if it is a legal move
     * if no function return false
     * if yes it calls the move functions and returns true
     * @return
     */
    protected boolean enPassant(){
        return false;
    }
    
    public void doComputerMove(){

    }
    
    

    /**
     * The most complicated method
     * It checks whether the move proposed by a player is legal
     * It uses functions from PIeceBehaviour to determine all legal moves for a given square
     * en Passant is an exeption and should be checked in game class
     * @param move
     * @param squares
     * @return
     */
    public static boolean isMoveLegal(Move move, Square[][] squares) {
        if (move == null || squares == null) {
            return false;
        }

        Square startSquare = move.getStartSquare();
        if (startSquare == null || startSquare.getPiece() == null) {
            return false; // No piece on the start square means no move possible
        }

        Piece movingPiece = startSquare.getPiece();
        Move[] legalMoves;

        switch (movingPiece.getName()) {
            case "King":
                legalMoves = PieceBehaviour.kingLegalMoves(startSquare, squares);
                break;
            case "Queen":
                legalMoves = PieceBehaviour.queenLegalMoves(startSquare, squares);
                break;
            case "Rook":
                legalMoves = PieceBehaviour.rookLegalMoves(startSquare, squares);
                break;
            case "Bishop":
                legalMoves = PieceBehaviour.bishopLegalMoves(startSquare, squares);
                break;
            case "Knight":
                legalMoves = PieceBehaviour.knightLegalMoves(startSquare, squares);
                break;
            case "Pawn":
                legalMoves = PieceBehaviour.pawnLegalMoves(startSquare, squares);
                break;
            default:
                return false;
        }

        for (Move legalMove : legalMoves) {
            if (legalMove != null && legalMove.equals(move)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks whether a Pawn can be promoted
     * @param square
     * @return
     */
    public static boolean canPawnBePromoted(Square square){
        return false;
    }

}
