package org.example.multithreading;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    private static final int AMOUNT = 100;

    private static int arr[] = new int[AMOUNT];

    public static void main(String[] args) {
        initArray();
        sumWithoutFork();
        long start = System.currentTimeMillis();
        SimpleCounter simpleCounter = new SimpleCounter(arr);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(simpleCounter);
        System.out.println("It takes with fork: " + (System.currentTimeMillis() - start));
    }

    private static void initArray() {
        for (int i = 0; i < AMOUNT; i++) {
            arr[i] = i;
        }
    }

    private static void sumWithoutFork() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < AMOUNT; i++) {
            sum = sum + arr[i];
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Sum without fork: " + sum);
        System.out.println("It takes without fork: " + (System.currentTimeMillis() - start));
    }

    static class SimpleCounter extends RecursiveTask<Integer> {

        private int[] array;

        public SimpleCounter(int[] array) {
            this.array = array;
        }


        @Override
        protected Integer compute() {
            if (array.length <= 2) {
                try {
                    Thread.sleep(20l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return Arrays.stream(array).sum();
            }
            SimpleCounter first = new SimpleCounter(Arrays.copyOfRange(array, 0, array.length/2));
            SimpleCounter second = new SimpleCounter(Arrays.copyOfRange(array, array.length/2, array.length));
            return 0;
        }
    }
}
