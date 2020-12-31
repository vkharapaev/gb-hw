package javacore.professional.hw6;

import java.util.Arrays;

public class Task1 {
    public static int[] copyOfRangeAfterLastFour(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] == 4) {
                return Arrays.copyOfRange(arr, i + 1, arr.length);
            }
        }
        throw new RuntimeException("Array does not contain 4 element.");
    }
}
