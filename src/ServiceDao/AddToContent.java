package ServiceDao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 追加内容
 * @author gq
 *
 */
public class AddToContent {
	private static List<String> notes=new ArrayList<String>();//注释内容
	private static List<String> lines=new ArrayList<String>();//需要追加的接口
	private static List<String> lineServices=new ArrayList<String>();//需要追加的service实现
	private static List<String> lineDaos=new ArrayList<String>();//需要追加的dao实现
	private static String override="@Override";
	private static String format="	";
	/**
	 * 根据类型获得需要追加的内容
	 */
	public static void getContent(File file){
		 BufferedReader br = null;
		 try {
			 br = new BufferedReader(new FileReader(file));
			 String line=null;
			 Integer isCopy=0;//复制注释 0 什么都不复制  1 复制注释  2 复制方法 3复制方法结束
			 while( (line = br.readLine()) != null){
				 if(isCopy==3 && (line.contains("/**") ||line.contains(";"))){
					 notes.clear();
					 lines.clear();
				 }
				 if(line.contains("/**")){
					 isCopy=1;
				 }
				 if(isCopy==1){
					 notes.add(line);
				 }
				 if(isCopy==2){
					 if(lines.size()==0){
						 if(!line.contains("public")){
							 line=format+"public "+line.substring(1,line.length()); 
						 } 
					 }
					 lines.add(line);
				 }
				 if(line.contains("*/")){
					 isCopy=2;
				 }
				 if(line.contains(";")){
					 isCopy=3;
				 }
			 }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {  
	         try {  
	             if (br != null)  
	                 br.close();  
	         } catch (IOException e) {  
	             e.printStackTrace();  
	         }
         } 
	}
	/**
	 * 增加内容到接口
	 * @param file
	 */
	public static void addContentInterface(File file){
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		 String lineEnd="";
		 File outFile =null;
		 try {
			 // 临时文件
			 outFile = File.createTempFile("name", ".tmp");
			 br = new BufferedReader(new FileReader(file));
			 bw = new BufferedWriter(new FileWriter(outFile));
			 String line=null;
			 while( (line = br.readLine()) != null){
				   if(!line.contains("}")){
					   bw.write(line);
					   bw.newLine();
				   }else{
					   lineEnd=line; 
				   }
			 }
			 for (String s : notes) {
				 bw.write(s);
				 bw.newLine();
			 }
			 for (String s : lines) {
				 bw.write(s);
				 bw.newLine();
			 }
			 bw.write(lineEnd);
			 bw.newLine();
			 bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
         try {  
             if (br != null)  
                 br.close();  
             if (bw != null)  
                 bw.close();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
		}
		// 删除原始文件
		file.delete();
	    // 把临时文件改名为原文件名
	    outFile.renameTo(file);
	}
	/**
	 * 增加内容到dao实现
	 * @param file
	 */
	public static void addContentDao(File file){
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		 String lineEnd="";
		 File outFile =null;
		 int lineNum=0;
		 try {
			 // 临时文件
			 outFile = File.createTempFile("name", ".tmp");
			 br = new BufferedReader(new FileReader(file));
			 bw = new BufferedWriter(new FileWriter(outFile));
			 String line=null;
			 while( (line = br.readLine()) != null){
				   if(lineNum==0 && line.contains(override)){
					   lineNum=line.indexOf(override);
				   }
				   if(line.contains("}") && line.indexOf("}")==lineNum){
					   lineEnd=line; 
				   }else{
					   bw.write(line);
					   bw.newLine();
				   }
			 }
			 bw.write(format+override);
			 bw.newLine();
			 for (int i = 0; i < lines.size(); i++) {
				 if(i==lines.size()-1){
					bw.write(lines.get(i).replaceFirst(";", "{")); 
					bw.newLine();
				 }else{
					bw.write(lines.get(i));
				 	bw.newLine();
				 }
			 }
			 bw.newLine();
			 bw.write(format+"}");
			 bw.newLine();
			 bw.write(lineEnd);
			 bw.newLine();
			 bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
	     try {  
	         if (br != null)  
	             br.close();  
	         if (bw != null)  
	             bw.close();  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     }  
		}
		// 删除原始文件
		file.delete();
	    // 把临时文件改名为原文件名
	    outFile.renameTo(file);
	}
}
