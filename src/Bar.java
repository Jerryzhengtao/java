/**
 * @ClassName Bar
 * //TODO
 * @Author zt
 * @Date 2021/3/5 14:40
 **/
public class Bar extends Foo {

    int j = 1;

    Bar() {
        j = 2;
    }

    {
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }
}


