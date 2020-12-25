package javacore.professional.hw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private final List<Stage> stages;
    private final CyclicBarrier barrier;
    private final CountDownLatch preparationLatch;
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;
    private Car winner;

    public Race(int carsCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.barrier = new CyclicBarrier(carsCount);
        this.preparationLatch = new CountDownLatch(carsCount);
        this.startLatch = new CountDownLatch(1);
        this.finishLatch = new CountDownLatch(carsCount);
    }

    public Car getWinner() {
        return winner;
    }

    public void setWinner(Car winner) {
        this.winner = winner;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void awaitOthers() throws Exception {
        barrier.await();
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
