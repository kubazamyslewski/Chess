package networking;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Service implements Runnable{
    public BufferedReader input;

    public PrintWriter output;

    private String username;

    private Table table;


    Service(BufferedReader input, PrintWriter output, String username, Table table){

    }

    /**
     * method for listening
     */
    @Override
    public void run() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
