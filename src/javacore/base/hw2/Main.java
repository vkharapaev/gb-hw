package javacore.base.hw2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    }

    // 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    private static void task1() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        replaceByCondition(arr, 0, 1, 0);
    }

    /**
     * Производит замену значений элементов массив по условию. Сравнивается элемент массива с comparedValue, если
     * условие выполняется, то значение элемента меняется на valueIfTrue, иначе на valueIfFalse
     *
     * @param arr           Входной массив
     * @param comparedValue Сравниваемое значение
     * @param valueIfTrue   Значение, если условие выполняется
     * @param valueIfFalse  Значение, если условие не выполняется
     */
    private static void replaceByCondition(int[] arr, int comparedValue, int valueIfTrue, int valueIfFalse) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == comparedValue) {
                arr[i] = valueIfTrue;
            } else {
                arr[i] = valueIfFalse;
            }
        }
    }

    // 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    private static void task2() {
        int[] arr = new int[8];
        fillWithArithmeticProgression(arr, 0, 3);
    }

    /**
     * Заполняет массив арифметической прогресией.
     *
     * @param arr        Входной массив
     * @param startValue Значение первого элемента массива
     * @param step       Константа, которую неходимо прибавить к значению предыдущего элемента массива, чтобы получить значение
     *                   следующего элемента
     */
    private static void fillWithArithmeticProgression(int[] arr, int startValue, int step) {
        arr[0] = startValue;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + step;
        }
    }

    // 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    private static void task3() {
        int arr[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyIfLess(arr, 6, 2);
    }

    /**
     * Перебирает элементы массива и умножает очередной элемент массива на multiplier, если его значение меньше comparedValue.
     *
     * @param arr           Входной массив
     * @param comparedValue Сравниваемое значение
     * @param multiplier    Величина, на которую неоходимо умножить значение элемента
     */
    private static void multiplyIfLess(int[] arr, int comparedValue, int multiplier) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < comparedValue) {
                arr[i] *= multiplier;
            }
        }
    }

    // 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
    // цикла(-ов) заполнить его диагональные элементы единицами;
    private static void task4() {
        int arr[][] = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        setDiagonals(arr, 1);
    }

    /**
     * Задает значение диагональным элементам массива.
     *
     * @param arr   Входной массив
     * @param value Новое значение элемента
     */
    private static void setDiagonals(int[][] arr, int value) {
        setMainDiagonal(arr, value);
        setSideDiagonal(arr, value);
    }

    /**
     * Задает значение элементам массива главной диагонали.
     *
     * @param arr   Входной массив
     * @param value Новое значение эемента
     */
    private static void setMainDiagonal(int[][] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = value;
        }
    }

    /**
     * Задает значение элементам массива побочной диагонали.
     *
     * @param arr   Входной массив
     * @param value Новое значение эемента
     */
    private static void setSideDiagonal(int[][] arr, int value) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            arr[len - 1 - i][i] = 1;
        }
    }

    // 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    private static void task5() {
        int arr[] = {2, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = findMin(arr);
        int max = findMax(arr);
    }

    /**
     * Поиск минимального элемента в массиве.
     *
     * @param arr Входной массив
     * @return Возвращается значение минимального элемента массива
     */
    private static int findMin(int[] arr) {
        int min = arr[0];
        for (int element : arr) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }

    /**
     * Поиск максимального элемента в массиве.
     *
     * @param arr Входной массив
     * @return Возвращается значение максимального элемента массива
     */
    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int element : arr) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }


    // 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    // checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    // граница показана символами ||, эти символы в массив не входят.
    private static void task6() {
        int[] arr = {2, 2, 2, 1, 2, 2, 10, 1};
        boolean result = checkBalance(arr);
    }

    /**
     * Метод возвращает true, если в массиве есть место, в котором сумма левой и правой части массива равны.
     *
     * @param arr Входной массив
     * @return True, если такое место найдено, иначе - false
     */
    private static boolean checkBalance(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        int leftSum = arr[0];
        int rightSum = totalSum - leftSum;
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
    private static void task7() {
        int[] arr = {1, 2, 3};
        rotate(arr, -3);
    }

    /**
     * Вращает циклично элементы массива на N элементов влево, при N<0 или враво, при N>0
     *
     * @param arr Входной массив
     * @param n   Количество смещений
     */
    public static void rotate(int[] arr, int n) {
        int distance = Math.abs(n % arr.length); // избавляемся от избыточных смещений
        if (n > 0) {
            rotateRight(arr, distance);
        } else {
            rotateLeft(arr, distance);
        }
    }

    /**
     * Сместить элементы массива циклично на N вправо
     */
    private static void rotateRight(int[] arr, int n) {
        for (int j = 0; j < n; j++) {
            int tmp = arr[arr.length - 1];
            System.arraycopy(arr, 0, arr, 1, arr.length - 1);
            arr[0] = tmp;
        }
    }

    /**
     * Сместить элементы массива циклично на N влево
     */
    private static void rotateLeft(int[] arr, int n) {
        for (int j = 0; j < n; j++) {
            int tmp = arr[0];
            System.arraycopy(arr, 1, arr, 0, arr.length - 1);
            arr[arr.length - 1] = tmp;
        }
    }

}
