package javacore.professional.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @ParameterizedTest
    @MethodSource("prepareData")
    void test(int[] input, int[] expected) {
        assertArrayEquals(expected, Task1.copyOfRangeAfterLastFour(input));
    }

    private static Stream<Arguments> prepareData() {
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of(new int[]{6, 2, 5, 4}, new int[]{}));
        arguments.add(Arguments.of(new int[]{6, 2, 5, 4, 1, 2, 3}, new int[]{1, 2, 3}));
        arguments.add(Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        return arguments.stream();
    }

    @Test
    void testException() {
        assertThrows(RuntimeException.class, () -> Task1.copyOfRangeAfterLastFour(new int[]{1, 2, 3}));
    }
}
