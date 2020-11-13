package javacore.advanced.hw4.task2.ui.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalcView implements Initializable, CalcContract.View {

    private static final int ROWS = 5;
    private static final int COLUMNS = 4;

    @FXML
    public GridPane buttons;
    @FXML
    public Label expDisplay;
    @FXML
    public Label display;

    private CalcContract.Presenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        presenter = new CalcPresenter();
        presenter.takeView(this);
    }

    private void initButtons() {
        String[] btnLabels = "(,),<,/,7,8,9,*,4,5,6,-,1,2,3,+,C,0,.,=".split(",");
        for (int j = 0; j < ROWS; j++) {
            for (int i = 0; i < COLUMNS; i++) {
                Button btn = new Button(btnLabels[j * (ROWS - 1) + i]);
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn.setOnAction(value -> presenter.process(btn.getText()));
                buttons.add(btn, i, j);
            }
        }
    }

    @Override
    public void show(String text) {
        display.setText(text);
    }

    @Override
    public void showExpression(String text) {
        expDisplay.setText(text);
    }
}
