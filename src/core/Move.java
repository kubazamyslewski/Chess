package core;

import core.pieces.Piece;

import java.io.Serializable;

/**
 *  This class carries an information on from what to what square player wants to move
 *  Later if the move is promotion change the promotion boolean to true
 *  then also it can be given a promotion piece
 */
public class Move implements Serializable {
    private Square startSquare;
    private Square endSquare;
    private boolean promotion = false;
    private Piece promotionPiece;

    public Move(Square startSquare, Square endSquare){
        this.startSquare = startSquare;
        this.endSquare = endSquare;
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

    public void setPromotionPiece(Piece promotionPiece) {
        this.promotionPiece = promotionPiece;
    }

    public Piece getPromotionPiece() {
        return promotionPiece;
    }
}
