package multithreading;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/*
https://www.baeldung.com/java-deadlock-livelock
 */
class LivelockTest {

    @Test //fix later, doesn't work
    void testLivelock() {
        Lock lock1 = new ReentrantLock(true);
        Lock lock2 = new ReentrantLock(true);

        Runnable first = new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock1.tryLock(50l, TimeUnit.MILLISECONDS);
                        System.out.println("lock1 acquired, trying to acquire lock2.");
                        sleep(500);

                        if (lock2.tryLock(50l, TimeUnit.MILLISECONDS)) {
                            System.out.println("lock2 acquired.");
                        } else {
                            System.out.println("cannot acquire lock2, releasing lock1.");
                            lock1.unlock();
                            continue;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("executing first operation.");
                    break;
                }
                lock2.unlock();
                lock1.unlock();
            }
        };

        Runnable second = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock2.tryLock(50l, TimeUnit.MILLISECONDS);
                        System.out.println("lock2 acquired, trying to acquire lock1.");
                        sleep(500);

                        if (lock1.tryLock(50l, TimeUnit.MILLISECONDS)) {
                            System.out.println("lock1 acquired.");
                        } else {
                            System.out.println("cannot acquire lock1, releasing lock2.");
                            lock2.unlock();
                            continue;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("executing second operation.");
                    break;
                }
                lock1.unlock();
                lock2.unlock();
            }
        };

        new Thread(first, "First thread").start();
        new Thread(second, "Second thread").start();
    }

}
