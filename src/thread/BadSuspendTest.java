package thread;

/**
 * Created by ZSL on 2017/8/14.
 */
public class BadSuspendTest {
    public static Object obj=new Object();
    static  ChangeObjectThread t1=new ChangeObjectThread("t1");
    static  ChangeObjectThread t2=new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super(name);
        }
        @Override
        public void run() {
            System.out.println("in "+getName());
            Thread.currentThread().suspend();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
