package javacore.advanced.hw5;

import java.util.Arrays;

public class Main {

    private static final int SIZE = 10_000_000;
    private static final int HALF = SIZE / 2;
    private static final int FILLER = 1;
    private static final int PART_FIRST = 0;
    private static final int PART_SECOND = 1;

    public static void main(String[] args) throws InterruptedException {
        System.out.printf("method1: %d\n", doMethod1());
        System.out.printf("method2: %d\n", doMethod2());
        System.out.printf("method3: %d\n", doMethod3());
    }

    private static long doMethod1() {
        float[] arr = prepareArray();
        long time = System.currentTimeMillis();
        fillArray(arr);
        return System.currentTimeMillis() - time;
    }

    private static long doMethod2() throws InterruptedException {
        float[] arr = prepareArray();
        long time = System.currentTimeMillis();
        float[][] parts = prepareParts();
        mapArray(arr, parts);

        Thread thread = new Thread(() -> fillArray(parts[PART_SECOND]));
        thread.start();
        fillArray(parts[PART_FIRST]);
        thread.join();

        reduceArray(arr, parts);
        return System.currentTimeMillis() - time;
    }

    private static long doMethod3() {
        float[] arr = prepareArray();
        long time = System.currentTimeMillis();
        float[][] parts = prepareParts();
        mapArray(arr, parts);

        Arrays.stream(parts).parallel()
                .forEach(Main::fillArray);

        reduceArray(arr, parts);
        return System.currentTimeMillis() - time;
    }

    private static float[] prepareArray() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, FILLER);
        return arr;
    }

    private static float[][] prepareParts() {
        return new float[][]{new float[HALF], new float[HALF]};
    }

    private static void mapArray(float[] arr, float[][] parts) {
        System.arraycopy(arr, 0, parts[PART_FIRST], 0, HALF);
        System.arraycopy(arr, HALF, parts[PART_SECOND], 0, HALF);
    }

    private static void fillArray(float[] arr) {
        for (int i = 0, len = arr.length; i < len; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void reduceArray(float[] arr, float[][] parts) {
        System.arraycopy(parts[PART_FIRST], 0, arr, 0, HALF);
        System.arraycopy(parts[PART_SECOND], 0, arr, HALF, HALF);
    }
}
