package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadTxt2 {
	public static String toFile="七界武神2.txt";
	public static String fromFile="C:\\Users\\ZSL\\Desktop\\七界武神.txt";
	public static void main(String[] args) {
		File fromfile=new File(fromFile);
		File tofile=new File(fromfile.getParent(),toFile);
		copyDeal(fromfile, tofile);
	}
	 public static void copyDeal(File fromfile,File tofile){
			 BufferedReader br = null;
			 BufferedWriter bw =null;
			try {
				 br = new BufferedReader(new InputStreamReader(new FileInputStream(fromfile),"gbk"));
				 bw = new BufferedWriter(new FileWriter(tofile));
				 String line=null;
				 while( (line = br.readLine()) != null){
					 System.out.println(line);
					 int start=line.indexOf(0);
					 int end=line.indexOf(".第");
					 int end2=line.indexOf("章");
					 System.out.println(end-start);
					if(end>0 && end-start<5 && end2-end<5){
						StringBuilder lineb=new StringBuilder(line);
						line=line.substring(end+1, line.length());
//						lineb.insert(0,"正文  第");
//						c=lineb.toString();
					}
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
}
