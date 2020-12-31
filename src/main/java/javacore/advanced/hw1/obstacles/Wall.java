package javacore.advanced.hw1.obstacles;

import javacore.advanced.hw1.competitors.Competitor;

public class Wall implements Obstacle {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Competitor competitor) {
        return competitor.jump(height);
    }
}
