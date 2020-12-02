package javacore.professional.hw1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private static final int EMPTY_BOX_WEIGHT = 0;

    private final List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void pourInto(Box<T> box) {
        if (box == this) {
            return;
        }
        box.fruits.addAll(fruits);
        fruits.clear();
    }

    public boolean compare(Box<?> box) {
        return Float.compare(getWeight(), box.getWeight()) == 0;
    }

    public float getWeight() {
        return fruits.isEmpty() ? EMPTY_BOX_WEIGHT :
                fruits.get(0).getWeight() * fruits.size();
    }

    public int size() {
        return fruits.size();
    }
}
