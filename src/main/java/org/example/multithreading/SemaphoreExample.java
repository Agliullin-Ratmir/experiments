package org.example.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class SemaphoreExample {

    public static void main(String[] args) {
        int count = 2;
        Semaphore sem = new Semaphore(2); // 1 разрешение
        CommonResource res = new CommonResource();
        ExecutorService executorService = Executors.newFixedThreadPool(2 * count);
        for (int i = 0; i < 3 * count; i++) {
            executorService.execute(new CountThread(res, sem, "CountThread " + i));
        }
        executorService.shutdown();
    }

    static class CommonResource {

        int x = 0;
    }

    static class CountThread implements Runnable {

        CommonResource res;
        Semaphore sem;
        String name;

        CountThread(CommonResource res, Semaphore sem, String name) {
            this.res = res;
            this.sem = sem;
            this.name = name;
        }

        public void run() {

            try {
                System.out.println(name + " ожидает разрешение");
                sem.acquire();
                res.x = 1;
                for (int i = 1; i < 5; i++) {
                    System.out.println(this.name + ": " + res.x);
                    res.x++;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(name + " освобождает разрешение");
            sem.release();
        }

    }
}
