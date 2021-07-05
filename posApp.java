package pos_json;

import com.sun.net.httpserver.HttpServer;
import server.com.studenns;
import server.com.studennsHttp;

import java.io.IOException;
import java.net.InetSocketAddress;

public class posApp {
    HttpServer server;

    public static void main(String[] args){
        System.out.println("Pos server started...");

        posApp app = new posApp();
        app.init();
    }
    public  void  init() {
        try{
            server= HttpServer.create(new InetSocketAddress("localhost", 8001),0);
            server.createContext("/pos", new posServer());
            server.start();
        }catch(IOException ex) {
            System.out.println("Server error" + ex);

        }
    }
}
