package org.example;

import org.example.multithreading.Counter;
import org.example.multithreading.Runner;

public class Main {
    public static void main(String[] args) {
        incAndGet();
    }

    private static void incAndGet() {
        Counter counter = new Counter();
        for (int i = 0; i < 30; i++) {
            Thread runner = new Thread(new Runner(counter, i));
            runner.start();
        }
    }
}