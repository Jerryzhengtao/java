package nju.zt.thread.pc;

/**
 * @ClassName BySynchronized
 * @Description //同步块（同步方法）解决消费者和生产者问题，wait，notify
 * @Author zt
 * @Date 2021/1/10 23:10
 * @Version 1.0
 **/
public class BySynchronized {
    static class Product {
        private int count = 0;

        public synchronized void produce() {
            while (count >= 15) {
                try {
                    System.out.println("Full");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            count++;
            System.out.println(Thread.currentThread().getName() +
                    " produced 1 product, now contains " +
                    count);
            this.notifyAll();
        }

        public synchronized void consume() {
            while (count == 0) {
                try {
                    System.out.println("Empty");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            System.out.println(Thread.currentThread().getName() +
                    " consuming 1 product, now contains " +
                    count);
            this.notifyAll();
        }
    }
    static class Producer implements Runnable {
        private Product product;
        private int i;

        public Producer(Product product,int i) {
            this.product = product;
            this.i = i;
        }

        @Override
        public void run() {
            while (i>0) {
                product.produce();
                i--;
            }
        }
    }
    static class Consumer implements Runnable {
        private Product product;
        private int i;

        public Consumer(Product product,int i) {
            this.product = product;
            this.i = i;
        }

        @Override
        public void run() {
            while (i>0) {
                product.consume();
                i--;
            }
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime()/1000000;
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
                end = System.nanoTime()/1000000;
                break;
            }
        }
        System.out.println(end - start);
    }
}
