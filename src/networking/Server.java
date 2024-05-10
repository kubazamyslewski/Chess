package networking;

import core.Move;

import javax.sound.sampled.Port;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.net.InetAddress;

/**
 * Class starts 2 servers:
 * -message server on port 64000
 * -move server on port 64001
 */
public class Server implements Runnable{
    /**
     * Map containing every table on the server with Integer id
     */
    protected static Map<Integer, Table> tables = new HashMap<>();
    public final int MESSAGES_PORT = 64000;
    public final int MOVES_PORT = 64001;
    protected ServerSocket moveServerSocket;
    protected ServerSocket messageServerSocket;
    protected InetAddress IPaddress;
    protected boolean isRunning = false;
    protected Table tableDisplayed;

    public Server(){
        try{
            moveServerSocket = new ServerSocket(MOVES_PORT);
            messageServerSocket = new ServerSocket(MESSAGES_PORT);
        } catch (Exception e){
            System.err.println("Create server socket:" + e);
        }
    }


    /**
     * Method for starting the server
     */
    public void runServer(){
        isRunning = true;
        this.run();
    }

    /**
     * Method to start listening on server, when player joins, the Service and MoveServer threads are started.
     */
    // TODO: implement choosing player color(If needed), now first player to join is white
    @Override
    public void run() {
        try{
            while (true){
                Socket client1message = messageServerSocket.accept();
                Service c1service = new Service(client1message, this);
                c1service.start();
                Socket client1move = moveServerSocket.accept();
                MoveServerThread c1move = new MoveServerThread(client1move, true);
                c1move.start();
                Socket client2message = messageServerSocket.accept();
                Service c2service = new Service(client2message, this);
                c2service.start();
                Socket client2move = moveServerSocket.accept();
                MoveServerThread c2move = new MoveServerThread(client2move, false);
                c2move.start();
                c2move.setOtherClient(client1move);
                c1move.setOtherClient(client2move);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param idTable : takes an id of the table user wants to join
     * @param password : password for the table
     */
    public void newTable(int idTable, String password) throws Exception{
        if(!tables.containsKey(idTable)) {
            tables.put(idTable, new Table(password));
        } else {
            throw new Exception("Inserted ID exists, choose new ID");
        }
    }

    public InetAddress getIPaddress() {
        return IPaddress;
    }

    /**
     * Method that checks if the server is running
     * @return boolean true if server is running, else false
     */
    public boolean isRunning(){
        return isRunning;
    }

    public Table getTableDisplayed() {
        return tableDisplayed;
    }
    public void displayTable(int tableID){
        tableDisplayed = tables.get(tableID);
    }

    /**
     * Class connecting move sockets of clients, sends moves between players.
     */
    private class MoveServerThread extends Thread{
        private boolean myTurn;
        private Socket client;
        private Socket otherClient;
        ObjectInputStream otherClientReceiver;
        ObjectOutputStream otherClientSender;
        ObjectInputStream moveReceiver;
        ObjectOutputStream moveSender;
        public MoveServerThread(Socket s, boolean white) throws IOException{
            this.client = s;
            moveReceiver = new ObjectInputStream(s.getInputStream());
            moveSender = new ObjectOutputStream(s.getOutputStream());
        }

        private void sendMove(Move m) throws IOException{
            moveSender.writeObject(m);
        }

        /**
         * Receives move from other player, so it can be sent to client's socket.
         * @return
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private Move receiveOtherMove() throws IOException, ClassNotFoundException{
            if(otherClientReceiver==null) {
                return null;
            }
            return (Move) otherClientReceiver.readObject();
        }

        /**
         * Method to receive a move from player and send it to another player.
         * return null when no move was received.
         */
        public Move receiveMove() throws IOException, ClassNotFoundException{
            return (Move) moveReceiver.readObject();
        }

        /**
         * Waits for each player move if it's their turn.
         * //TODO: implement stopwatch in GUI for players to have limited gametime.
         */
        @Override
        public void run(){
            isRunning =true;
            try {
                while (true) {
                    if(myTurn){
                        Move moveToSend = receiveMove();
                        if(moveToSend!=null){
                            otherClientSender.writeObject(moveToSend);
                            myTurn = !myTurn;
                        }
                    } else {
                        Move received = receiveOtherMove();
                        if(received!=null){
                            moveSender.writeObject(received);
                            myTurn = !myTurn;
                        }
                    }

                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        /**
         * Sets other client's socket to send and receive move from other player.
         * @param otherClient other client's socket.
         * @throws IOException
         */
        public void setOtherClient(Socket otherClient) throws IOException{
            this.otherClient = otherClient;
            this.otherClientReceiver = new ObjectInputStream(otherClient.getInputStream());
            this.otherClientSender = new ObjectOutputStream(otherClient.getOutputStream());
        }


    }
}
