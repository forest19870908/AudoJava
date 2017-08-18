package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZSL on 2017/8/4.
 */
public class ReOrderTest {

    public static void main(String[] args) {
        ExecutorService excutors = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            CountDownLatch count = new CountDownLatch(1);
            ReOrderModel t = new ReOrderModel();
            excutors.execute(() -> {
                        try {
                            count.await();
                            System.out.println("------------1");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int r2 = t.getA();
                        t.setB(1);
                        System.out.println(t.hashCode() + ":r2:" + r2);
                    }
            );
            excutors.execute(() -> {
                        try {
                            count.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("------------2");
                        int r1 = t.getB();
                        t.setA(2);
                        System.out.println(t.hashCode() + ":r1:" + r1);
                    }
            );
            count.countDown();
        }
        excutors.shutdown();
    }
}
