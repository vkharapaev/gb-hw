package javacore.base.hw7;

import java.util.List;

public class Main {

    public static final int MIN_APPETITE = 10;
    public static final int MAX_APPETITE = 20;
    public static final int CAT_COUNT = 5;
    public static final int FOOD_ON_PLATE = 50;

    public static void main(String[] args) {
        List<Cat> cats = CatGenerator.generate(CAT_COUNT, MIN_APPETITE, MAX_APPETITE);
        Plate plate = new Plate(FOOD_ON_PLATE);
        cats.forEach(cat -> cat.eat(plate));
        cats.forEach(Cat::printInfo);
        plate.printInfo();
    }
}
