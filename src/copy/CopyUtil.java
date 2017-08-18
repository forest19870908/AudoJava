package copy;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class CopyUtil{
	/**
	 * 复制对象
	 * @param source
	 * @param dest
	 * @throws Exception
	 */
	public static void Copy(Object source, Object dest)throws Exception {
		//获取属性
		BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
		PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
		
		BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
		PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();
		
		try{
			for(int i=0;i<sourceProperty.length;i++){
				
				for(int j=0;j<destProperty.length;j++){
					
					if(sourceProperty[i].getName().equals(destProperty[j].getName())){
						//调用source的getter方法和dest的setter方法
						Method method = destProperty[j].getWriteMethod();
						if(method!=null)
						{		
							String className=destProperty[j].getReadMethod().getReturnType().getCanonicalName();
							if(className==null||!(className.contains("zsl")||className.contains("List")))
							{
								method.invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
							}
						}
						break;					
					}
				}
			}
		}catch(Exception e){
			throw new Exception("属性复制失败:"+e.getMessage());
		}
	}
}