package javacore.base.hw7;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = CatGenerator.generate(5, 10, 20);
        Plate plate = new Plate(50);
        cats.forEach(cat -> cat.eat(plate));
        cats.forEach(Cat::printInfo);
        plate.printInfo();
    }
}
