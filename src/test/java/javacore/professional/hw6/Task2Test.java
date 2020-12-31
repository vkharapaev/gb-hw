package javacore.professional.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {
    @ParameterizedTest
    @MethodSource("prepareData")
    void test(int[] input, boolean expected) {
        assertEquals(expected, Task2.containsOnly1and4(input));
    }

    private static Stream<Arguments> prepareData() {
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, true));
        arguments.add(Arguments.of(new int[]{1, 1, 1, 1, 1, 1}, false));
        arguments.add(Arguments.of(new int[]{4, 4, 4, 4}, false));
        arguments.add(Arguments.of(new int[]{1, 4, 4, 1, 1, 4, 3}, false));
        return arguments.stream();
    }
}
