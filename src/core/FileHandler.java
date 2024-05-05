package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import players.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Utility class for handling game files.
 * This class provides methods for saving and loading game data to/from XML files.
 */
public class FileHandler {
    private String filename;

    /**
     * Constructor for FileHandler class.
     *
     * @param filename The name of the file to be handled.
     */
    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Saves game data to a file.
     *
     * @param allMoves The list of moves representing the game history.
     */
    public void saveGameToFile(List<Move> allMoves, Chessboard board, Player playerWhite, Player playerBlack, boolean isWhitePlayerTurn, boolean isEnPassant, Square whereEnPassant) {
    	// allMoves
    	try {
            FileOutputStream fileOut = new FileOutputStream("allMoves.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allMoves);
            out.close();
            fileOut.close();
            System.out.println("Lista ruchów została zserializowana do pliku allMoves.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// Chessboard
    	try {
            FileOutputStream fileOut = new FileOutputStream("board.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(board);
            out.close();
            fileOut.close();
            System.out.println("board.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// playerWhite
    	try {
            FileOutputStream fileOut = new FileOutputStream("playerWhite.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(playerWhite);
            out.close();
            fileOut.close();
            System.out.println("playerWhite.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// playerBlack
    	try {
            FileOutputStream fileOut = new FileOutputStream("playerBlack.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(playerBlack);
            out.close();
            fileOut.close();
            System.out.println("Lista ruchów została zserializowana do pliku playerBlack.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// isWhitePlayerTurn
    	try {
            FileOutputStream fileOut = new FileOutputStream("isWhitePlayerTurn.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(isWhitePlayerTurn);
            out.close();
            fileOut.close();
            System.out.println("Lista ruchów została zserializowana do pliku isWhitePlayerTurn.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// isEnPassant
    	try {
            FileOutputStream fileOut = new FileOutputStream("isEnPassant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(isEnPassant);
            out.close();
            fileOut.close();
            System.out.println("isEnPassant.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    	// whereEnPassant
    	try {
            FileOutputStream fileOut = new FileOutputStream("whereEnPassant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(whereEnPassant);
            out.close();
            fileOut.close();
            System.out.println("Lista ruchów została zserializowana do pliku whereEnPassant.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads game data from a file.
     *
     * @return The list of moves representing the game history.
     */
    public List<Move> loadGameFromFile() {
    	try {
            FileInputStream fileIn = new FileInputStream("all_Moves.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Move> moves = (List<Move>) in.readObject();
            in.close();
            fileIn.close();
            return moves;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    	return new ArrayList<Move>();
    }
}

