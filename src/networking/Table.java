package networking;

import core.Move;

public class Table {
    private Service clientPlayerWhite;
    private Service clientPlayerBlack;
    private String password;

    Table(String password){

    }

    /**
     *  Method for sending move to the other player
     * @param sender : who is sending the move
     * @param promoted : if promoted to what piece, else null
     */
    public void sendMoveToOther(Service sender, Move move, String promoted){

    }

    /**
     * Method to check if 2 players are at the table
     * @return true if there are 2 players, else false
     */
    public boolean isAllPlayers(){
        return false;
    }

    /**
     * Method for adding players to the table
     * @param client : client that wants to join the table
     */
    public void addPlayer(Service client){

    }

    public void setPassword(String password){

    }
    public String getPassword(){
        return null;
    }




}
