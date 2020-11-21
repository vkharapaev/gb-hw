package javacore.advanced.hw6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final String SERVER_SIDE = "server";

    public Server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Server is bound to the port %d and waiting for a client...\n", serverSocket.getLocalPort());
            try (Socket socket = serverSocket.accept()) {
                System.out.printf("The client %s:%s connected\n",
                        socket.getInetAddress().getHostAddress(), socket.getPort());
                new Common(socket, SERVER_SIDE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            new Server(Integer.parseInt(args[0]));
        } else {
            new Server(Common.DEFAULT_PORT);
        }
    }
}
