package javacore.advanced.hw1.obstacles;

import javacore.advanced.hw1.competitors.*;

public interface Obstacle {

    /**
     * It forces a competitor to overcome this obstacle.
     *
     * @param competitor A competitor that has to overcome this obstacle.
     * @return True if a competitor can overcome this obstacle.
     */
    boolean overcome(Competitor competitor);
}
