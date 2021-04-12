package nju.zt.socket;

import java.nio.CharBuffer;
import java.util.Vector;

/**
 * @ClassName ChatManager
 * //TODO
 * @Author zt
 * @Date 2021/3/8 16:38
 **/
public class ChatManager {

    private ChatManager(){

    }
    private static final ChatManager cm = new ChatManager();
    public static ChatManager getChatManager(){
        return cm;
    }

    //socket队列
    Vector<ChatSocket> vector = new Vector<>();

    public void addChatPeople(ChatSocket cs)
    {
        vector.add(cs);
    }

    public void send(ChatSocket cs,String str)
    {
        for (int i = 0; i < vector.size(); i++) {

            ChatSocket chatSocket = vector.get(i);
            if(!cs.equals(chatSocket))
            {
                chatSocket.out(str);
            }
        }
    }
}
