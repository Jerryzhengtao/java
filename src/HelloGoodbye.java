
public class HelloGoodbye {
    static public final int i = 2;
    public int j;
    static {
        System.out.println("static");
    }

    public static void out() {
        System.out.println("static method");
    }

    public HelloGoodbye() {
        System.out.println("init");
    }
}