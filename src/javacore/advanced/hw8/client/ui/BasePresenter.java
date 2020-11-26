package javacore.advanced.hw8.client.ui;

public interface BasePresenter<T> {

    /**
     * Set a view to a presenter
     *
     * @param view The view associated with this presenter
     */
    void takeView(T view);
}
