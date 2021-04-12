package nju.zt.designpattern;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDynamic {
    interface Subject {
        void doSomething();
    }

    static class SubjectImpl implements Subject {
        @Override
        public void doSomething() {
            System.out.println("do some thing");
        }
    }

    //实现InvocationHandler
    static class MyHandler implements InvocationHandler {

        private Object object;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            method.invoke(object, args);
            System.out.println("after");
            return null;
        }

        public Object newProxyInstance(Object object) {
            this.object = object;
            return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                    object.getClass().getInterfaces(),
                    this);
        }
    }

    public static void main(String[] args) {
       /*
        MyHandler handler = new MyHandler();
        SubjectImpl target = new SubjectImpl();
        Subject proxy = (Subject) handler.newProxyInstance(target);
        proxy.doSomething();
        */

        Subject proxy = (Subject)new SubjectProxy().newProxyInstance(new SubjectImpl());
        proxy.doSomething();


    }

    //cglib
    static class SubjectProxy implements MethodInterceptor{
        private Object object;

        public Object newProxyInstance(Object object){
            this.object=object;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(object.getClass());
            enhancer.setCallback(this);
            return enhancer.create();

        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("before");
            method.invoke(object,args);
            System.out.println("after");
            return null;
        }
    }

}
