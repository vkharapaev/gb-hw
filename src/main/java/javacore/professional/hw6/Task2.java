package javacore.professional.hw6;

public class Task2 {
    public static boolean containsOnly1and4(int[] arr) {
        boolean f1 = false, f4 = false;
        for (int e : arr) {
            if (e == 1) {
                f1 = true;
            } else if (e == 4) {
                f4 = true;
            } else {
                return false;
            }
        }
        return f1 && f4;
    }
}
