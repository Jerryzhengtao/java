/**
 * @ClassName Foo
 * //TODO
 * @Author zt
 * @Date 2021/3/5 14:40
 **/
public class Foo {
    int i = 1;

    Foo() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}
