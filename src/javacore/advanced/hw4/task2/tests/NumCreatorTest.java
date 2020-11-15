package javacore.advanced.hw4.task2.tests;

import javacore.advanced.hw4.task2.number.NumCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumCreatorTest {

    @Test
    void notAllowedDoubleDot() {
        NumCreator creator = new NumCreator();
        creator.add("1").add(".").add(".").add("9");
        assertEquals("1.9", creator.toString());
    }

    @Test
    void removeAndAddDot() {
        NumCreator creator = new NumCreator();
        creator.add("1").add(".");
        creator.removeLast();
        creator.add(".").add("9");
        assertEquals("1.9", creator.toString());
    }
}
