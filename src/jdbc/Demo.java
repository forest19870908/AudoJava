package jdbc;

import java.sql.ResultSet;  
import java.sql.SQLException;  
  
public class Demo {  
  
//    static String sql = null;  
//    static DBHelper db1 = null;  
//    static ResultSet ret = null;  
  
    public static void main(String[] args) { 
    	long a=System.currentTimeMillis();
    	Thread[] ts=new Thread[1];
        for (int i = 0; i < ts.length; i++) {
        	ts[i]=new Thread(new Runnable() {
				@Override
				public void run() {
					String sql = "select id from z_jxc_sale where id=1";//SQL语句 
					DBHelper db1 = new DBHelper(sql);//创建DBHelper对象  
					ResultSet ret=null;
			        try {  
				            ret = db1.pst.executeQuery();//执行语句，得到结果集  
				        while (ret.next()) {  
				            String saleId = ret.getString(1);  
//				            System.out.println(saleId + "\t");  
				        }//显示数据  
				        ret.close();  
				        db1.close();//关闭连接 
				    } catch (SQLException e) {  
				        e.printStackTrace();  
				    }
					
				}
			});
        	ts[i].start();
		      
		}
        while(Thread.activeCount()>1){
        	Thread.yield();
        }
        long b=System.currentTimeMillis();
        System.out.println(b-a+"ms");
    }  
  
} 
