package nju.zt.designpattern;
/**
 * @ClassName SignletonLazy
 * @Description //单例模式懒汉式
 * @Author zt
 * @Date 2021/1/23 13:27
 * @Version 1.0
 **/
public class SingletonLazy {
    private static volatile SingletonLazy instance = null;

    private SingletonLazy() {
    }

    /**
     * public static SingletonLazy getInstance(){
     * if(instance==null)
     * {
     * instance = new SingletonLazy();
     * }
     * return instance;
     * }
     **/

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}