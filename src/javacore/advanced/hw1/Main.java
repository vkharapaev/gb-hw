package javacore.advanced.hw1;

import javacore.advanced.hw1.competitors.Android;
import javacore.advanced.hw1.competitors.Cat;
import javacore.advanced.hw1.competitors.Competitor;
import javacore.advanced.hw1.competitors.Human;
import javacore.advanced.hw1.obstacles.Obstacle;
import javacore.advanced.hw1.obstacles.Treadmill;
import javacore.advanced.hw1.obstacles.Wall;

public class Main {
    public static void main(String[] args) {

        Competitor[] competitors = {
                new Cat(80, 50),
                new Android(70, 20),
                new Human(100, 100)
        };

        Obstacle[] obstacles = {
                new Treadmill(80),
                new Wall(40),
                new Treadmill(90),
                new Wall(100)
        };

        for (Competitor competitor : competitors) {
            competitor.overcome(obstacles);
        }

    }
}
