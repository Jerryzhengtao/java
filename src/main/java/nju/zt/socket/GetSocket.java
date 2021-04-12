package nju.zt.socket;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName GetSocket
 * //TODO
 * @Author zt
 * @Date 2021/3/8 16:38
 **/
public class GetSocket extends Thread{
    @Override
    public void run(){
        try {
            //创建绑定到特定端口的服务器套接字
            ServerSocket serverSocket = new ServerSocket(62224);
            while (true)
            {
                //建立连接
                Socket socket = serverSocket.accept();
                //窗口
                JOptionPane.showMessageDialog(null,"有客户端连接到62224端口");
                //与客户端通信
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                ChatManager.getChatManager().addChatPeople(cs);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
