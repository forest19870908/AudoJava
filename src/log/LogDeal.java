package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 对要更新的文件处理一下得到自己需要的数据
 * @author gq
 *
 */
public class LogDeal {
	public static String toFile="要更新的文件.txt";
	public static String fromFile="C:\\Users\\ZSL\\Desktop\\changeLog.txt";
	public static String historyFile="F:\\modifyHistory";
	
	public static void main(String[] args) {
		File fromfile=new File(fromFile);
		File tofile=new File(fromfile.getParent(),toFile);
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		File history=new File(historyFile,"要更新的文件_"+sdf.format(date)+".txt");
		copyDeal(fromfile, tofile);
		copyHistory(tofile,history);
	}
	 public static void copyDeal(File fromfile,File tofile){
			 BufferedReader br = null;
			 BufferedWriter bw =null;
			try {
				 br = new BufferedReader(new InputStreamReader(new FileInputStream(fromfile),"gbk"));
				 bw = new BufferedWriter(new FileWriter(tofile));
				 Set records=new HashSet();
				 
				 Set notes=new HashSet();
				 String lastRecord="";//上一条记录
				 String line=null;
				 while( (line = br.readLine()) != null){
					 System.out.println(line);
					 if(lastRecord.contains("Changed paths:")
							 || line.contains(".java")
							 || line.contains(".htm")
							 || line.contains(".js")
							 || line.contains(".jsp")
							 || line.contains(".html")
							 || line.contains(".properties")){
						
						if(!records.contains(line)){
//							 bw.write(line);	
//							 bw.newLine();
							 records.add(line);
						}
					 }
					 if(line.contains("----")){
						 notes.add(lastRecord); 
					 }
					 lastRecord=line;
				 }
				 List list=sort(records);
				 Set lineKeyS=new HashSet();
				 for (Object  record : list) {
					String line_key="";
					line=(String) record;
					if(line.lastIndexOf("/")!=-1){
						line_key=line.substring(line.lastIndexOf("/"), line.length()-1);
					}
					if( !"".equals(line_key) && !lineKeyS.contains(line_key)){						
						bw.write(record.toString());
						bw.newLine();
					}
					lineKeyS.add(line_key);
				 }
				 bw.newLine();
				 bw.newLine();
				 int i=1;
				 for (Object  note : notes) {
					bw.write(i+". "+note.toString());
					bw.newLine();
					i++;
				 }
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
		 }
	 public static void copyHistory(File fromfile,File tofile){
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(fromfile),"utf-8"));
			 bw = new BufferedWriter(new FileWriter(tofile));
			 String line=null;
			 while( (line = br.readLine()) != null){
				 bw.write(line);	
				 bw.newLine();
			 }
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
	 }
	 /**
	  * 对需要更新的数据根据规则排序
	  * 1.java htm/jsp js  其他 来分一个大组
	  * 2.对java 先根据类型 然后按照controller service dao 目录 排序
	  * 3.对其他的根据类型来排序
	  * @param set
	  */
	 public static List sort(Set set){
		 List list=new ArrayList();
		 List javaList=new ArrayList();
		 List htmList=new ArrayList();
		 List jspList=new ArrayList();
		 List jsList=new ArrayList();
		 List otherList=new ArrayList();
		 for (Object obj : set) {
			 if(obj.toString().contains(".java")){
				 javaList.add(obj);
			 }else if(obj.toString().contains(".htm")){
				 htmList.add(obj);
			 }else if(obj.toString().contains(".jsp")){
				 jspList.add(obj);
			 }else if(obj.toString().contains(".js")){
				 jsList.add(obj);
			 }else{
				 otherList.add(obj);
			 }
		}
		Collections.sort(javaList);
		list.addAll(javaList);
		Collections.sort(htmList);
		list.addAll(htmList);
		Collections.sort(jspList);
		list.addAll(jspList);
		Collections.sort(jsList);
		list.addAll(jsList);
		Collections.sort(otherList);
		list.addAll(otherList);
		return list;
	 }
}
