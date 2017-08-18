package thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by ZSL on 2017/8/17.
 */
public class CountTask extends RecursiveTask<Long> {
    private final static int nthread=1000;
    private long start;
    private long end;
    public  CountTask(long start,long end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        long sum=0;
        boolean canCompute=end-start<nthread;
        if(canCompute){
            for (long i = start; i <= end; i++) {
                sum+=i;
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("线程id："+Thread.currentThread().getId());
            }
        }else{
            //分成100个小任务
            long step=(end-start)/100;
            ArrayList<CountTask> countTasks=new ArrayList<CountTask>();
            long pos=start;
            for (int i = 0; i < 100; i++) {
                long lastOne=pos+step;
                if(lastOne>end){
                    lastOne=end;
                }
                CountTask countTask=new CountTask(pos,lastOne);
                pos+=step+1;
                countTasks.add(countTask);
                countTask.fork();
            }
            for (CountTask t:countTasks){
                sum+=t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask countTask=new CountTask(105,2000);
        ForkJoinTask<Long> result=forkJoinPool.submit(countTask);
        try {
            long res=result.get();
            System.out.println("sum="+res);
            System.out.println("共使用了"+(System.currentTimeMillis()-a)+"ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
