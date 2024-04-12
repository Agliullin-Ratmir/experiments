package org.example.multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * p. 132 - add implementation of ExecutorService with thread pool and socket???
 * curl -v http://localhost:1055/
 */
public class ExecutorServer {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        System.out.println("Server has been started!");
        TaskWebServer webServer = new TaskWebServer(latch, 1055, executorService);
        try {
            webServer.start();
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("it's done");
        executorService.shutdown();
    }

    static class TaskWebServer {


        CountDownLatch latch;
        int port;
        ExecutorService exec;

        public TaskWebServer(CountDownLatch latch, int port, ExecutorService exec) {
            this.latch = latch;
            this.port = port;
            this.exec = exec;
        }

        public void start() throws IOException {
            ServerSocket socket = new ServerSocket(port);
            while (!exec.isShutdown()) {
                try {
                    final Socket conn = socket.accept();
                    exec.execute(new Runnable() {
                        public void run() {
                            System.out.println("Got socket from port:" + conn.getPort()
                            + " and thread: " + Thread.currentThread().getName());
                            latch.countDown();
                            System.out.println("Amount of latches: " + latch.getCount());
                            if (latch.getCount() == 0) {
                                exec.shutdown();
                            }
                        }
                    });
                } catch (RejectedExecutionException e) {
                    if (!exec.isShutdown())
                        System.out.println("task submission rejected" + e);
                }
            }
        }
    }
}
