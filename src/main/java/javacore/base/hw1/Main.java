package javacore.base.hw1;

public class Main {
    public static void main(String[] args) {

    }

    // 2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
    private static void initVars() {
        byte byteVar = 99;
        short shortVar = 9999;
        int intVar = 999999999;
        long longVar = 999999999999999999L;
        float floatVar = 9999999999.99999999f;
        double doubleVar = 9999999999.99999999;
        char charVar = '\uFFFF';
        boolean booleanVar = true;
        String stringVar = "Hello world";
    }

    // 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные
    // параметры этого метода;
    private static float calcByFormula(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    // 4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до
    // 20 (включительно), если да – вернуть true, в противном случае – false;
    private static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль
    // положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
    private static void printSign(int val) {
        System.out.println(val < 0 ? "Отрицательное" : "Положительное");
    }

    // 6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true,
    // если число отрицательное;
    private static boolean isNegative(int val) {
        return val < 0;
    }

    // 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в
    // консоль сообщение «Привет, указанное_имя!»;
    private static void sayHello(String name) {
        System.out.printf("Привет, %s!\n", name);
    }

    // 8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль. Каждый 4-й
    // год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    private static void isLeap(int year) {
        boolean isLeapYear = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        System.out.printf("Год %d %s\n", year, isLeapYear ? "високосный" : "не високосный");
    }
}