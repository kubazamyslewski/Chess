package networking;

import core.Game;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

public class NetworkingIntegrationTest {
    @BeforeAll
    public static void testStart(){
        Thread serverThread = new Thread(() -> {
            try {
                Server s = new Server();
                s.runServer();
                s.newTable(1, "1");
                s.newTable(2, "2");
                s.newTable(3, "3");
                s.displayTable(2);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        serverThread.start();
    }
    @Test
    void testClient(){
        try {
            Thread clientW = new Thread(()->{
                try{
                    Client clientWhite = new Client("127.0.0.1", null);
                    clientWhite.run();
                    assertTrue(clientWhite.join(1,"1","user1"));
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
            clientW.run();
            Thread clientB = new Thread(()->{
                try{
                    Client clientBlack = new Client("127.0.0.1", null);
                    clientBlack.run();
                    assertTrue(clientBlack.join(1,"2","user2"));
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
            clientB.run();

            Client clientBlack = new Client("127.0.0.1", null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}