package thread;

import javafx.concurrent.ScheduledService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZSL on 2017/8/17.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(5);
        scheduled.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()/1000+"秒，"+Thread.currentThread().getId());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1,1, TimeUnit.SECONDS);
        //scheduled.shutdown();
    }
}
