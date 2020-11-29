package javacore.advanced.hw7.client.ui.login;

import javacore.advanced.hw7.client.ui.BasePresenter;
import javacore.advanced.hw7.client.ui.BaseView;

public class LoginContract {
    interface View extends BaseView<Presenter> {
        String getLogin();

        String getPass();

        void showError(String message);

        void goToChatWindow();
    }

    interface Presenter extends BasePresenter<View> {
        void signIn();
    }
}
