package javacore.advanced.hw1.competitors;

import javacore.advanced.hw1.obstacles.Obstacle;

public class Android implements Competitor {

    private final BaseCompetitor base;

    public Android(int maxRunDistance, int maxJumpHeight) {
        base = new BaseCompetitor(this, maxRunDistance, maxJumpHeight);
    }

    @Override
    public boolean run(int distance) {
        return base.run(distance);
    }

    @Override
    public boolean jump(int height) {
        return base.jump(height);
    }

    @Override
    public void overcome(Obstacle[] obstacles) {
        base.overcome(obstacles);
    }

    @Override
    public String toString() {
        return "Android@" + Integer.toHexString(hashCode());
    }
}
