package org.example.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        int count = 8;
        CountDownLatch latch = new CountDownLatch(count);
        CommonResource res = new CommonResource();
        ExecutorService executorService = Executors.newFixedThreadPool(count/2);
        for (int i = 0; i < count; i++) {
            executorService.execute(new CountThread(res, latch, "CountThread" + i));
        }
        latch.await();
        System.out.println("Result: " + res.x);
        executorService.shutdown();

    }

    static class CommonResource {

        int x = 0;
    }

    static class CountThread implements Runnable {

        CommonResource res;
        CountDownLatch latch;
        String name;

        public CountThread(CommonResource res, CountDownLatch latch, String name) {
            this.res = res;
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("Thread with name: " + name + " acquire control");
            res.x++;
            latch.countDown();
            System.out.println("Thread with name: " + name + " release control, "
            + "current latch is: " + latch.getCount());
        }
    }
}
