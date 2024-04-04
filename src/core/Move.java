package core;

import core.pieces.Piece;

/**
 *  This class carries an information on from what to what square player wants to move
 *  Later if the move is promotion change the promotion boolean to true
 *  then also it can be given a promotion piece
 */
public class Move {
     private Square startSquare;
     private Square endSquare;

     private boolean promotion = false;
     private Piece movedPiece = null;
     private Square promotionPiece;

     public Move(Square startSquare, Square endSquare, Piece movedPiece){
         this.startSquare = startSquare;
         this.endSquare = endSquare;
         this.movedPiece = movedPiece;
     }

    public void setEndSquare(Square endSquare) {
        this.endSquare = endSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }

    public void setStartSquare(Square startSquare) {
        this.startSquare = startSquare;
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotionPiece(Square promotionPiece) {
        this.promotionPiece = promotionPiece;
    }

    public Square getPromotionPiece() {
        return promotionPiece;
    }
}
