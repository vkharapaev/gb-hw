package javacore.base.hw6;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random r = new Random();
        Animal[] animals = {new Cat("Барсик"), new Dog("Шарик"), new Dog("Бобик")};
        for (int i = 0; i < 3; i++) {
            for (Animal animal : animals) {
                animal.run(r.nextInt(1000));
                animal.swim(r.nextInt(20));
                System.out.println("------------------");
            }
        }

        System.out.printf("Животных %d, Кошек %d, Собак %d", Animal.getAnimalCount(), Cat.getCatCount(), Dog.getDogCount());
    }

}
