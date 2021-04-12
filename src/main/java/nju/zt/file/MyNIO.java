package nju.zt.file;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * @ClassName MyNIO
 * //TODO
 * @Author zt
 * @Date 2021/3/2 16:43
 **/
public class MyNIO {
    public static void main(String[] args) {

        //创建一个Buffer
        //CharBuffer为抽象方法，无法实例化
        // 需要调用CharBuffer的allocate(int capacity)方法
        // allocate(int capacity)会实例化CharBuffer子类HeapCharBuffer(其访问权限是default，仅仅包可见)
        // 或者创建数组，然后调用wrap(Char[] arr)方法
        CharBuffer charBuffer = CharBuffer.allocate(100);
        char[] chars = {'A'};
        CharBuffer charBuffer1 = CharBuffer.wrap(chars);
        CharBuffer charBuffer2 = CharBuffer.wrap("hello buffer");
        byte[] bytes = {0,0,0,66};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        charBuffer1 = byteBuffer.asCharBuffer();
        System.out.println( byteBuffer.getInt());

    }
}
