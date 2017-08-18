package thread;

/**
 * Created by ZSL on 2017/8/14.
 */
public class WaitNotifyTest {
    final static Object obj=new Object();
    public static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("thread1 开始");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 结束");
            }
        }
    }
    public static class Thread2 extends Thread{
        @Override
        public void run() {
            System.out.println("thread2 准备进入同步代码块");
            synchronized (obj){
                System.out.println("thread2 开始");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 结束");
            }
        }
    }
    public static class Thread3 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("thread3 开始");
                obj.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread3 结束");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1=new Thread1();
        t1.start();
        Thread2 t2=new Thread2();
        t2.start();
        Thread3 t3=new Thread3();
        t3.start();
    }
}
