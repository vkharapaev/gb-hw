package javacore.advanced.hw4.task1.ui.main;

import javacore.advanced.hw4.task1.ui.BasePresenter;
import javacore.advanced.hw4.task1.ui.BaseView;

class ChatContract {
    interface View extends BaseView<Presenter> {

        /**
         * Get a message from the message text field.
         *
         * @return Message
         */
        String getMessage();

        /**
         * Clear a message in the message text field
         */
        void clearMessageField();

        /**
         * Append a message to the chat
         *
         * @param message Message, that should be appended
         */
        void appendToChat(String message);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Send a message to the chat
         */
        void sendMessage();
    }
}
