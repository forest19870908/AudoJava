package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZSL on 2017/8/4.
 */
public class MultiThreadInt {
    public static int t=0;
    public static class ChangT implements Runnable{
        private int to;
        public ChangT(int to){
            this.to=to;
        }
        @Override
        public void run() {
            while (true){
                MultiThreadInt.t=to;
                Thread.yield();
            }
        }
    }
    public static class ReadT implements Runnable{
        @Override
        public void run() {
            while (true){
                int tmp=MultiThreadInt.t;
                if(tmp!=111 && tmp!=-999 && tmp!=333 && tmp!=-444){
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor= Executors.newFixedThreadPool(5);
        executor.execute(new ChangT(111));
        executor.execute(new ChangT(-999));
        executor.execute(new ChangT(333));
        executor.execute(new ChangT(-444));
        executor.execute(new ReadT());
    }
}
