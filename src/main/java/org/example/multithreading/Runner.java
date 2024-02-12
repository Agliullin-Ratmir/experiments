package org.example.multithreading;

public class Runner implements Runnable {

    public Runner(Counter counter, int index) {
        this.counter = counter;
        this.index = index;
    }

    private Counter counter;
    private int index;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + index +
                    " has the counter: " + counter.incAndGetNotAtomic());
        }
    }
}
