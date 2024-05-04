package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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
     * @param moves The list of moves representing the game history.
     */
    public void saveGameToFile(List<Move> moves) {
    	try {
            FileOutputStream fileOut = new FileOutputStream("allMoves.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(moves);
            out.close();
            fileOut.close();
            System.out.println("Lista ruchów została zserializowana do pliku allMoves.ser");
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
            FileInputStream fileIn = new FileInputStream("listaZakupow.ser");
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

