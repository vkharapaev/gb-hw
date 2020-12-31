package javacore.advanced.hw4.task1.ui.main;

import java.util.Date;

public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View view;

    @Override
    public void takeView(ChatContract.View view) {
        this.view = view;
    }

    @Override
    public void sendMessage() {
        String message = view.getMessage();
        if (message != null && !message.isEmpty()) {
            view.clearMessageField();
            view.appendToChat(String.format("%s: %s\n", new Date(), message));
        }
    }
}
