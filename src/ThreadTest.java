import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadTest
 * @Description //TODO
 * @Author zt
 * @Date 2021/1/8 21:43
 * @Version 1.0
 **/
public class ThreadTest extends Thread{
    private static int tickets = 10;

    @Override
    public synchronized void run() {

            while (true) {
                if (tickets > 0) {
                    System.out.println(getName() + " 卖票：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }

    public static void main(String[] args) {
        Thread t1 = new ThreadTest();
        Thread t2 = new ThreadTest();
        Thread t3 = new ThreadTest();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}
