package javacore.advanced.hw8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private final MyServer myServer;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.myServer = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authenticate();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("There is a problem during a client handler creation.");
        }
    }

    public String getName() {
        return name;
    }

    private void authenticate() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = nick;
                        myServer.broadcastMsg(name + " entered the chat");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("The account is already in use.");
                    }
                } else {
                    sendMsg("The login/pass is not correct.");
                }
            }
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String str = in.readUTF();
            System.out.printf("from %s: %s\n", name, str);
            if (str.startsWith("/")) {
                if (str.equals("/end")) {
                    return;
                }
                if (str.startsWith("/w ")) {
                    int space2Idx = Math.max(str.indexOf(" ", 3), 3);
                    String nick = str.substring(3, space2Idx);
                    String msg = str.substring(space2Idx + 1);
                    myServer.sendMsgToClient(this, nick, msg);
                }
                continue;
            }
            myServer.broadcastMsg(String.format("%s: %s", name, str));
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(name + " has left the chat.");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
