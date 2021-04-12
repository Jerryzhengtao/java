package nju.zt.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @ClassName MyChannel
 * //TODO
 * @Author zt
 * @Date 2021/3/5 23:45
 **/
public class MyChannel {
    public static void main(String[] args) {

        try {
            ReadableByteChannel source = Channels.newChannel(System.in);
            WritableByteChannel dest = Channels.newChannel(System.out);

            channelCopy1(source,dest);
            source.close();
            dest.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void channelCopy1(ReadableByteChannel src,WritableByteChannel des){

        try {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16*1024);

            while (src.read(byteBuffer)!=-1){
                byteBuffer.flip();
                des.write(byteBuffer);
                byteBuffer.compact();
            }

            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                des.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
