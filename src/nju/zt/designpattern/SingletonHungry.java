package nju.zt.designpattern;
/**
 * @ClassName SignletonHungry
 * @Description //单例模式饿汉式
 * @Author zt
 * @Date 2021/1/23 13:27
 * @Version 1.0
 **/
public class SingletonHungry {
    private static SingletonHungry instance = new SingletonHungry();
    private SingletonHungry(){}
    public static SingletonHungry getInstance(){
        return instance;
    }
}