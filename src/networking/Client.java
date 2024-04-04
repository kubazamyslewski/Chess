package networking;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{

    protected Socket socket;
    protected PrintWriter output;
    protected BufferedReader input;
    protected Game game

    public Client(){

    }

    /**
     *
     * @param tableID id of the table user wants to join
     * @param password password for the table
     * @param username
     * @return true if connection to the table was successful, else: false
     */
    public boolean join(int tableID, String password, String username){
        return false;
    }


    /**
     * method for listening
     */
    @Override
    public void run() {

    }

    /**
     * method for handling moves sent by the other player
     */
    private void handleNewMoveFromServer(){

    }

    /**
     * method for handling received messages
     */
    private void handleCommands(){

    }

    /**
     * Method for sending moves to ServerClient
     * @param promoted : if promoted name of the piece, else null
     */
    public void sendMove(Move move, String promoted){

    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }
}
