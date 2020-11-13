package javacore.advanced.hw4.task2.tests;

import javacore.advanced.hw4.task2.expression.InfixExpCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfixExpCreatorTest {

    @Test
    void bracketsAutoComplete() {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("(").add("(").add("(").add("5");
        assertEquals("( ( ( 5 ) ) )", creator.toFinalizedString());
    }

    @Test
    void removingLastOperator() {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("5").add("+").add("9").add("*");
        assertEquals("5 + 9", creator.toFinalizedString());
    }

    @Test
    void notAllowedToAddOperand() {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("5").add("10");
        assertEquals("5", creator.toString());
    }

    @Test
    void notAllowedToAddOperator() {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("5").add("+").add("10").add("+").add("*");
        assertEquals("5 + 10", creator.toFinalizedString());
    }

    @Test
    void notAllowedToAddExtraBracket() {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("(").add("5").add("+").add("10").add(")").add(")");
        assertEquals("( 5 + 10 )", creator.toString());
    }

    @Test
    void emptyExpToString () {
        InfixExpCreator creator = new InfixExpCreator();
        assertEquals("", creator.toString());
    }

    @Test
    void addDecimal () {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("0.5");
        assertEquals("0.5", creator.toString());
    }

    @Test
    void removeLeftBrackets () {
        InfixExpCreator creator = new InfixExpCreator();
        creator.add("5").add("+").add("(").add("(").add("(").add("(").add("(");
        assertEquals("5", creator.toFinalizedString());
    }

}
