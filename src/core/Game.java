package core;

import players.Player;
import players.PlayerFactory;
import java.util.Scanner;

import enums.Color;
import enums.PlayerType;

import java.util.ArrayList;
import java.util.List;


/**
 * This class controls the game by starting it, moving pieces, determining winner
 */
public class Game extends GameLogic {
	
    private Chessboard board;
    Player playerWhite;
    Player playerBlack;
    private ArrayList<Move> allMoves;
    public Scanner systemin = new Scanner(System.in);

    private boolean isWhitePlayerTurn=true;
    
    
    public Game(){
        newGame();
        setWhitePlayerTurn(true);
        setIsEnPassant(false);
        
        PlayerFactory playerFactory = new PlayerFactory();
        int type;
        String playername;
        System.out.println("Konfiguracja białego gracza");
        System.out.println("Podaj typ białego gracza. Do wyboru:\n 0 - człowiek\n 1 - człowiek internetowy\n 2 - komputer");
        type = systemin.nextInt();
        systemin.nextLine();
        System.out.println("Podaj nazwę gracza");
        playername = systemin.nextLine();
        playerWhite = playerFactory.createPlayer(PlayerType.getPlayerType(type), playername, Color.WHITE);
        System.out.println();
        System.out.println("Konfiguracja czarnego gracza");
        System.out.println("Podaj typ czarnego gracza. Do wyboru:\n 0 - człowiek\n 1 - człowiek internetowy\n 2 - komputer");
        type = systemin.nextInt();
        systemin.nextLine();
        System.out.println("Podaj nazwę gracza");
        playername = systemin.nextLine();
        playerBlack = playerFactory.createPlayer(PlayerType.getPlayerType(type), playername, Color.BLACK);
        type = 0;
        playername = null;
        playerFactory = null;
        
        saveGame();
        loadGame();
        
    }

    /**
     * for initializing all objects
     */
    protected final void init(){
    	board = new Chessboard();
    	board.setSquares();
    	board.setPiecesAtStart(playerWhite, playerBlack);
    }
    
    
    public static void main(String[] args) {
    	Game game = new Game();
    }
    
    public void newGame(){
    	init();
    }
    
    public void endGame(){
    	System.out.println("Thanks for playing");
    }

    public void moveNetworkAction(){

    }
    

    /**
     * Switches the current active player
     */
    public void switchActive(){
    	isWhitePlayerTurn = !isWhitePlayerTurn;
    }
    
    /**
     * makes the lastest move in ArrayList
     */
    public void move(){

    }


    public void saveGame() {
        // Create an instance of FileHandler in the constructor or elsewhere in the class
        FileHandler fileHandler = new FileHandler("zapis");

        // Get the list of moves representing the game history or the state of the board
        List<Move> moves = null;
        // Get the list of moves representing the game history or the state of the board
        // Call the saveGameToFile method using the fileHandler field
        fileHandler.saveGameToFile(allMoves, board, playerBlack, playerWhite, isWhitePlayerTurn, isEnPassant, whereEnPassant);
        
        fileHandler = null;
    }


    // Metoda wczytująca stan gry z pliku
    public void loadGame() {
    	Game game = new Game();
    	FileHandler fileHandler = new FileHandler("zapis");
    	board = (Chessboard) fileHandler.loadGameFromFile("board.ser");
    	allMoves = (ArrayList<Move>) fileHandler.loadGameFromFile("allMoves.ser");
    	playerWhite = (Player) fileHandler.loadGameFromFile("playerWhite.ser");
    	playerBlack = (Player) fileHandler.loadGameFromFile("playerBlack.ser");
    	isWhitePlayerTurn = (boolean) fileHandler.loadGameFromFile("isWhitePlayerTurn.ser");
    	isEnPassant = (boolean) fileHandler.loadGameFromFile("isEnPassant.ser");
    	whereEnPassant = (Square) fileHandler.loadGameFromFile("whereEnPassant.ser");
        
        fileHandler = null;
    }

    
    public boolean isWhitePlayerTurn() {
        return isWhitePlayerTurn;
    }

    public void setWhitePlayerTurn(boolean whitePlayerTurn) {
        isWhitePlayerTurn = whitePlayerTurn;
    }
    
    public Chessboard getChessboard() {
        return board;
    }


    public void setBoard(Chessboard board) {
        this.board = board;
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerWhite(Player playerWhite) {
        this.playerWhite = playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }

    public void setPlayerBlack(Player playerBlack) {
        this.playerBlack = playerBlack;
    }

    public ArrayList<Move> getAllMoves() {
        return allMoves;
    }

    public void setAllMoves(ArrayList<Move> allMoves) {
        this.allMoves = allMoves;
    }

    
}
