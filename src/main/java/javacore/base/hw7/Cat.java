package javacore.base.hw7;

public class Cat {

    private String name;
    private int appetite;
    private boolean hungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void eat(Plate p) {
        if (hungry && p.availableFood() >= appetite) {
            p.decreaseFood(appetite);
            hungry = false;
        }
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", hungry=" + hungry +
                '}';
    }
}
