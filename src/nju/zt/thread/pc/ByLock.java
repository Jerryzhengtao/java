package nju.zt.thread.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ByLock
 * @Description //显示加锁解决消费者生产者问题，await，signal
 * @Author zt
 * @Date 2021/1/10 23:43
 * @Version 1.0
 **/
public class ByLock {
    static class Product {
        private int count = 0;
        private ReentrantLock lock = new ReentrantLock();
        private Condition full = lock.newCondition();//管理生产者
        private Condition empty = lock.newCondition();//管理消费者

        public void produce() {
            lock.lock();
            while (count >= 15) {
                try {
                    System.out.println("Full");
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " produced 1 product, now contains " + count);
            empty.signal();
            lock.unlock();
        }

        public void consume() {
            lock.lock();
            while (count == 0) {

                try {
                    System.out.println("Empty");
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            System.out.println(Thread.currentThread().getName() +
                    " consumed 1 product, now contains " +
                    count);
            full.signal();
            lock.unlock();

        }
    }

    static class Producer implements Runnable {
        private Product product;
        private int i;

        /**
         * @return
         * @MethodName Producer
         * @Description //TODO
         * @Date 2021/1/11 1:20
         * @Param product ，i // loops
         **/
        public Producer(Product product, int i) {
            this.product = product;
            this.i = i;
        }

        @Override
        public void run() {
            while (i > 0) {
                product.produce();
                i--;
            }
        }
    }

    static class Consumer implements Runnable {
        private Product product;
        private int i;

        public Consumer(Product product, int i) {
            this.product = product;
            this.i = i;
        }

        @Override
        public void run() {
            while (i > 0) {
                product.consume();
                i--;
            }
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime() / 1000000;  //简单测试一下运行时间
        long end;
        Product product = new Product();

        Producer producer1 = new Producer(product, 3000000);
        Producer producer2 = new Producer(product, 3000001);  //多一次，检测最后剩余产品
        Producer producer3 = new Producer(product, 3000000);
        Consumer consumer1 = new Consumer(product, 3000000);
        Consumer consumer2 = new Consumer(product, 3000000);
        Consumer consumer3 = new Consumer(product, 3000000);

        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(producer2);
        Thread t3 = new Thread(producer3);

        Thread t4 = new Thread(consumer1);
        Thread t5 = new Thread(consumer2);
        Thread t6 = new Thread(consumer3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        while (true) {
            if (t1.getState() == Thread.State.TERMINATED &&
                    t2.getState() == Thread.State.TERMINATED &&
                    t3.getState() == Thread.State.TERMINATED &&
                    t4.getState() == Thread.State.TERMINATED &&
                    t5.getState() == Thread.State.TERMINATED &&
                    t6.getState() == Thread.State.TERMINATED) {
                end = System.nanoTime() / 1000000;
                break;
            }
        }
        System.out.println(end - start);
    }
}

