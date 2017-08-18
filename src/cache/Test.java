package cache;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ZSL on 2017/6/20.
 */
public class Test {
    public static void main(String[] args) {
        int threadCount=1000;//线程数量
        CountDownLatch startLatch=new CountDownLatch(1);
        CountDownLatch endLatch=new CountDownLatch(threadCount);
        Computable<String,BigInteger> m=new Memoizer<String,BigInteger>(new ExpensiveFunction());
        Random r=new Random();
        Thread[] ts=new Thread[threadCount];
        for (int i = 0; i < ts.length; i++) {
            ts[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startLatch.await();
                        String str=Integer.valueOf(r.nextInt(5)).toString();
                        m.computable(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        endLatch.countDown();
                    }
                }
            });
            ts[i].start();
        }
        long a =System.currentTimeMillis();
        startLatch.countDown();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.getCount());
        long b =System.currentTimeMillis();
        System.out.println("执行时间："+(b-a)+"ms");
    }
    // Memoizer1 10 线程 执行时间：30004ms
    // Memoizer2 10 线程 执行时间：3002ms
    // Memoizer3 10 线程 执行时间：3018ms
    // Memoizer 10 线程 执行时间：3003ms


}
