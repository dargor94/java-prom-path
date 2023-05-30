package networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

public class ServerThread extends Thread {

    private final Socket socket;
    private final List<Socket> clients;
    private final Map<Socket, String> clientNameList;

    public ServerThread(Socket clientSocket, List<Socket> clients, Map<Socket, String> clientNameList) {
        this.socket = clientSocket;
        this.clients = clients;
        this.clientNameList = clientNameList;
    }

    private void sendMessage(Socket sender, String outputString) {
        Socket socket;
        PrintWriter printWriter;
        int i = 0;
        while (i < clients.size()) {
            socket = clients.get(i);
            i++;
            try {
                if (socket != sender) {
                    printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(outputString);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                var inputLine = in.readLine();
                if (inputLine.equals("bye")) {
                    throw new SocketException();
                }
                if (!clientNameList.containsKey(socket)) {
                    String[] messageString = inputLine.split(":", 2);
                    clientNameList.put(socket, messageString[0]);
                    System.out.println(messageString[0] + messageString[1]);
                    sendMessage(socket, messageString[0] + messageString[1]);
                } else {
                    System.out.println(inputLine);
                    sendMessage(socket, inputLine);
                }

            }
        } catch (SocketException e) {
            String printMessage = clientNameList.get(socket) + " left the chat room";
            System.out.println(printMessage);
            sendMessage(socket, printMessage);
            clients.remove(socket);
            clientNameList.remove(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
