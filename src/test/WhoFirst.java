package test;

import java.util.concurrent.*;

/**
 * Created by ZSL on 2017/6/19.
 */
public class WhoFirst {
    private static Integer ThreadNumber=60;
    private static CountDownLatch latchStart =new CountDownLatch(1);
    private static CountDownLatch latchEnd =new CountDownLatch(ThreadNumber);
    private static int count=0;
    public static void main(String[] args) {
        //默认线程执行
        long a=System.currentTimeMillis();
        defaultMethod();
        long b=System.currentTimeMillis();
        System.out.println("defaultMethod使用："+(b-a)+"ms");

        //闭锁线程执行
        countDownLatchMethod();
        System.out.println("countDownLatchMethod:开始");
        long c=System.currentTimeMillis();
        latchStart.countDown();
        try {
            latchEnd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long d=System.currentTimeMillis();
        System.out.println("countDownLatchMethod使用："+(d-c)+"ms,count:"+count);

        long e=System.currentTimeMillis();
        futureTaskMethod();
        long f=System.currentTimeMillis();
        System.out.println("futureTaskMethod使用："+(f-e)+"ms,count:"+count);

        long g=System.currentTimeMillis();
        semaphoreMethod();
        long h=System.currentTimeMillis();
        System.out.println("semaphoreMethod："+(h-g)+"ms,count:"+count);
    }

    private static void defaultMethod() {
        Thread[] ts=new Thread[ThreadNumber];
        for (int i = 0; i < ts.length; i++) {
            ts[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("defaultMethod,当前线程："+Thread.currentThread().getName());
                }
            });
            ts[i].start();
        }
    }
    private static void countDownLatchMethod() {
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
                    System.out.println("countDownLatchMethod,当前线程："+Thread.currentThread().getName());
                    count++;
                    latchEnd.countDown();
                }
            });
            ts[i].start();
        }
    }
    private static void futureTaskMethod() {
        FutureTask<Integer> future=new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("futureTaskMethod,当前线程："+Thread.currentThread().getName());
                count++;
                return count;
            }
        });
        Thread[] ts=new Thread[ThreadNumber];
        for (int i = 0; i < ts.length; i++) {
            ts[i]=new Thread(future);
            ts[i].start();
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    private static void semaphoreMethod() {
        Semaphore semaphore=new Semaphore(10);
        Thread[] ts=new Thread[ThreadNumber];
        for (int i = 0; i < ts.length; i++) {
            ts[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("defaultMethod,当前线程："+Thread.currentThread().getName());
                    count++;
                    semaphore.release();
                }
            });
            ts[i].start();
        }
    }
}
