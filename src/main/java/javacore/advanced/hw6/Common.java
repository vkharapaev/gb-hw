package javacore.advanced.hw6;

import java.io.*;
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
            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    if (EXIT.equalsIgnoreCase(message)) {
                        break;
                    }
                    out.writeUTF(String.format("from %s: %s", side, message));
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
        try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
            while (true) {
                String message = in.readUTF();
                System.out.println(message);
            }
        } catch (EOFException ignore) {
            System.out.println("Shutting down...");
        } catch (SocketException e) {
            if (!e.getMessage().equalsIgnoreCase("Socket closed")) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
