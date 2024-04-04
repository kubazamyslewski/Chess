package networking;

import javax.sound.sampled.Port;
import java.net.ServerSocket;
import java.util.Map;

public class Server implements Runnable{
    private static Map<Integer, Table> tables;
    public static int PORT;
    private static ServerSocket serverSocket;
    private static boolean isRunning;

    public Server(){}


    /**
     * Method for starting the server
     */
    private static void runServer(){

    }

    /**
     * Method to start listening on server
     */
    @Override
    public void run() {
    }

    /**
     * @param idTable : takes an id of the table user wants to join
     * @param password : password for the table
     */
    public void newTable(int idTable, String password){

    }

    /**
     * Method that checks if the server is running
     * @return boolean true if server is running, else false
     */
    public boolean isRunning(){
        return isRunning;
    }


}
