package networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service extends Thread{
    public BufferedReader messageReceiver;
    public PrintWriter messageSender;
    private ServerSocket messageServerSocket;
    private Server server;
    private String username;
    private Socket userSocket;
    private ObjectOutputStream moveOutput;
    private ObjectInputStream moveInput;
    Service(Socket userSocket, Server server) throws IOException{
        this.userSocket = userSocket;
        this.server = server;
        this.messageServerSocket = server.messageServerSocket;
        this.messageReceiver = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
        this.messageSender = new PrintWriter(userSocket.getOutputStream(),true);
    }
    /**
     * method for communicating with client via protocol messages
     */
    @Override
    public void run(){
        try {
            String line;
            while ((line = messageReceiver.readLine()) != null) {
                int header = Integer.parseInt(line);

                // Message from client contains header (defined in enum Protocol) and further message.
                switch (header) {
                    case 5:
                        server.moveServerSocket.close();
                        sendMessage("6");
                        messageServerSocket.close();
                    case 7:
                        String idTable = messageReceiver.readLine();
                        if(server.tables.containsKey(idTable)){
                            server.tableDisplayed = server.tables.get(idTable);
                            if(server.tableDisplayed.isAllPlayers()){
                                sendMessage("2");
                            } else {
                                String expectedPassword = server.tableDisplayed.getPassword();
                                String username = messageReceiver.readLine();
                                String insertedPassword = messageReceiver.readLine();
                                if(expectedPassword.equals(insertedPassword)){
                                    this.username = username;
                                    server.tableDisplayed.addPlayer(this);
                                    sendMessage("0");
                                } else {
                                    sendMessage("4");
                                }
                            }
                        } else {
                            sendMessage("1");
                        }
                    case 8:
                        String chatMessage = messageReceiver.readLine();
                    default:
                        messageSender.println("Nieznany typ komunikatu");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        messageSender.write(message);
    }
    public String readMessage() throws  IOException{
        return messageReceiver.readLine();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMoveInput(ObjectInputStream moveInput) {
        this.moveInput = moveInput;
    }
    public ObjectInputStream getMoveInput() {
        return moveInput;
    }
    public void setMoveOutput(ObjectOutputStream moveOutput) {
        this.moveOutput = moveOutput;
    }
    public ObjectOutputStream getMoveOutput() {
        return moveOutput;
    }
}
