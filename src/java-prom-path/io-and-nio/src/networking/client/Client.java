package networking.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final static int SERVER_PORT = 9000;
    private static final String SERVER_HOST = "localhost";

    private static void stopConnection(Socket socket, PrintWriter out) {
        try {
            System.out.println("Logging Out...");
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void join() {

        String name;
        String reply;

        System.out.println("Client: Connection established");
        System.out.println("You can now start sending messages..");
        System.out.println("Enter your name (Please enter your name to join the chat): ");

        Scanner sc = new Scanner(System.in);
        reply = sc.nextLine();
        name = reply;

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            var out = new PrintWriter(socket.getOutputStream(), true);
            var threadClient = new ThreadClient(socket);
            new Thread(threadClient).start();
            out.println(reply + ": has joined chat-room.");
            do {
                var message = (name + " : ");
                reply = sc.nextLine();
                if (reply.equals("bye")) {
                    out.println(reply);
                    stopConnection(socket, out);
                    break;
                }
                out.println(message + reply);
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
