package networking;

import core.Move;

import java.io.IOException;

/**
 * Class representing game on a server.
 */
public class Table {
    private Service clientPlayerWhite;
    private Service clientPlayerBlack;
    private int playerCounter = 0;
    private String password;
    private boolean isAllPlayers = false;

    public Table(String password){
        this.password = password;
    }

    /**
     * Method to check if 2 players are at the table
     * @return true if there are 2 players, else false
     */
    public boolean isAllPlayers(){
        return isAllPlayers;
    }

    /**
     * Method for adding players to the table
     * @param client : client that wants to join the table
     */
    public void addPlayer(Service client){
        switch (playerCounter){
            case 0:
                clientPlayerWhite = client;
            case 1:
                clientPlayerBlack = client;
                isAllPlayers = true;
            default:
                System.out.println("Table is full");

        }
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }




}
