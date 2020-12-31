package javacore.advanced.hw8.client.ui.login;

import javacore.advanced.hw8.client.Client;
import javacore.advanced.hw8.client.ClientApp;
import javacore.utils.Util;
import javafx.application.Platform;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private final Client client;

    public LoginPresenter() {
        client = ClientApp.getInstance().getClient();
        client.setMessageListener(msg -> Platform.runLater(() -> view.showError(msg)));
        client.setAuthorizationListener(isAuthorized -> Platform.runLater(() -> processAuthorizationChange(isAuthorized)));
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void signIn() {
        String login = Util.nvl(view.getLogin(), "").trim();
        String pass = Util.nvl(view.getPass(), "").trim();

        if (login.isEmpty() || pass.isEmpty()) {
            view.showError("Please, enter a login and password");
            return;
        }

        view.showError("Connecting...");
        client.signIn(login, pass);
    }

    public void processAuthorizationChange(boolean isAuthorized) {
        if (isAuthorized) {
            view.goToChatWindow();
        }
    }
}
