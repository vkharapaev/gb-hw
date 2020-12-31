package javacore.advanced.hw1.obstacles;

import javacore.advanced.hw1.competitors.Competitor;

public class Treadmill implements Obstacle {
    private final int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Competitor competitor) {
        return competitor.run(distance);
    }
}
