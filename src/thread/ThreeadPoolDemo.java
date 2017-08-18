package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZSL on 2017/8/17.
 */
public class ThreeadPoolDemo {
    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getId()+"正在执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getId()+"执行完成");
            }
        };
        ExecutorService executors= Executors.newFixedThreadPool(5);
        //ExecutorService executors= Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executors.execute(runnable);
        }
        executors.shutdown();
    }
}
