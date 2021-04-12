package nju.zt.file;

import jdk.internal.util.xml.impl.Input;

import javax.swing.text.html.CSS;
import java.io.*;

/**
 * @ClassName FIle
 * //TODO
 * @Author zt
 * @Date 2021/3/2 15:17
 **/
public class FileTest {

    public static void main(String[] args) throws IOException {
        //当前系统下的路径分隔符 如：";"
        //  System.out.println(File.pathSeparatorChar);
        //  System.out.println(File.pathSeparator);
        //当前系统下的名称分隔符 如："\"
        //  System.out.println(File.separatorChar);
        //  System.out.println(File.separator);
        //从路径创建file实例
        File file = new File("D:\\W" +
                "\\WorkSpace\\javal\\src\\test.txt");

        //创建目录
        File dir = new File("D:\\W\\WorkSpace\\" +
                "javal\\src\\dirtest");
        dir.mkdir();

        //创建文件
        File file1 = new File("D:\\W\\WorkSpace\\" +
                "javal\\src\\dirtest\\filetest.txt");
        //  System.out.println(file.createNewFile());

        //打印给定目录下的所有文件
        File[] files = new File("D:\\W\\WorkSpace\\" +
                "javal").listFiles();
      /*
        for(File i:files)
        {
            System.out.println(i);
        }

       */
        //字节流
        //FileInputStream
        //创建目标对象
        File target = new File("WorkSpace.iml");
        InputStream in = new FileInputStream(target);

        //IO操作
        //in.read();读取一个字节
        //in.read(byte[] b)读取字节保存在 byte数组中
        //in.read(byte[] b,int i,int j ) 读取多个字节，从b的i开始存，连续读取j个字节
        byte[] buffer = new byte[100];
        in.read(buffer, 1, 3);
       /* for (int j = 0; j < buffer.length; j++) {
            System.out.print((char) buffer[j]);
        }

        */
        //关闭流
        in.close();
        //OutputStream
        OutputStream out = new FileOutputStream("test.txt");
        //IO操作
        //out.write(int b); 把一个字节写入文件；
        //out.write("AB".getBytes());把AB写入文件
        //out.write("ABcd".getBytes(),1,2);把Bc写入文件

        //以下代码将BABBc写入test.txt，采取覆盖模式
        out.write(66);
        out.write(("ABssssssssssssssssssssssssssss\n" +
                "ssssssssss").getBytes());
        out.write("ABcd".getBytes(), 1, 2);

        //关闭流
        out.close();

        //用字节流完成文件的复制
        File srcFile = new File("test.txt");
        File desFile = new File("des.txt");
        desFile.createNewFile();

        InputStream inputStream = new FileInputStream(srcFile);
        OutputStream outputStream = new FileOutputStream(desFile);
        byte[] bytes = new byte[100]; //存储读取的内容
        int len = -1; //记录已经读取字节的数量
        while((len=inputStream.read(bytes))!=-1){
            //打印读取的数据
       //     System.out.println(new String(bytes,0,len));
            outputStream.write(bytes,0,len);
        }
        inputStream.close();
        outputStream.close();

        //字符流
        //Reader,Writer
        //汉字不止一个字节，使用字节流时汉字会出现问题，于是可以使用字符流，
        // 文本文件使用字符流，二进制使用字节流

        //FileWriter
        Writer writer = new FileWriter(srcFile);
        //IO操作
        /*
        write(int c);向外写出一个字符
        write(char[] buffer); 向外写出多个字符
        write(char[] buffer,int off,int len);向外从off开始写字符，总共写len长度
        write(String str);向外写出字符串
         */
        writer.write(65);//写A
        writer.write("as写入");
        writer.close();

        //FileReader
        Reader reader = new FileReader(srcFile);
        //IO
        //读取字符
        /*
        while ((len=reader.read())!=-1){
            System.out.println((char)len);
        }

         */

        char[] chars = new char[10];
        while ((len=reader.read(chars))!=-1){
            System.out.println(new String(chars,0,len));
        }
        reader.close();

        //包装流
        //我们在将字符输入输出流、字节输入输出流的时候，读取操作，
        // 通常都会定义一个字节或字符数组，将读取/写入的数据先存放到这个数组里面，
        // 然后在取数组里面的数据。这比我们一个一个的读取/写入数据要快很多，
        // 而这也就是缓冲流的由来。只不过缓冲流里面定义了一个
        // 数组用来存储我们读取/写入的数据，当内部定义的数组满了
        // （注意：我们操作的时候外部还是会定义一个小的数组，小数组放入到内部数组中），
        // 就会进行下一步操作。
        BufferedInputStream bufferedOutputStream =
                new BufferedInputStream(new FileInputStream(srcFile));


    }
}
