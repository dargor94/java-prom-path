package networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

    public static void main(String[] args) {
        //PORT = 0 --> Busca cualquier puerto libre
        int PORT = 9000;
        List<Socket> clients = new ArrayList<>();
        Map<Socket, String> clientsName = new HashMap<>();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server: Connection established");
            while (true) {
                Socket socket = serverSocket.accept();
                clients.add(socket);
                var serverThread = new ServerThread(socket, clients, clientsName);
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
