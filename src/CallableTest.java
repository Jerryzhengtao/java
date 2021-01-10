import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.*;
/**
 * @ClassName StringTest
 * @Description //TODO
 * @Author zt
 * @Date 2021/1/10 15:11
 * @Version 1.0
 **/
public class CallableTest implements Callable {
    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        Future future = executorService.submit(callableTest);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    @Override
    public Object call() throws Exception {

        return "sss";
    }
}
