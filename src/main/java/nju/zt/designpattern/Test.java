package nju.zt.designpattern;

import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args) {
      Singleton singleton = Singleton.INSTANCE;
      Singleton singleton1 = Singleton.INSTANCE;
        System.out.println(singleton==singleton1);

    }
}
