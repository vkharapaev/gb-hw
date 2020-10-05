package javacore.base.hw6;

public class Cat extends Animal {
    private static int catCount;

    public Cat(String name) {
        super(name, 200, 0);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }
}
