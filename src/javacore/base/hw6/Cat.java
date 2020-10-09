package javacore.base.hw6;

public class Cat extends Animal {

    private static int catCount;
    private static final int RUNNING_MAX_DISTANCE = 200;
    private static final int SWIMMING_MAX_DISTANCE = 0;

    public Cat(String name) {
        super(name, RUNNING_MAX_DISTANCE, SWIMMING_MAX_DISTANCE);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }
}
