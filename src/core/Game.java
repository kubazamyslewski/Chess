package core;

import players.Player;

import java.util.ArrayList;

/**
 * This class controls the game by starting it, moving pieces, determining winner
 */
public class Game {
    private Chessboard board;
    private Player playerWhite;
    private Player playerBlack;
    private ArrayList<Move> allMoves;

    private boolean isWhitePlayerTurn=true;
    private boolean enPassant = false;
    public Game(){
        init();
    }

    /**
     * for initializing all objects
     */
    protected final void init(){

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

    /**
     * makes the lastest move in ArrayList
     */
    public void move(){

    }
    public void newGame(){

    }
    public void endGame(){

    }

    public void moveNetworkAction(){

    }
    public void doComputerMove(){

    }

    /**
     * Switches the current active player
     */
    public void switchActive(){

    }

    public void isCheckMate(){

    }
    public void isStaleMate(){

    }
}
