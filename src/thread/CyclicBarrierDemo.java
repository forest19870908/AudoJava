package thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ZSL on 2017/8/15.
 */
public class CyclicBarrierDemo {
    private static class Soldier implements Runnable {
        private String soldier;
        private CyclicBarrier cyclic;

        public Soldier(CyclicBarrier cyclic, String soldierName) {
            this.cyclic = cyclic;
            this.soldier = soldierName;
        }

        @Override
        public void run() {
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void doWork() throws InterruptedException {
            Thread.sleep(1000);
            System.out.println(soldier + ":任务完成");
        }
    }

    public static class BarrierRun implements Runnable {
        private boolean flag;
        private int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：【士兵" + N + "个，任务完成】");
            } else {
                System.out.println("司令：【士兵" + N + "个，集合完毕】");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cylic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道");
            allSoldier[i] = new Thread(new Soldier(cylic, "士兵" + i));
            allSoldier[i].start();
//            if(i==5){
//                allSoldier[i].interrupt();
//            }
        }
    }
}
