package test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
	private static AtomicLong count=new AtomicLong(0);
	private static AtomicLong countA=new AtomicLong(0);
	private static AtomicLong countB=new AtomicLong(0);
	public static void deal(long init){
		count.set(init);
		Random r=new Random();
		int a=r.nextInt((int) init);
		countA.set(a);
		countB.set(init-a);
		System.out.print(count);
		System.out.print("="+countA);
		System.out.print("+"+countB);
		System.out.println();
	}
	public static void increase() throws InterruptedException{
		count.incrementAndGet();
		countA.incrementAndGet();
//		countA.set(count.get());
//		count.set(countA.get());
	}
	/**
	 * @param args
	 * @author gq
	 * @time 2017年6月15日
	 */
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		Thread[] threads=new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			threads[i]=new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
//						deal(j);
						try {
							increase();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			threads[i].start();
		}
		while (Thread.activeCount()>1) {
			Thread.yield();
		}
		System.out.println(count.get());
		System.out.println(countA.get());
		long b=System.currentTimeMillis();
		System.out.println(b-a+"ms");
	}

}
