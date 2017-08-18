package test;


import java.util.concurrent.CountDownLatch;

public class NoVisibility {
	private static boolean ready;
	private static int number;
	private static final CountDownLatch latch=new CountDownLatch(1);
	private static class ReadyThread extends Thread{
		@Override
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (!ready) {
				Thread.yield();
			}
//			System.out.println(number);
		}
	} 
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new ReadyThread().start();
		}
		long a=System.currentTimeMillis();
		latch.countDown();
		number=42;
		ready=true;
		long b=System.currentTimeMillis();
		System.out.print("执行时间："+(b-a)+"ms");
	}

}
