package copy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.Utils;

public class Copy {
	 public static void copyModel(File model,String EntityName,String note,String tableName){
		//复制文件数据
		 BufferedReader br = null;
		 BufferedWriter bw =null;
//		 System.out.println(model.getAbsoluteFile());
		 String pack=model.getParent().replace("\\", ".");
		 pack=pack.substring(pack.indexOf("src")+4, pack.length());
		 pack="package "+pack+";\n";
		 System.out.println(pack);
		 
		try {
			 br = new BufferedReader(new FileReader(".\\src\\model.java"));
			 bw = new BufferedWriter(new FileWriter(model));
			 String line=null;
			 bw.write(pack);
			 while( (line = br.readLine()) != null){
				   if(line.contains("Model")){
					   line = line.replaceAll("Model", EntityName);
				   }
				   if(line.contains("note")){
					   line = line.replaceAll("note", note);
				   }
				   if(line.contains("table_name")){
					   line = line.replaceAll("table_name", tableName);
				   }
				   bw.write(line+"\n");
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
	 public static void copyDao(File dao,String EntityName,String note){
			//复制文件数据
			 BufferedReader br = null;
			 BufferedWriter bw =null;
			 String pack=dao.getParent().replace("\\", ".");
			 pack=pack.substring(pack.indexOf("src")+4, pack.length());
			 String importName="import "+pack.replace("dao", "model")+"."+EntityName+";\n";
			 pack="package "+pack+";\n";
			 System.out.println(pack);
			 try {
				 br = new BufferedReader(new FileReader(".\\src\\IModelDao.java"));
				 bw = new BufferedWriter(new FileWriter(dao));
				 String line=null;
				 bw.write(pack);
				 bw.write(importName);
				 while( (line = br.readLine()) != null){
					   if(line.contains("Model")){
						   line = line.replaceAll("Model", EntityName);
					   }
					   if(line.contains("note")){
						   line = line.replaceAll("note", note);
					   }
					   bw.write(line+"\n");
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
	public static void copyDaoImpl(File daoImpl, String EntityName, String note) {
		//复制文件数据
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		 String pack=daoImpl.getParent().replace("\\", ".");
		 pack=pack.substring(pack.indexOf("src")+4, pack.length());
		 String importName1="import "+pack.replace("dao.impl", "model")+"."+EntityName+";\n";
		 String importName2="import "+pack.replace("dao.impl", "dao")+".I"+EntityName+"Dao;\n";
		 pack="package "+pack+";\n";
		 System.out.println(pack);
		 try {
			 br = new BufferedReader(new FileReader(".\\src\\ModelDaoImpl.java"));
			 bw = new BufferedWriter(new FileWriter(daoImpl));
			 String line=null;
			 bw.write(pack);
			 bw.write(importName1);
			 bw.write(importName2);
			 while( (line = br.readLine()) != null){
				   if(line.contains("Model")){
					   line = line.replaceAll("Model", EntityName);
				   }
				   if(line.contains("model")){
					   line = line.replaceAll("model", Utils.toLowerFirstCase(EntityName));
				   }
				   if(line.contains("note")){
					   line = line.replaceAll("note", note);
				   }
				   bw.write(line+"\n");
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
	public static void copyService(File service, String EntityName, String note) {
		//复制文件数据
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		 String pack=service.getParent().replace("\\", ".");
		 pack=pack.substring(pack.indexOf("src")+4, pack.length());
		 String importName="import "+pack.replace("service", "model")+"."+EntityName+";\n";
		 pack="package "+pack+";\n";
		 System.out.println(pack);
		 try {
			 br = new BufferedReader(new FileReader(".\\src\\IModelService.java"));
			 bw = new BufferedWriter(new FileWriter(service));
			 String line=null;
			 bw.write(pack);
			 bw.write(importName);
			 while( (line = br.readLine()) != null){
				   if(line.contains("Model")){
					   line = line.replaceAll("Model", EntityName);
				   }
				   if(line.contains("note")){
					   line = line.replaceAll("note", note);
				   }
				   bw.write(line+"\n");
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
	public static void copyServiceImpl(File serviceImpl, String EntityName,
			String note) {
		//复制文件数据
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		 String pack=serviceImpl.getParent().replace("\\", ".");
		 pack=pack.substring(pack.indexOf("src")+4, pack.length());
		 String importName1="import "+pack.replace("service.impl", "model")+"."+EntityName+";\n";
		 String importName2="import "+pack.replace("service.impl", "service")+".I"+EntityName+"Service;\n";
		 String importName3="import "+pack.replace("service.impl", "dao")+".I"+EntityName+"Dao;\n";
		 pack="package "+pack+";\n";
		 System.out.println(pack);
		 try {
			 br = new BufferedReader(new FileReader(".\\src\\ModelServiceImpl.java"));
			 bw = new BufferedWriter(new FileWriter(serviceImpl));
			 String line=null;
			 bw.write(pack);
			 bw.write(importName1);
			 bw.write(importName2);
			 bw.write(importName3);
			 while( (line = br.readLine()) != null){
				   if(line.contains("Model")){
					   line = line.replaceAll("Model", EntityName);
				   }
				   if(line.contains("model")){
					   line = line.replaceAll("model", Utils.toLowerFirstCase(EntityName));
				   }
				   if(line.contains("note")){
					   line = line.replaceAll("note", note);
				   }
				   bw.write(line+"\n");
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
	public static void copyController(File controller, String EntityName,
			String note) {
		//复制文件数据
		 BufferedReader br = null;
		 BufferedWriter bw =null;
		 String pack=controller.getParent().replace("\\", ".");
		 pack=pack.substring(pack.indexOf("src")+4, pack.length());
		 String importName="import "+pack.replace("controller", "service")+".I"+EntityName+"Service;\n";
		 pack="package "+pack+";\n";
		 System.out.println(pack);
		 try {
			 br = new BufferedReader(new FileReader(".\\src\\ModelController.java"));
			 bw = new BufferedWriter(new FileWriter(controller));
			 String line=null;
			 bw.write(pack);
			 bw.write(importName);
			 while( (line = br.readLine()) != null){
				   if(line.contains("Model")){
					   line = line.replaceAll("Model", EntityName);
				   }
				   if(line.contains("model")){
					   line = line.replaceAll("model", Utils.toLowerFirstCase(EntityName));
				   }
				   if(line.contains("note")){
					   line = line.replaceAll("note", note);
				   }
				   bw.write(line+"\n");
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
