package execute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ZSL on 2017/6/27.
 */
public class TaskExecutionWebClientDemo {
    /**
     * 　　* @param args
     * <p>
     */

    private static Integer ThreadNumber = 60;
    private static CountDownLatch latchStart = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        Thread[] ts=new Thread[ThreadNumber];
        for (int i = 0; i < ts.length; i++) {
            ts[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latchStart.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendMsg();
                }
            });
            ts[i].start();
        }
        latchStart.countDown();
    }

    private static void sendMsg() {
        Socket socket = null;

        try {

            socket = new Socket("localhost", 80);

//获取输出流，用于客户端向服务器端发送数据

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

//获取输入流，用于接收服务器端发送来的数据

            DataInputStream dis = new DataInputStream(socket.getInputStream());

//客户端向服务器端发送数据

            dos.writeUTF("我是客户端，请求连接!");

//打印出从服务器端接收到的数据

            System.out.println(dis.readUTF());

//不需要继续使用此连接时，记得关闭哦

            socket.close();


        } catch (UnknownHostException e) {

            e.printStackTrace();


        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
