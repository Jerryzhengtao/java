package nju.zt.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ClassName ChatSocket
 * //TODO
 * @Author zt
 * @Date 2021/3/8 16:38
 **/
public class ChatSocket extends  Thread{
    Socket socket;
    public ChatSocket(Socket socket)
    {
        this.socket = socket;
    }

    //向客户端输出消息
    public void out(String message)
    {
        try {
            socket.getOutputStream().write(toString().getBytes("UTF-8"));

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //向客户端发送消息

    public void run(){
        out("success\n");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String line = null;
            while ((line=br.readLine())!=null)
            {
                System.out.println(line);
                ChatManager.getChatManager().send(this,line);
            }
            br.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
