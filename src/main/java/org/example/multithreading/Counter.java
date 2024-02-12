package org.example.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private int count = 0;
    private AtomicInteger atomicCount = new AtomicInteger(0);

    public synchronized int incAndGetNotAtomic() {
        count = count + 1;
        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

}
