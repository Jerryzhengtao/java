package nju.zt.designpattern;

/**
 * @ClassName AbstractFactoryPattern
 * //抽象工厂模式
 * @Author zt
 * @Date 2021/1/31 18:02
 * @Version 1.0
 **/
public class AbstractFactoryPattern {
    static abstract class AbstractFactory {
        abstract public Television createTelevision();
        abstract public Aircondition createAircondition();
    }

    interface Television {
        void play();
    }

    interface Aircondition {
        void work();
    }

    static class TCLTelevision implements Television {
        @Override
        public void play() {
            System.out.println("海尔电视播放电视剧");
        }
    }

    static class HaierTelevision implements Television {
        @Override
        public void play() {
            System.out.println("TCL电视播放电视剧");
        }
    }

    static class TCLAircondition implements Aircondition {
        @Override
        public void work() {
            System.out.println("TCL空调正在工作");
        }
    }

    static class HaierAircondition implements Aircondition {
        @Override
        public void work() {
            System.out.println("海尔空调正在工作");
        }
    }

    static class HaierFactory extends AbstractFactory {
        @Override
        public Television createTelevision() {
            return new HaierTelevision();
        }

        @Override
        public Aircondition createAircondition() {
            return new HaierAircondition();
        }
    }

    static class TCLFactory extends AbstractFactory {
        @Override
        public Television createTelevision() {
            return new TCLTelevision();
        }

        @Override
        public Aircondition createAircondition() {
            return new TCLAircondition();
        }
    }

    public static void main(String[] args) {

        AbstractFactory tclFactory = new TCLFactory();
        AbstractFactory haierFactory = new HaierFactory();

        Television TCLTelevision = tclFactory.createTelevision();
        TCLTelevision.play();
        Aircondition TCLAircondition = tclFactory.createAircondition();
        TCLAircondition.work();

        Television haierTelevision = haierFactory.createTelevision();
        haierTelevision.play();
        Aircondition haierAircondition = haierFactory.createAircondition();
        haierAircondition.work();
    }

}
