package ServiceDao;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * 自动生成接口 service dao
 * @author gq
 *
 */
public class ServiceDaoAudo {
	public static String directory="D:\\java\\workspace\\gyl_zsl_v0.27\\src"; //绝对目录
	public static String toDirectory="com.zsl.web.modules.company.service";
	public static TypeEnum type=TypeEnum.SERVICE;
//	public static TypeEnum type=TypeEnum.DAO;
	public final static String toFile="CompanyGradeDetail";
	private static List<File> listFileResults=new ArrayList<File>();
	
	private static File findFile(File dir) {
		String[] toFiles=toDirectory.split("\\.");
		for (String child : toFiles) {
			dir=new File(dir,child);
		}
		dir=new File(dir,toFile);
        return dir;
    }
	private static void test1(){
		File file=new File(directory);
		File resultFile=findFile(file);
		System.out.println(resultFile.getName());
		recursionFile(resultFile.getParentFile());
	}
	private static void recursionFile2(List<File> files){
		for (File file : files) {
			if(file.isDirectory()){
				listFileResults.add(file);
				System.out.println("目录："+file.getName());
				List<File> list=Arrays.asList(file.listFiles());
				recursionFile2(list);
			}else{
				System.out.println("文件："+file.getName());
			}
		}
	}
	private static List<File> recursionFile(File dir){
			FileFilter filter=new FileFilter() {
				
				@Override
				public boolean accept(File paramFile) {
					if(paramFile.getName().contains(toFile)){
						return true;
					}
					return false;
				}
			};
			File[] files = dir.listFiles(filter);
			for (File file : files) {
				System.out.println("结果文件："+file.getName());
			}
		return Arrays.asList(files);
	}
	/**
	 * 类型枚举
	 */
	public enum TypeEnum {
	    SERVICE(1,"service方法"),
	    DAO(10,"dao方法");
	    
	    private Integer key;
	    private String value;
	    
	    private TypeEnum(Integer key, String value) {
	        this.key = key;
	        this.value = value;
	    }
	    
	    public static String getValue(Integer key) {
	        for (TypeEnum c : TypeEnum.values()) {
	            if (c.getKey().equals(key)) {
	                return c.getValue();
	            }
	        }
	        return null;
	    }

	    public Integer getKey() {
	        return key;
	    }
	    public void setKey(Integer key) {
	        this.key = key;
	    }
	    
	    public String getValue() {
	        return value;
	    }
	    public void setValue(String value) {
	        this.value = value;
	    }
	    
	}
	public static void main(String[] args) throws IOException {
		List<File> list=new ArrayList<File>();
		List<File> listFiles=new ArrayList<File>();
		list.add(new File(directory));
		recursionFile2(list);
		for (File file : listFileResults) {
			listFiles.addAll(recursionFile(file));
		}
		System.out.println("--------------------------");
		File serviceFile=null;
		File serviceImplFile=null;
		File daoFile=null;
		File daoImplFile=null;
		for (File file : listFiles) {
			System.out.println("结果文件："+file.getName());
			String fileName=file.getName().substring(0, file.getName().lastIndexOf("."));
			if(fileName.endsWith("Service")){
				serviceFile=file;
			}
			if(fileName.endsWith("ServiceImpl")){
				serviceImplFile=file;
			}
			if(fileName.endsWith("Dao")){
				daoFile=file;
			}
			if(fileName.endsWith("DaoImpl")){
				daoImplFile=file;
			}
		}
		
		AddToContent.getContent(serviceFile);//获得注释接口
		AddToContent.addContentInterface(daoFile);//生成dao层接口
		AddToContent.addContentDao(daoImplFile);//生成dao层实现
	}

}
