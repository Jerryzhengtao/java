package nju.zt.designpattern;

public class ProxyStatic {
    interface Subject {
        void doSomething();
    }

    static class Target implements Subject {
        @Override
        public void doSomething() {
            System.out.println("target doSomething");
        }
    }

    static class TargetProxy implements Subject {
        Subject target;

        public TargetProxy(Subject target) {
            this.target = target;
        }

        @Override
        public void doSomething() {
            System.out.println("before");
            target.doSomething();
            System.out.println("after");
        }
    }

    public static void main(String[] args) {
        //实例化被代理对象
        Target target = new Target();
        //构建代理对象
        TargetProxy proxy = new TargetProxy(target);
        //执行代理对象的相应方法，可以看出，一个代理类只能为一个类服务，
        // 如果需要代理大量类，那就会编写大量繁杂代码，效率低下
        proxy.doSomething();
    }
}
