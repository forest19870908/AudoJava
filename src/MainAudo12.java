import java.io.File;
import java.io.IOException;

import copy.Copy;

/**
 *  自动生成代码执行主方法
 * @author gq
 *
 */
public class MainAudo12 {
//	public static String directory=".\\src\\file"; //相对目录
	public static String directory="D:\\java2\\workspace\\gylzsl31\\src"; //绝对目录
	public static String toFile="com.zsl.web.modules.cw.model";
	public static String EntityName="ToFundInventoryTimeDay";
	public static String note="库存吨数分布每日增量表";//注释
	public static String tableName="to_fund_inventory_time_day";//实体表名
	 /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
	public static void dealFile(){
		File file=new File(directory);
		//清除目录以前的内容
//		deleteDir(file);
		//创建file目录
		if(!file.isDirectory()){
			file.mkdir();
		}
		String[] toFiles=toFile.split("\\.");
		for (String child : toFiles) {
			File childFile=new File(file,child);
			if(!childFile.isDirectory()){
				childFile.mkdir();
			}
			file=childFile;
		}
		//创建实体文件
		File model=new File(file,EntityName+".java");
		try {
			model.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Copy.copyModel(model, EntityName, note, tableName); 
		//创建dao文件
		File daoFile=new File(model.getParentFile().getParentFile(),"dao");
		if(!daoFile.isDirectory()){
			daoFile.mkdir();
		}
		File dao=new File(daoFile,"I"+EntityName+"Dao.java");
		try {
			dao.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Copy.copyDao(dao, EntityName, note);
		//创建daoImpl文件
		File daoImplFile=new File(daoFile,"impl");
		if(!daoImplFile.isDirectory()){
			daoImplFile.mkdir();
		}
		File daoImpl=new File(daoImplFile,EntityName+"DaoImpl.java");
		try {
			daoImpl.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Copy.copyDaoImpl(daoImpl, EntityName, note);
		//创建service文件
		File serviceFile=new File(model.getParentFile().getParentFile(),"service");
		if(!serviceFile.isDirectory()){
			serviceFile.mkdir();
		}
		File service=new File(serviceFile,"I"+EntityName+"Service.java");
		try {
			service.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Copy.copyService(service, EntityName, note);
		//创建serviceImpl文件
		File serviceImplFile=new File(serviceFile,"impl");
		if(!serviceImplFile.isDirectory()){
			serviceImplFile.mkdir();
		}
		File serviceImpl=new File(serviceImplFile,EntityName+"ServiceImpl.java");
		try {
			serviceImpl.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Copy.copyServiceImpl(serviceImpl, EntityName, note);
		//创建controller文件
		File controllerFile=new File(model.getParentFile().getParentFile(),"controller");
		if(!controllerFile.isDirectory()){
			controllerFile.mkdir();
		}
		File controller=new File(controllerFile,EntityName+"Controller.java");
		try {
			controller.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Copy.copyController(controller, EntityName, note);
	}
	public static void main(String[] args) {
		dealFile();
	}
}
