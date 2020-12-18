package javacore.professional.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    public static void task1() {
        System.out.println("====== Task1 ======");
        String[] strings = {"Hello", "World"};
        System.out.printf("before: %s\n", Arrays.toString(strings));
        Main.swap(strings, 0, 1);
        System.out.printf("after: %s\n", Arrays.toString(strings));
    }

    public static void task2() {
        System.out.println("====== Task2 ======");
        String[] strings = {"Hello", "World"};
        List<String> stringList = Main.asList(strings);
        System.out.println(stringList);
    }

    public static void task3() {
        System.out.println("====== Task3 ======");
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox = new Box<>();

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.printf("Are boxes of the same weight? %b \n", orangeBox.compare(appleBox));

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.add(new Apple());
        appleBox2.add(new Apple());
        appleBox2.add(new Apple());

        System.out.printf("appleBox.size = %d, appleBox2.size = %d\n", appleBox.size(), appleBox2.size());
        appleBox2.pourInto(appleBox);
        System.out.printf("appleBox.size = %d, appleBox2.size = %d\n", appleBox.size(), appleBox2.size());
    }

    public static <T> void swap(T[] arr, int idx1, int idx2) {
        T tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    @SafeVarargs
    public static <T> List<T> asList(T... arr) {
        List<T> list = new ArrayList<>(arr.length);
        for (T e : arr) {
            list.add(e);
        }
        return list;
    }

}
