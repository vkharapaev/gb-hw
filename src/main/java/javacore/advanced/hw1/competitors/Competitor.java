package javacore.advanced.hw1.competitors;

import javacore.advanced.hw1.obstacles.Obstacle;

public interface Competitor {

    /**
     * It forces the competitor to run a distance.
     *
     * @param distance Distance that should be run
     * @return True if can run it else false
     */
    boolean run(int distance);

    /**
     * It forces the competitor to jump over a height.
     *
     * @param height Height that should be overcome
     * @return True if can run it else false
     */
    boolean jump(int height);

    /**
     * It forces a competitor to overcome obstacles.
     *
     * @param obstacles An obstacle course.
     */
    void overcome(Obstacle[] obstacles);
}
