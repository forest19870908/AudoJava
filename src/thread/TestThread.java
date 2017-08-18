package thread;

import java.util.List;
import java.util.concurrent.*;

public class TestThread {
    /**
     * 使用线程池的方式是复用线程的（推荐）
     * 而不使用线程池的方式是每次都要创建线程
     * Executors.newCachedThreadPool()，该方法返回的线程池是没有线程上限的，可能会导致过多的内存占用
     * 建议使用Executors.newFixedThreadPool(n)
     *
     * 有兴趣还可以看下定时线程池：SecheduledThreadPoolExecutor
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long a=System.currentTimeMillis();
        int nThreads = 10;

        /**
         * Executors是ThreadPoolExecutor的工厂构造方法
         */
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        //submit有返回值，而execute没有返回值，有返回值方便Exception的处理
        Future res=null;
        for (int i = 0; i <20 ; i++) {

             res= executor.submit(new ConsumerThread());
        }





        /**
         * shutdown调用后，不可以再submit新的task，已经submit的将继续执行
         * shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
         */
        List<Runnable> runnableList = executor.shutdownNow();
//        res= executor.submit(new ConsumerThread());

        //配合shutdown使用，shutdown之后等待所有的已提交线程运行完，或者到超时。继续执行后续代码
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(runnableList.size()+"AAAAAAAAAAAAAAA");

        //打印执行结果，出错的话会抛出异常，如果是调用execute执行线程那异常会直接抛出，不好控制，submit提交线程，调用res.get()时才会抛出异常，方便控制异常
        System.out.println("future result:"+res.get());
        long b=System.currentTimeMillis();
        System.out.println((b-a)+"ms");
    }

    static class ConsumerThread implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<5;i++) {
                System.out.println("线程："+Thread.currentThread().getName()+":"+i);
            }
        }
    }
}