package util;


public class Utils {
	/**
	 * 将首字母变成小写
	 * @param entityName
	 * @return
	 */
	public static String toLowerFirstCase(String entityName) {
		char[] chars=new char[1];  
        chars[0]=entityName.charAt(0);  
        String temp=new String(chars);  
        if(chars[0]>='A'  &&  chars[0]<='Z')  
        {  
        	return entityName.replaceFirst(temp,temp.toLowerCase());  
        }  
		return entityName;
	}
	
}
