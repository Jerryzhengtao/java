package nju.zt.thread.pc;

import java.util.concurrent.Semaphore;

/**
 * @ClassName BySemaphore
 * @Description //信号量解决消费者生产者问题
 * @Author zt
 * @Date 2021/1/11 0:14
 * @Version 1.0
 **/
public class BySemaphore {

    static class Product {
        private int count = 0;
        private Semaphore notFull = new Semaphore(15); //生产者还能生产多少产品（缓冲区剩余空间）
        private Semaphore notEmpty = new Semaphore(0); //消费者可以消费的产品（缓冲区已经装填的空间）
        private Semaphore mutex = new Semaphore(1);// 缓冲区互斥

        public void produce() {
            try {
                notFull.acquire();
                mutex.acquire();
                count++;
                System.out.println(Thread.currentThread().getName() +
                        " produced 1 product, now contains " +
                        count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notEmpty.release();
            }

        }

        public void consume() {
            try {
                notEmpty.acquire();
                mutex.acquire();
                count--;
                System.out.println(Thread.currentThread().getName() +
                        " consumed 1 product, now contains " +
                        count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notFull.release();
            }
        }
    }

    static class Producer implements Runnable {
        private Product product;
        private int i;

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
        long start = System.nanoTime() / 1000000;
        long end;
        Product product = new Product();

        Producer producer1 = new Producer(product, 3000000);
        Producer producer2 = new Producer(product, 3000001);
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
