package multithreading;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockTest {

    public static void main(String[] args) {


        CommonResource commonResource = new CommonResource();
        ReentrantLock locker = new ReentrantLock();
        for (int i = 1; i < 6; i++) {

            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Thread " + i);
            t.start();
        }
    }
}

    class CommonResource{

        int x=0;
    }

    class CountThread implements Runnable {

        CommonResource res;
        ReentrantLock locker;
        CountThread(CommonResource res, ReentrantLock lock){
            this.res=res;
            locker = lock;
        }
        public void run() {

            locker.lock();
            try{
                res.x=1;
                for (int i = 1; i < 5; i++){
                    System.out.println(Thread.currentThread().getName() + " " + res.x);
                    res.x++;
                    Thread.sleep(100);
                }
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            finally{
                locker.unlock(); // снимаем блокировку
            }
        }
}
