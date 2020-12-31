package javacore.advanced.hw8.client.ui.chat;

import javacore.advanced.hw8.client.Client;
import javacore.advanced.hw8.client.ClientApp;
import javafx.application.Platform;

public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View view;
    private final Client client;

    public ChatPresenter() {
        this.client = ClientApp.getInstance().getClient();
    }

    @Override
    public void takeView(ChatContract.View view) {
        this.view = view;
        client.setMessageListener(msg -> Platform.runLater(() -> view.appendToChat(msg + "\n")));
        client.setAuthorizationListener(isAuthorized -> Platform.runLater(() -> processAuthorizationChange(isAuthorized)));
    }

    @Override
    public void sendMessage() {
        String message = view.getMessage();
        if (message != null && !message.isEmpty()) {
            view.clearMessageField();
            client.sendMsg(message);
        }
    }

    public void processAuthorizationChange(boolean isAuthorized) {
        if (!isAuthorized) {
            view.goToLoginWindow();
        }
    }
}
