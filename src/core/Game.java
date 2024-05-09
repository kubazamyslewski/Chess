package core;

import core.pieces.King;
import core.pieces.Pawn;
import core.pieces.Piece;
import networking.Client;
import players.NetworkPlayer;
import players.Player;
import players.PlayerFactory;

import java.io.IOException;
import java.util.Scanner;

import enums.Color;
import enums.PlayerType;

import java.util.ArrayList;
import java.util.List;


/**
 * This class controls the game by starting it, moving pieces, determining winner
 */
public class Game extends GameLogic {

    private Client client;
    private Chessboard board;
    Player playerWhite;
    Player playerBlack;
    private ArrayList<Move> allMoves;
    public Scanner systemin = new Scanner(System.in);

    private boolean isWhitePlayerTurn=true;


    public Game(){
        init();
        client = null;
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
        setWhitePlayerTurn(true);
        setIsEnPassant(false);

    }


    public static void main(String[] args) {
        Game game = new Game();
    }

    public void newGame(){
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


    public void endGame(){
        System.out.println("Thanks for playing");
    }

    /**
     * This method handles network actions for moves.
     */
//    public void moveNetworkAction() {
//        try {
//
//            if ((isWhitePlayerTurn() && playerWhite instanceof NetworkPlayer) ||
//                    (!isWhitePlayerTurn() && playerBlack instanceof NetworkPlayer)) {
//
//                Move move = (Move) moveInput.readObject();
//                if (move != null) {
//                    executeMove(move);
//                    switchActive();
//                    client.moveOutput().writeObject("Move received");
//                }
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Network error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    /**
     * Switches the current active player
     */
    public void switchActive(){
        isWhitePlayerTurn = !isWhitePlayerTurn;
    }

    /**
     * makes the lastest move in ArrayList
     */
    /**
     * Makes the latest move in ArrayList if there are any moves.
     */
    public void move() {
        if (!allMoves.isEmpty()) {
            Move latestMove = allMoves.remove(allMoves.size() - 1);  // Retrieve and remove the latest move
            executeMove(latestMove);  // Execute the move
            switchActive();  // Switch turns
        }
    }

    /**
     * Executes a move on the board, handling captures and moving the piece.
     */
    private void executeMove(Move move) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();
        Piece movingPiece = startSquare.getPiece();  // Get the piece making the move

        if (movingPiece != null) {
            // Handle capture
            if (endSquare.getPiece() != null) {
                // Capture logic, e.g., adding to a list of captured pieces if needed
            }
            // Move the piece
            endSquare.setPiece(movingPiece);
            startSquare.setPiece(null);
            movingPiece.setHasMoved(true);
            handleSpecialMoves(move, movingPiece);
        }
    }
    private void capturePiece(Square square) {

        square.setPiece(null); //
    }
    /**
     * Handles special moves such as en passant, castling, and pawn promotion.
     */
    private void handleSpecialMoves(Move move, Piece piece) {
        if (move.isPromotion()) {
            promotePawn(move.getEndSquare(), move.getPromotionPiece());
        }

        // Handling en passant
        if (piece instanceof Pawn && Math.abs(move.getStartSquare().getY() - move.getEndSquare().getY()) == 1) {
            if (isEnPassant) { // Assuming 'isEnPassant' and 'whereEnPassant' are tracked elsewhere in your game logic
                capturePiece(whereEnPassant);
                isEnPassant = false;
            }
        }

        // Check if move enables future en passant
        if (piece instanceof Pawn && Math.abs(move.getStartSquare().getX() - move.getEndSquare().getX()) == 2) {
            isEnPassant = true;
            whereEnPassant = move.getEndSquare(); // Set the potential en passant capture square
        }

        // Handling castling
        if (piece instanceof King && Math.abs(move.getStartSquare().getY() - move.getEndSquare().getY()) == 2) {
            handleCastling(move);
        }
    }

    /**
     * Handle the rook movement for castling.
     */
    private void handleCastling(Move move) {
        int row = move.getStartSquare().getX();
        if (move.getEndSquare().getY() > move.getStartSquare().getY()) { // King side castling
            Square rookStart = board.getSquare(row, 7);
            Square rookEnd = board.getSquare(row, move.getEndSquare().getY() - 1);
            rookEnd.setPiece(rookStart.getPiece());
            rookStart.setPiece(null);
        } else { // Queen side castling
            Square rookStart = board.getSquare(row, 0);
            Square rookEnd = board.getSquare(row, move.getEndSquare().getY() + 1);
            rookEnd.setPiece(rookStart.getPiece());
            rookStart.setPiece(null);
        }
    }


    /**
     * Promotes a pawn to a new piece at a given square.
     */
    private void promotePawn(Square square, Piece newPiece) {
        square.setPiece(newPiece);
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

