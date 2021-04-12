package nju.zt.thread;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName ConnPoolDemo
 * //TODO
 * @Author zt
 * @Date 2021/3/10 20:43
 **/
public class ConnPoolDemo {
    private LinkedList<Connection> pool = new LinkedList<Connection>();
    public ConnPoolDemo(int capacity)
    {
        for (int i = 0; i < capacity; i++) {
            pool.add(ConnectionDriver.createConnection());
        }
    }
    public  void releaseConnection(Connection connection){
        if(connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            if(mills<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }
            else {
                long future = System.currentTimeMillis() +mills;
                long remaining = mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait();
                    remaining = future-System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty())
                {
                    result = pool .remove();
                }
                return result;
            }
        }
    }
}
