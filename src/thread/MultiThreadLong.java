package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZSL on 2017/8/4.
 */
public class MultiThreadLong {
    public static volatile long t=0;
    public static class ChangT implements Runnable{
        private long to;
        public ChangT(long to){
            this.to=to;
        }
        @Override
        public void run() {
            while (true){
                MultiThreadLong.t=to;
                Thread.yield();
            }
        }
    }
    public static class ReadT implements Runnable{
        @Override
        public void run() {
            while (true){
                long tmp= MultiThreadLong.t;
                if(tmp!=111L && tmp!=-999L && tmp!=333L && tmp!=-444L){
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor= Executors.newFixedThreadPool(5);
        executor.execute(new ChangT(111L));
        executor.execute(new ChangT(-999L));
        executor.execute(new ChangT(333L));
        executor.execute(new ChangT(-444L));
        executor.execute(new ReadT());
    }
}
