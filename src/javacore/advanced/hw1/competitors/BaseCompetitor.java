package javacore.advanced.hw1.competitors;

import javacore.advanced.hw1.obstacles.Obstacle;

/**
 * As competitors cannot be inherited from a common super class so this class was introduced instead.
 * It contains common logic for all competitors.
 */
class BaseCompetitor implements Competitor {

    private final Competitor competitor;
    private final int maxRunDistance;
    private final int maxJumpHeight;

    public BaseCompetitor(Competitor competitor, int maxRunDistance, int maxJumpHeight) {
        this.competitor = competitor;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        boolean isCan = distance <= maxRunDistance;
        if (isCan) {
            System.out.printf("%-20s ran %d\n", competitor, distance);
        } else {
            System.out.printf("%-20s cannot run %d\n", competitor, distance);
        }
        return isCan;
    }

    @Override
    public boolean jump(int height) {
        boolean isCan = height <= maxJumpHeight;
        if (isCan) {
            System.out.printf("%-20s jumped %d\n", competitor, height);
        } else {
            System.out.printf("%-20s cannot jump %d\n", competitor, height);
        }
        return isCan;
    }

    @Override
    public void overcome(Obstacle[] obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (!obstacle.overcome(competitor)) {
                break;
            }
        }
    }
}
