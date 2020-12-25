package javacore.professional.hw5;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + ++CARS_COUNT;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            race.awaitOthers();
            race.prepare();
            race.awaitStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        synchronized (race) {
            if (race.getWinner() == null) {
                race.setWinner(this);
                System.out.println(this.name + " - WIN");
            }
        }

        race.finish();
    }
}
