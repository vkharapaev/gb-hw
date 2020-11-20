package javacore.advanced.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Common {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8189;
    public static final String EXIT = "exit";

    public Common(Socket socket, String side) {

        // OUTPUT
        Thread thread = new Thread(() -> {
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    if (EXIT.equalsIgnoreCase(message)) {
                        break;
                    }
                    out.println(String.format("from %s: %s", side, message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Should be Daemon because it is not possible to interrupt the System.in lock
        // in order to terminated the thread.
        thread.setDaemon(true);
        thread.start();

        // INPUT
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    System.out.println("Shutting down...");
                    break;
                }
                System.out.println(message);
            }
        } catch (SocketException e) {
            if (!e.getMessage().equalsIgnoreCase("Socket closed")) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
