package nju.zt.designpattern;

import java.util.zip.CheckedOutputStream;

/**
 * @ClassName StrategyPattern
 * @Description //策略模式
 * @Author zt
 * @Date 2021/1/31 17:01
 * @Version 1.0
 **/
public class StrategyPattern {
    interface Strategy {
        int doOperation(int a, int b);
    }

    static class OperationAdd implements Strategy {
        @Override
        public int doOperation(int a, int b) {
            return a + b;
        }
    }

    static class OperationSub implements Strategy {
        @Override
        public int doOperation(int a, int b) {
            return a - b;
        }
    }

    static class OperationMul implements Strategy {
        @Override
        public int doOperation(int a, int b) {
            return a * b;
        }
    }

    static class OperationDiv implements Strategy {
        @Override
        public int doOperation(int a, int b) {
            try {
                return a / b;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    static class Context {
        private Strategy strategy;

        public Context(Strategy strategy) {
            this.strategy = strategy;
        }

        public int executeStrategy(int a, int b) {
            return strategy.doOperation(a, b);
        }

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }
    }

    public static void main(String[] args) {

        try {
            int a = 2, b = 3;
            Class clazz = Class.forName("nju.zt.designpattern.StrategyPattern$OperationAdd");
            Strategy strategy = (Strategy) clazz.newInstance();
            // Strategy strategy = new OperationSub();
            // Strategy strategy = new OperationMul();
            // Strategy strategy = new OperationDiv();

            Context context = new Context(strategy);
            System.out.println(context.executeStrategy(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
