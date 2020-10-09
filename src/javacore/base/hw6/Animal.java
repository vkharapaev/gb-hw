package javacore.base.hw6;

public class Animal {

    private static int animalCount;

    private String name;
    private int runningMaxDistance;
    private int swimmingMaxDistance;

    public Animal(String name, int runningMaxDistance, int swimmingMaxDistance) {
        animalCount++;
        this.name = name;
        this.runningMaxDistance = runningMaxDistance;
        this.swimmingMaxDistance = swimmingMaxDistance;
    }

    public String getName() {
        return name;
    }

    public void run(int distance) {
        if (distance > runningMaxDistance) {
            System.out.printf("Я %s. Я не могу пробежать %d м. Слишком длинная дистанция.\n", name, distance);
        } else {
            System.out.printf("%s пробежал %d м.\n", name, distance);
        }
    }

    public void swim(int distance) {
        if (swimmingMaxDistance == 0) {
            System.out.printf("Я %s. Я не умею плавать.\n", name);
        } else if (distance > swimmingMaxDistance) {
            System.out.printf("Я %s. Я не могу проплыть %d м. Слишком длинная дистанция.\n", name, distance);
        } else {
            System.out.printf("%s проплыл %d м.\n", name, distance);
        }
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}
