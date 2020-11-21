package javacore.advanced.hw6;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static final String CLIENT_SIDE = "client";

    public Client(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            System.out.printf("Connected to the server %s:%s. Ready to send messages.\n",
                    socket.getInetAddress().getHostAddress(), socket.getPort());
            new Common(socket, CLIENT_SIDE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            new Client(args[0], Integer.parseInt(args[1]));
        } else {
            new Client(Common.DEFAULT_HOST, Common.DEFAULT_PORT);
        }
    }
}
