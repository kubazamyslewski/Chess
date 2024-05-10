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
    protected PrintWriter output;
    protected BufferedReader input;
    protected Game game;
    protected Socket moveServer;
    protected Socket messageServer;
    protected ObjectInputStream moveInput;
    protected ObjectOutputStream moveOutput;

    public Client(String serverAddress, Game game) throws IOException {
       this.game = game;
       moveServer = new Socket(serverAddress, 64001);
       messageServer = new Socket(serverAddress, 64000);
       output = new PrintWriter(messageServer.getOutputStream());
       input = new BufferedReader(new InputStreamReader(messageServer.getInputStream()));
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
            output.println(Protocol.JOIN.getValue());
            output.println(tableID);
            output.println(username);
            output.println(password);
            Thread.sleep(1000);
            String response = input.readLine();
            if(response.equals("0")){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * method for listening to incoming moves;
     */
    @Override
    public void run() {
        while (true){
            try {
                while(true){
                    Move toPlay = (Move) moveInput.readObject();
                    if(toPlay != null){
                        game.moveNetworkAction(toPlay);
                    }
                    Move toSend = receiveMove();
                    if(receiveMove() != null){
                        sendMove(toSend);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

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

    public PrintWriter getOutput() {
        return output;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }
    public ObjectOutputStream getMoveOutput(){
        return moveOutput;
    }
    public ObjectInputStream getMoveInput(){
        return moveInput;
    }
}
