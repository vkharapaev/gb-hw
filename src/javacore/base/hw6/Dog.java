package javacore.base.hw6;

public class Dog extends Animal {
    private static int dogCount;

    public Dog(String name) {
        super(name, 500, 10);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}
