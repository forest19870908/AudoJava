package execute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by ZSL on 2017/6/27.
 */
public class TaskExecutionWebServer {
    private static final int threads=10;
    private static final Executor exec= Executors.newFixedThreadPool(threads);
    private static int count=0;//链接数
    public static void main(String[] args) throws IOException {
        ServerSocket socket=new ServerSocket(80) ;
        while (true){
            final Socket connection=socket.accept();
            Runnable task=new Runnable() {
                @Override
                public void run() {
                    try {
                        handleRequest(connection);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(task);
        }
    }

    private static void handleRequest(Socket connection) throws IOException {
//用于向客户端发送数据的输出流

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

//用于接收客户端发来的数据的输入流

        DataInputStream dis = new DataInputStream(connection.getInputStream());

        System.out.println("服务器接收到客户端的连接请求：" + dis.readUTF());

        dos.writeUTF("接受连接请求，连接成功!");
        count++;
        System.out.println("当前线程id:"+Thread.currentThread().getId()+",活动线程数："+Thread.activeCount()+",链接数："+count);
    }
}
