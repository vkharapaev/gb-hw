package javacore.base.hw7;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    /**
     * Уменшает количесво еды в тарелке.
     *
     * @param n На сколько уменьшить количество еды в тарелке
     * @return На сколько уменшилось количество еды в тарелке
     */
    public int decreaseFood(int n) {
        int delta = Math.min(n, food);
        food -= delta;
        return delta;
    }

    /**
     * Увеличивает количесво еды в тарелке.
     *
     * @param n На сколько увеличить количество еды в тарелке
     */
    public void increaseFood(int n) {
        food += n;
    }

    public int availableFood() {
        return food;
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }
}
