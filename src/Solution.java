import nju.zt.designpattern.SingletonHungry;
import nju.zt.designpattern.SingletonLazy;

public class Solution {

    public static void main(String[] args) {
        SingletonLazy singletonLazy1 = SingletonLazy.getInstance();
        SingletonLazy singletonLazy2 = SingletonLazy.getInstance();
        SingletonHungry singletonHungry1 = SingletonHungry.getInstance();
        SingletonHungry singletonHungry2 = SingletonHungry.getInstance();
        System.out.println(singletonHungry1==singletonHungry2);
        System.out.println(singletonLazy1==singletonLazy2);

    }
}