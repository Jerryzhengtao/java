package nju.zt.thread.wr;

import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName solution
 * @Description //TODO
 * @Author zt
 * @Date 2021/1/11 1:13
 * @Version 1.0
 **/
public class solution {
    public static void main(String[] args) {
        final CyclicBarrier num = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("hello");
                        num.await();
                        System.out.println("world");
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
    }
}
