package search;

import java.io.File;

/**
 * 搜索入口
 * @author gq
 * @time 2016年12月13日
 */
public class SearchFastMain {
//	private static String con="Java 并发编程实战.pdf";//搜索条件
	private static String con="Spring源码深度解析";//搜索条件
	private static String noInclude="Windows.old";//不包括
	private static String[] range=new String[]{"D:","E:","F:","C:"};//范围(目录)
	
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		for (String str : range) {
			getFiles(new File(str));			
		}
		while (Thread.activeCount()>1) {
			Thread.yield();
		}
		long b=System.currentTimeMillis();
		System.out.println(b-a+"ms");
	}
	
	public static void getFiles(File file){
		File[] files=null;
		if(file.getName().contains(con)){
			System.out.println(Thread.currentThread().getName()+":"+file.getAbsolutePath());
		}
		if(file.getName().contains(noInclude)){
			return;
		}
		if(file.isDirectory()){
			files=file.listFiles();
			if(files!=null){
				if(file.length()<5){
					Thread[] t=new Thread[files.length];
					int i=0;
					for (File f : files) {
						t[i]=new Thread() {
							
							@Override
							public void run() {
								getFiles(f);
							}
						};
						t[i].start();
						i++;
					}
				}else{
					for (File f : files) {
						getFiles(f);
					}
				}
			}
		}
	}
}
