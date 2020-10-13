package javacore.base.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс для генерации котов
 */
public class CatGenerator {

    public static final String CAT_NAME = "Cat %s";

    /**
     * @param count       Количество котов
     * @param minAppetite Минимальный аппетит
     * @param maxAppetite Максимальный аппетит
     */
    public static List<Cat> generate(int count, int minAppetite, int maxAppetite) {
        List<Cat> cats = new ArrayList<>(count);
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int appetite = random.nextInt(maxAppetite - minAppetite) + minAppetite;
            cats.add(new Cat(String.format(CAT_NAME, i), appetite));
        }
        return cats;
    }
}
