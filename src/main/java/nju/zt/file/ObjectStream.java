package nju.zt.file;


import java.io.*;
import java.sql.SQLOutput;

/**
 * @ClassName ObjectStream
 * //TODO
 * @Author zt
 * @Date 2021/3/8 17:13
 **/
public class ObjectStream {
    //序列化
    //将内存中的java对象保存到磁盘或者网络传输出去
    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;

   static class Person implements Serializable{
        static final long serialVersionUID = 123134534L;
        String name;
        int age;
        public Person(String name,int age)
        {
            this.name = name ;
            this.age = age;
        }
        public void print(){
            System.out.println("name: "+name+"\nage: "+age);
        }
    }

    public static void main(String[] args) {
        testOutputStream();
        testInputStream();
    }

    public static void testOutputStream() {
        try {
            Person person = new Person("zt",22);
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("stream.data"));
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testInputStream() {

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("stream.data"));
            Object obj = objectInputStream.readObject();
            Person person = (Person)obj;
            System.out.println(person);
            person.print();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
