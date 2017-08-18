package search;

import java.io.File;

/**
 * 搜索入口
 * @author gq
 * @time 2016年12月13日
 */
public class SearchMain {
//	private static String con="Java 并发编程实战.pdf";//搜索条件
	private static String con="mybatis";//搜索条件
	private static String noInclude="Windows.old";//不包括
	private static String[] range=new String[]{"D:","E:","F:","C:"};//范围(目录)
	
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		for (String str : range) {
			getFiles(new File(str));
		}
		long b=System.currentTimeMillis();
		System.out.println(b-a+"ms");
	}
	
	public static void getFiles(File file){
		File[] files=null;
		if(file.getName().contains(con)){
			System.out.println(file.getAbsolutePath());
		}
		if(file.getName().contains(noInclude)){
			return;
		}
		if(file.isDirectory()){
			files=file.listFiles();
			if(files!=null){
				for (File f : files) {
					getFiles(f);
				}
			}
		}
	}
}
