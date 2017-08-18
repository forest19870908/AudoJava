package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by ZSL on 2017/6/19.
 */
public class Preloader {
    private static  final FutureTask<Integer> future=new FutureTask<Integer>(new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(10);
            return 5;
        }
    });
    private Thread thread=new Thread(future);
    public void start(){
        thread.start();
    }
    public Integer get(){
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
