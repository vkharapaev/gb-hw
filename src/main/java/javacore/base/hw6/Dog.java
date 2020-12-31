package javacore.base.hw6;

public class Dog extends Animal {

    private static int dogCount;
    private static final int RUNNING_MAX_DISTANCE = 500;
    private static final int SWIMMING_MAX_DISTANCE = 10;

    public Dog(String name) {
        super(name, RUNNING_MAX_DISTANCE, SWIMMING_MAX_DISTANCE);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}
