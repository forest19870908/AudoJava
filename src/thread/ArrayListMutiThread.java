package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZSL on 2017/8/15.
 */
public class ArrayListMutiThread implements  Runnable{
    static List<Integer> list=new ArrayList<Integer>();

    @Override
    public void run() {
        for (int i = 0; i <10000 ; i++) {
            list.add(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new ArrayListMutiThread());
        Thread t2=new Thread(new ArrayListMutiThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}
