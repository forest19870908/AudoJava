package test;
/**
 * 测试是否有指令重排序
 * @author gq
 * @time 2017年6月16日
 */
public class ReorderTest {
	private static int number=1000;
	public static void main(String[] args) {
		Thread[] ts=new Thread[number];
		for (int i = 0; i < number; i++) {
			ts[i]=new Thread(){
				@Override
				public void run() {
					print();
				}
			};
			ts[i].start();
		}

	}
	static void print(){
		String name=Thread.currentThread().getName();
		synchronized (name) {
			System.out.println("线程"+name+"说：我是A");
		}
		System.out.println("线程"+name+"说：我是B");
//		System.out.println("线程"+name+"说：我是C");
//		System.out.println("线程"+name+"说：我是D");
//		System.out.println("线程"+name+"说：我是E");
	}
}
