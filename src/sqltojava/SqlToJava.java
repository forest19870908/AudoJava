package sqltojava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * sql变java
 * @author gq
 * @time 2017年5月18日
 */
public class SqlToJava {

	public static void main(String[] args) {
//		File f = new File(".");
//		String absolutePath = f.getAbsolutePath();
//		System.out.println(absolutePath);
		
		File sql=new File("src/sqltojava/sql.txt");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		File history=new File("src/sqltojava/history/sql"+sdf.format(date)+".txt");
		File historyJava=new File("src/sqltojava/history/java"+sdf.format(date)+".txt");
		File javaReult=new File("src/sqltojava/javaResult.txt");
		copyDeal(sql, history,historyJava,javaReult);
		System.out.println("执行成功。");
	}
	 public static void copyDeal(File fromfile,File sourcefile,File tofile,File tofileCopy){
		 BufferedReader br = null;
		 BufferedWriter bwCopy =null;
		 BufferedWriter bw =null;
		 BufferedWriter bw2 =null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(fromfile),"utf-8"));
			 bwCopy = new BufferedWriter(new FileWriter(sourcefile));
			 bw = new BufferedWriter(new FileWriter(tofile));
			 bw2 = new BufferedWriter(new FileWriter(tofileCopy));
			 String line=null;
			 while( (line = br.readLine()) != null){
				 bwCopy.write(line);
				 bwCopy.newLine();
				 line="+ \" "+line+" \"";
//				 System.out.println(line);
				 bw.write(line);
				 bw.newLine();
				 bw2.write(line);
				 bw2.newLine();
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
                if (bwCopy != null)  
                	bwCopy.close();
                if (bw2 != null)  
                	bw2.close();
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } 
	 }
}
