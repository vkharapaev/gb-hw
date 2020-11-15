package javacore.advanced.hw4.task2.ui.main;

import javacore.advanced.hw4.task2.expression.Exp;
import javacore.advanced.hw4.task2.expression.InfixExpCreator;
import javacore.advanced.hw4.task2.number.NumCreator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static javacore.advanced.hw4.task2.expression.ExpUtil.*;
import static javacore.advanced.hw4.task2.number.NumUtil.*;

public class CalcPresenter implements CalcContract.Presenter {

    private static final String OPERATION_EQ = "=";
    private static final String OPERATION_DEL = "<";
    private static final String OPERATION_CLEAR = "C";

    private final InfixExpCreator expCreator;
    private final NumCreator numCreator;

    private CalcContract.View view;

    public CalcPresenter() {
        expCreator = new InfixExpCreator();
        numCreator = new NumCreator();
    }

    @Override
    public void takeView(CalcContract.View view) {
        this.view = view;
    }

    @Override
    public void process(String operation) {
        try {
            if (isDigit(operation) || isDot(operation)) {
                numCreator.add(operation);

            } else if (isOperator(operation) || isRightBracket(operation)) {
                expCreator.add(numCreator.toString());
                expCreator.add(operation);
                numCreator.clear();

            } else if (isLeftBracket(operation)) {
                expCreator.add(operation);

            } else if (OPERATION_EQ.equals(operation)) {
                expCreator.add(numCreator.toString());
                numCreator.clear();
                if (!expCreator.isEmpty()) {
                    List<String> postfix = Exp.convertToPostfix(expCreator.get());
                    numCreator.set(convertToString(Exp.calc(postfix)));
                }

            } else if (OPERATION_DEL.equals(operation)) {
                numCreator.removeLast();

            } else if (OPERATION_CLEAR.equals(operation)) {
                numCreator.clear();
                expCreator.clear();
            }

            view.show(numCreator.toString());

        } catch (ArithmeticException e) {
            view.show(e.getMessage());
        }

        view.showExpression(expCreator.toString());
    }

    private String convertToString(BigDecimal num) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(100);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(num);
    }
}
