package networking;

import core.Game;
import core.Move;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Connects client's socket to game server, handles messages sent by server.
 */
public class Client implements Runnable{
    protected DataOutputStream output;
    protected DataInputStream input;
    protected Game game;
    protected Socket moveServer;
    protected Socket messageServer;
    protected ObjectInputStream moveInput;
    protected ObjectOutputStream moveOutput;

    public Client(String serverAddress, Game game) throws IOException {
       this.game = game;
       moveServer = new Socket(serverAddress, 64001);
       messageServer = new Socket(serverAddress, 64000);
       output = new DataOutputStream(messageServer.getOutputStream());
       input = new DataInputStream(messageServer.getInputStream());
       moveOutput = new ObjectOutputStream(moveServer.getOutputStream());
       moveInput = new ObjectInputStream(moveServer.getInputStream());
    }

    /**
     *
     * @param tableID id of the table user wants to join
     * @param password password for the table
     * @param username
     * @return true if connection to the table was successful, else: false
     */
    public boolean join(int tableID, String password, String username){
        try {
            output.writeInt(7);
            output.writeInt(tableID);
            output.writeUTF(username);
            output.writeUTF(password);
            Thread.sleep(1000);
            String response = input.readUTF();
            System.out.println(response);
            switch(response) {
                case "0":
                    System.out.println("Joined Table");
                    return true;
                case "1":
                    System.out.println("Invalid Table ID");
                    return false;
                case "2":
                    System.out.println("Table is full");
                    return false;
                case "3":
                    System.out.println("Invalid paswword");
                    return false;
                default:
                    System.out.println("inny błąd");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * method for listening to incoming moves;
     * TODO: implement listening to local player's moves and network player's moves
     */
    @Override
    public void run() {
        Thread moveThread = new Thread(()->{
            try {
                while(true) {
                    moveInput.readObject();
                    Thread.sleep(1000);
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        moveThread.start();
    }

    /**
     * method for handling moves sent by the other player
     */
    private void handleNewMoveFromServer(Move move){
        game.moveNetworkAction(move);
    }

    /**
     * method for handling received messages
     * TODO: display messages in GUI; all messages can be seen in protocol and service.run() method.
     */
    private void handleCommands(){

    }

    /**
     * Method for sending moves to ServerClient
     *
     */
    public void sendMove(Move move) throws IOException{
        moveOutput.writeObject(move);
    }

    /**
     * //TODO get the move from GUI so it can be sent via network
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Move receiveMove() throws IOException, ClassNotFoundException{
        return new Move(null,null);
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }
    public ObjectOutputStream getMoveOutput(){
        return moveOutput;
    }
    public ObjectInputStream getMoveInput(){
        return moveInput;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Thread.sleep(1000);
        Thread clientW = new Thread(()->{
            try {
                Client c = new Client("127.0.0.1", null);
                c.join(1,"1","dup");
                c.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clientW.start();
    }
}
