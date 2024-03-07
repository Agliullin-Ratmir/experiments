package multithreading;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;


/*
https://www.baeldung.com/java-deadlock-livelock
 */
class DeadlockTest {

    @Test
    void testDeadlock() {
        Lock lock1 = new ReentrantLock(true);
        Lock lock2 = new ReentrantLock(true);

        Runnable first = new Runnable() {
            @Override
            public void run() {
                lock1.lock();
                System.out.println("lock1 acquired, waiting to acquire lock2.");
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                lock2.lock();
                System.out.println("lock2 acquired");

                System.out.println("executing first operation.");

                lock2.unlock();
                lock1.unlock();
            }
        };

        Runnable second = new Runnable() {
            @Override
            public void run() {
                lock2.lock();
                System.out.println("lock2 acquired, waiting to acquire lock1.");
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                lock1.lock();
                System.out.println("lock1 acquired");

                System.out.println("executing second operation.");

                lock1.unlock();
                lock2.unlock();
            }
        };

        new Thread(first, "First thread").start();
        new Thread(second, "Second thread").start();
    }

}
