package javacore.base.hw2;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        System.out.printf("task6: %s", checkBalance(new int[]{8, 8, 16}));
        System.out.printf("task7: %s", Arrays.toString(rotate(new int[]{1, 2, 3}, 1)));
    }

    // 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    private static void task1() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
        System.out.printf("task1: %s\n", Arrays.toString(arr));
    }

    // 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    private static void task2() {
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        System.out.printf("task2: %s\n", Arrays.toString(arr));
    }

    // 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    private static void task3() {
        int arr[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
        System.out.printf("task3: %s\n", Arrays.toString(arr));
    }

    // 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
    // цикла(-ов) заполнить его диагональные элементы единицами;
    private static void task4() {
        int arr[][] = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            arr[i][i] = 1;
            arr[len - 1 - i][i] = 1;
        }
        System.out.printf("task4: %s\n", Arrays.deepToString(arr));
    }

    // 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    private static void task5() {
        int arr[] = {2, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = arr[0], max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.printf("task5: %s, min = %d, max = %d\n", Arrays.toString(arr), min, max);
    }

    // 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    // checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    // граница показана символами ||, эти символы в массив не входят.
    private static boolean checkBalance(int[] arr) {
        int leftSum = arr[0];
        int rightSum = Arrays.stream(arr).sum() - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (leftSum == rightSum) {
                return true;
            }
            leftSum += arr[i];
            rightSum -= arr[i];
        }
        return false;
    }

    // 7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
    // или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются
    // циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1
    // (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую
    // сторону сдвиг можете выбирать сами.
    private static int[] rotate(int[] arr, int n) {
        for (int j = 0; j < Math.abs(n); j++) {
            if (n > 0) {
                int tmp = arr[arr.length - 1];
                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = tmp;
            } else {
                int tmp = arr[0];
                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = tmp;
            }
        }
        return arr;
    }
}
