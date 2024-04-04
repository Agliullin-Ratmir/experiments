package org.example.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureExample {


    public static void main(String[] args) {
        CompletableFutureExample example = new CompletableFutureExample();
        System.out.println("Start");
        Future<String> completableFuture = example.getFuture();
        System.out.println("getFuture has been called");
        String result;
        try {
            result = completableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("finished: " + result);
    }

    Future<String> getFuture() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("inside future");
                    return "Hi";}
        );
        return completableFuture;
    }
}
