package nju.zt.thread;

import com.sun.deploy.util.Waiter;
import nju.zt.file.Test;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName MyThread
 * //TODO
 * @Author zt
 * @Date 2021/3/7 14:49
 **/
public class MyThread {
    private static Object Integer;

    public static void main(String[] args) {
        test();
    }
    public static void test() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                    countDownLatch.countDown();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("world");
                }
            }).start();

        }

    }

    public static  void test2() {
        //创建线程池
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
                int result = 2;
            }
        };

        Callable task2 = new Callable() {
            @Override
            public Object call() throws Exception {
                return 1 + 2;
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(10);
        ExecutorService es2 = Executors.newCachedThreadPool();
        ExecutorService es3 = Executors.newScheduledThreadPool(2);
        ExecutorService es4 = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.DAYS, new LinkedBlockingDeque<Runnable>());
        Future future = es.submit(task);
        Future future1 = es.submit(task2);
        ReentrantLock reentrantLock;
        ReentrantReadWriteLock reentrantReadWriteLock;


        try {

            System.out.println(future.get());
            System.out.println(future1.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
