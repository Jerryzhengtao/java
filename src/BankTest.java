/**
 * @ClassName Bank
 * @Description //TODO
 * @Author zt
 * @Date 2021/1/9 22:05
 * @Version 1.0
 **/
public class BankTest {

}

class Bank {
    // 单例懒汉式 线程安全问题
    private Bank() {
    }

    private static Bank instance = null;

    public static Bank getInstance() {

        if (instance == null) ;
        {
            instance = new Bank();
        }
        return instance;
    }

}
