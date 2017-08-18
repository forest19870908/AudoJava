package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZSL on 2017/8/17.
 */
public class SelfExecutorDemo {
    public static class  MyTask implements Runnable{
        private String name;
        public MyTask(String name){
            this.name=name;
        }
        @Override
        public void run() {
            System.out.println(name+":正在执行，线程id"+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor=new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行："+((MyTask)r).getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行结束："+((MyTask)r).getName());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyTask("线程"+i));
        }
        executor.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
