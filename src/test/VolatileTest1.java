package test;

public class VolatileTest1 {
	private static volatile boolean isShutDown=false;
	public static void shutDown(){
		isShutDown=true;
		System.out.println("shutDown:"+System.currentTimeMillis()+"ms");
	}
	public static void  doWork(){
		while(!isShutDown){
			System.out.println("isShutDown  :"+isShutDown);
			System.out.println("doWork  :"+System.currentTimeMillis()+"ms");
		}
	}
	public static void main(String[] args) {
		final Thread[] threads=new Thread[20];
		for (int i = 0; i < threads.length; i++) {			
			threads[i]=new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程："+Thread.currentThread().getName());
					doWork();
					
				}
			});
			threads[i].start();
		}
		Thread thread2=new Thread(new Runnable() {
			@Override
			public void run() {
				shutDown();
				
			}
		});
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread2.start();
	}

}
