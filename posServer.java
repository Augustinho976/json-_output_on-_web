package pos_json;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.com.connectDB;

import java.io.IOException;
import java.io.OutputStream;

public class posServer implements HttpHandler {
    public void handle(HttpExchange httpExchange) {
        System.out.println("Connected to handler...");
        handleResponse(httpExchange);// method
    }

    private void handleResponse(HttpExchange httpExchange) {
        posConnect dbCnct = new posConnect();
        dbCnct.DbConnect();
        String resp = dbCnct.getProducts();//method
        dbCnct.close();

        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlbuilder = new StringBuilder(); //html builder method
            htmlbuilder.append(resp);

            String htmlResponse = htmlbuilder.toString();

            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();


        } catch (IOException ex) {
            System.out.println("IO error on server response" + ex);

        }
    }
}
