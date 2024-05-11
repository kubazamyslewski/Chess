package networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class for handling messages and protocol communication with client.
 */
public class Service extends Thread{
    public DataInputStream messageReceiver;
    public DataOutputStream messageSender;
    private ServerSocket messageServerSocket;
    private Server server;
    private String username;
    private Socket userSocket;
    Service(Socket userSocket, Server server) throws IOException{
        this.userSocket = userSocket;
        this.server = server;
        this.messageServerSocket = server.messageServerSocket;
        this.messageReceiver = new DataInputStream(userSocket.getInputStream());
        this.messageSender = new DataOutputStream(userSocket.getOutputStream());
    }
    /**
     * method for communicating with client via protocol messages
     */
    @Override
    public void run(){
        try {
            DataInputStream dataInputStream = new DataInputStream(userSocket.getInputStream());
            while (true) {
                int header = dataInputStream.readInt();// Odbiór nagłówka jako int

                // Message from client contains header (defined in enum Protocol) and further message.
                switch (header) {
                    case 5:
                        server.moveServerSocket.close();
                        sendMessage("6");
                        messageServerSocket.close();
                        break;
                    case 7:
                        System.out.println("Ktoś chce dołączyć");
                        int idTable = dataInputStream.readInt();
                        System.out.println("Table id: " + idTable) ;
                        String username = dataInputStream.readUTF();
                        String password = dataInputStream.readUTF();
                        if (server.tables.containsKey(idTable)) {
                            server.tableDisplayed = server.tables.get(idTable);
                            if (server.tableDisplayed.isAllPlayers()) {
                                sendMessage("2");
                            } else {
                                String expectedPassword = server.tableDisplayed.getPassword();
                                if (expectedPassword.equals(password)) {
                                    this.username = username;
                                    server.tableDisplayed.addPlayer(this);
                                    sendMessage("0");
                                } else {
                                    sendMessage("3");
                                }
                            }
                        } else {
                            sendMessage("1");
                        }
                        break;
                    case 8:
                        String chatMessage = dataInputStream.readUTF();
                        break;
                    default:
                        messageSender.writeUTF("Nieznany typ komunikatu");
                        break;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void sendMessage(String message)throws IOException{
        messageSender.writeUTF(message);
    }
    public String readMessage() throws  IOException{
        return messageReceiver.readUTF();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
