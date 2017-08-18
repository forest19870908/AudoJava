package test;

public class VolatileTest {
	private static volatile int race=0;
	public static synchronized void increase(){
		race++;
	}
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		Thread[] threads=new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			threads[i]=new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		while (Thread.activeCount()>1) {
			Thread.yield();
		}
		System.out.println(race);
		long b=System.currentTimeMillis();
		System.out.println(b-a+"ms");
	}

}
