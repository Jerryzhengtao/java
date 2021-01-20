import java.util.ArrayList;
import java.util.List;

public class GenericTypes{
    public static void main(String[]args){
        System.out.println(HelloGoodbye.i);

        try {
            Thread.currentThread().getContextClassLoader().loadClass("HelloGoodbye");
            System.out.println("true");
        } catch (ClassNotFoundException e) {
            System.out.println("false");
        }
        HelloGoodbye helloGoodbye = new HelloGoodbye();
        HelloGoodbye.out();

        try {
            Thread.currentThread().getContextClassLoader().loadClass("HelloGoodbye");
            System.out.println("true");
        } catch (ClassNotFoundException e) {
            System.out.println("false");
        }

    }

}