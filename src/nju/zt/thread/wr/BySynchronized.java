package nju.zt.thread.wr;

/**
 * @ClassName BySynchronized
 * @Description //读者写者问题，写者优先解法
 * @Description ①：写者与写者互斥，不能同时写
 * @Description ②：写者和读者互斥，写（读）的过程中不能读（写）
 * @Description ③：读者直接不互斥，可以多个线程同时读
 * @Description ④：写者优先，写者到来时，要求尽快写完
 *                         也就是已经在读的读者读完要把执行权让给写者
 *                         而还没有开始写的读者直接wait，等写者写完再重新请求执行权
 * @Author zt
 * @Date 2021/1/12 16:56
 * @Version 1.0
 **/
public class BySynchronized {
    static class MyFile {
        private int data = 0; //文件数据，没啥用
        private int c = 0; //输出找bug用，用来表示有多少个已经进入，但还没有开始读的读者进程，这种进程将会阻塞以等待写者写完（如果有）
        private int writerCount = 0; //写者线程数
        private int readerCount = 0;  //正在读的读者数
        volatile boolean hasWriter = false;
        volatile boolean isReading = false;
        private Object writeMutex = new Object(); //用于写者间互斥共享变量writerCount和hasWriter
        private Object readMutex = new Object(); //用于读者间互斥共享变量readerCount和isReading
        private Object fileMutex = new Object(); //用于文件互斥，也就是写写互斥和读写互斥

        public void write() {
            //写者一进来就hasWriter = true，读者就能检测到,于是还没有开始读的但已经进入的线程就wait
            synchronized (writeMutex) {
                System.out.println(Thread.currentThread().getName() + " 进来"+
                        ",现有写者" + writerCount + "个" +
                        ",现有"+ readerCount + "个读者正在读");
                hasWriter = true;
                writerCount++;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (fileMutex) {
                while (true) {
                    if (isReading) {
                        //有读者正在读，写者等待读者读完
                        System.out.println(Thread.currentThread().getName() + " 等待读者读完"+
                                ",现有写者" + writerCount + "个" +
                                ",现有"+ readerCount + "个读者正在读");
                        try {
                            fileMutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //读者读完，现在开始写
                        System.out.println("没有读者，或读者读完，" + Thread.currentThread().getName() + "开始写"+
                                ",现有写者" + writerCount + "个" +
                                ",现有"+ c + "个读者在等待");
                        break;
                    }
                }
                int temp = data++;
                System.out.println(Thread.currentThread().getName() + " 写：将" + temp + " 写为：" + data+"现在有"
                        + c + "个读者在等待");
                fileMutex.notifyAll();
            }
            synchronized (writeMutex) {
                writerCount--;
                System.out.println(Thread.currentThread().getName() + " 写完 " +
                        ",现有" + writerCount + "个写者要写" +"现在有"
                        + c + "个读者在等待");
                if (writerCount == 0) {
                    //写者全部写完才能让读者读
                    hasWriter = false;
                    System.out.println("所有写者写完");
                }
            }
        }

        public void read() {
            //只要有写者正在写（或者想写)，读者就不能开始读, 若读者正在读时，出现了写者，那读者读完就要退出
            synchronized (fileMutex) {
                c++;
                System.out.println(Thread.currentThread().getName() + " 进来" +
                        ",现有写者" + writerCount + "个" +
                        ",现有"+ c + "个读者在等待");
                while (true) {
                    if (hasWriter) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "等待写者写完" +
                                    ",现有写者" + writerCount + "个" +
                                    ",现有"+ c + "个读者在等待");
                            fileMutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //没有writer了，就可以开始读，isReading = true 使得写者不能写
                        synchronized (writeMutex) {
                            isReading = true;
                            readerCount++;
                            System.out.println("没有写者，或写者写完，" + Thread.currentThread().getName() + "开始读"+
                                    ",现有写者" + writerCount + "个" +
                                    ",现有"+ readerCount + "个读者正在读");
                            fileMutex.notifyAll();
                            break;
                        }
                    }
                }
            }
            /**
             * 开始读
             * 此时isreading = true， haswriter = flase
             * 此线程读文件的过程中，若另一个写者来
             * ① 另一个读者已经经过haswriter判断，则和此线程同时读，读完后两个读者都退出
             * ② 另一个读者还在haswriter，那么将直接wait
             **/
            System.out.println(Thread.currentThread().getName() + " 正在读文件"+
                    ",现有写者" + writerCount + "个" +
                    ",现有"+ readerCount + "个正在读");

            synchronized (readMutex) {
                readerCount--;
                c--;
                System.out.println(Thread.currentThread().getName() + " 读完"+
                        ",现有写者" + writerCount + "个" +
                        ",现有"+ readerCount + "个读者需要读");

                /**
                 * 所有正在读文件的读者都读完了，就停止读
                 * 同时前面已经限制了还没有开始读的读者不进入读
                 * 于是此时无读者，写者能够开始写
                 **/
                if (readerCount == 0) {
                    isReading = false;
                    System.out.println("所有读者读完");
                }
            }
        }
        static class MyWriter implements Runnable {
            private MyFile file;

            public MyWriter(MyFile file) {
                this.file = file;
            }

            @Override
            public void run() {
                file.write();
            }
        }

        static class MyReader implements Runnable {
            private MyFile file;

            public MyReader(MyFile file) {
                this.file = file;
            }

            @Override
            public void run() {
                file.read();
            }
        }

        public static void main(String[] args) {
            MyFile file = new MyFile();
            MyWriter writer1 = new MyWriter(file);
            MyWriter writer2 = new MyWriter(file);
            MyWriter writer3 = new MyWriter(file);

            MyReader reader1 = new MyReader(file);
            MyReader reader2 = new MyReader(file);
            MyReader reader3 = new MyReader(file);
            MyReader reader4 = new MyReader(file);
            MyReader reader5 = new MyReader(file);
            MyReader reader6 = new MyReader(file);
            MyReader reader7 = new MyReader(file);
            MyReader reader8 = new MyReader(file);
            MyReader reader9 = new MyReader(file);
            MyReader reader10 = new MyReader(file);

            Thread t4 = new Thread(reader1);
            Thread t5 = new Thread(reader2);
            Thread t6 = new Thread(reader3);
            Thread t7 = new Thread(reader4);
            Thread t8 = new Thread(reader5);
            Thread t9 = new Thread(reader6);
            Thread t10 = new Thread(reader7);
            Thread t13 = new Thread(reader10);
            Thread t11= new Thread(reader8);
            Thread t12 = new Thread(reader9);

            Thread t1 = new Thread(writer1);
            Thread t3 = new Thread(writer3);
            Thread t2 = new Thread(writer2);

            t1.setName("Writer 1");
            t2.setName("Writer 2");
            t3.setName("Writer 3");
            t4.setName("Reader 1");
            t5.setName("Reader 2");
            t6.setName("Reader 3");
            t7.setName("Reader 4");
            t8.setName("Reader 5");
            t9.setName("Reader 6");
            t10.setName("Reader 7");
            t11.setName("Reader 8");
            t12.setName("Reader 9");
            t13.setName("Reader 10");

            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
            t9.start();
            t10.start();
            t11.start();
            t12.start();
            t13.start();

            t2.start();
            t1.start();
            t3.start();

        }
    }
}
