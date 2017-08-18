package test;

import java.util.Vector;

public class VectorTest {
	private static Vector<Integer> vector=new Vector<Integer>();
	public static void main(String[] args) {
		test1();
	}
	static void test1(){
		while(true){
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}
			Thread removeThread=new Thread(new Runnable() {
				
				@Override
				public void run() {
//					synchronized (vector) {						
						for (int i = 0; i < vector.size(); i++) {
							System.out.println("remove:"+i);
							vector.remove(i);
						}
//					}
				}
			});
			removeThread.start();
			Thread getThread=new Thread(new Runnable() {
				
				@Override
				public void run() {
//					synchronized (vector) {						
						for (int i = 0; i < vector.size(); i++) {
							System.out.println("get:"+i);
							vector.get(i);
						}
//					}
				}
			});
			getThread.start();
		}
	}
	static void test2(){
		Thread[] threads=new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			threads[i]=new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						dealVector();
					}
				}
			});
			threads[i].start();
		}
		while (Thread.activeCount()>1) {
			Thread.yield();
		}
		System.out.println(vector);
	}
	static void dealVector(){
		for (int i = 0; i < 100; i++) {
			if(!vector.contains(i)){
				vector.add(i);
			}
		}
	}
}
