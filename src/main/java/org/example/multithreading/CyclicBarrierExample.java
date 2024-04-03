package org.example.multithreading;

import java.util.concurrent.*;

public class CyclicBarrierExample {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int count = 8;
        CommonResource res = new CommonResource();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(new CountThread(res, cyclicBarrier, "CountThread" + i));
        }
        executorService.shutdown();
    }

    static class CommonResource {

        int x = 0;
    }

    static class CountThread implements Runnable {

        CommonResource res;
        String name;
        CyclicBarrier cyclicBarrier;

        public CountThread(CommonResource res, CyclicBarrier cyclicBarrier, String name) {
            this.res = res;
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("Thread with name: " + name + " acquire control");
            res.x++;
            System.out.println("Thread with name: " + name + " has incremented counter to: " + res.x);
            try {
                System.out.println(name
                        + " waiting for others to reach barrier.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
