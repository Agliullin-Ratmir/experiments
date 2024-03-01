package multithreading;

import org.junit.jupiter.api.Test;


class ThreadLocalTest {

    @Test
    void testOne() {
        for (int i = 0; i < 5; i++) {
            Thread runner = new Thread(new ThreadLocalRunner("thread" + i, i));
            runner.start();
        }
    }

    class ThreadLocalRunner implements Runnable {
        private String threadName;
        private int index;

        public ThreadLocalRunner(String threadName, int index) {
            this.threadName = threadName;
            this.index = index;
        }

        @Override
        public void run() {
            ThreadLocal<Integer> contentThreadLocal = new ThreadLocal<>();
            contentThreadLocal.set(index);
            System.out.println("Thread name: " + threadName + " has index: " + contentThreadLocal.get());
            contentThreadLocal.set(contentThreadLocal.get() + 1);
            System.out.println("Thread name: " + threadName + " has new index: " + contentThreadLocal.get());
        }
    }
}
