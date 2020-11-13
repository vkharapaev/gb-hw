package javacore.advanced.hw4.task2.tests;

import javacore.advanced.hw4.task2.expression.Exp;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpTest {

    @Test
    void convertInfixToPostfix () {
        String exp = "( 1 + 2 ) * 3 / ( 4 - 5 )";
        List<String> infix = Arrays.asList(exp.split(" "));
        List<String> postfix = Exp.convertToPostfix(infix);
        assertEquals("[1, 2, +, 3, *, 4, 5, -, /]", postfix.toString());
    }

    @Test
    void calcPostfixExpression () {
        String exp = "1 2 + 3 * 4 5 - /";
        List<String> postfix = Arrays.asList(exp.split(" "));
        assertEquals(BigDecimal.valueOf(-9), Exp.calc(postfix));
    }

    @Test
    void calcSimplePostfixExpression () {
        String exp = "5 10 -";
        List<String> postfix = Arrays.asList(exp.split(" "));
        assertEquals(BigDecimal.valueOf(-5), Exp.calc(postfix));
    }

    @Test
    void testMultipleConsequentOperatorsOfTheSamePriority() {
        String exp = "( 89 + 3 ) * 6 - 552 + 395 / 3 - 395 / 3 * 9 + 395 / 3 * 9 - 395 / 3";
        List<String> infix = Arrays.asList(exp.split(" "));
        List<String> postfix = Exp.convertToPostfix(infix);
        assertEquals(0, Exp.calc(postfix).floatValue());
    }

}
