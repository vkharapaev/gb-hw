package javacore.professional.hw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Race {
    private final List<Stage> stages;
    private final CountDownLatch preparationLatch;
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;
    private Car winner;

    public Race(int participantCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.preparationLatch = new CountDownLatch(participantCount);
        this.startLatch = new CountDownLatch(1);
        this.finishLatch = new CountDownLatch(participantCount);
    }

    public synchronized void setWinner(Car winner) {
        if (this.winner == null) {
            this.winner = winner;
            System.out.println(winner.getName() + " - WIN");
        }
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void awaitPreparation() throws InterruptedException {
        preparationLatch.await();
    }

    public void prepare() {
        preparationLatch.countDown();
    }

    public void awaitStart() throws InterruptedException {
        startLatch.await();
    }

    public void start() {
        startLatch.countDown();
    }

    public void awaitFinish() throws InterruptedException {
        finishLatch.await();
    }

    public void finish() {
        finishLatch.countDown();
    }

}
