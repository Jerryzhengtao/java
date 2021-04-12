package nju.zt.thread;

import java.util.concurrent.locks.Lock;

/**
 * @ClassName TwinsLockTest
 * //TODO
 * @Author zt
 * @Date 2021/3/10 23:16
 **/
public class TwinsLockTest {
    public static void main(String[] arg){
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            public void run(){
                while (true){
                    lock.lock();
                    try{
                    Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
