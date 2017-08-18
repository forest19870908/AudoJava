package IntegerTest;
/**
 * 测试Integer是否是不可变的
 * @author gq
 * @time 2017年5月16日
 */
public class IntegerTest {
	public static void main(String[] args) {
		String s="5";
		String s1=s;
		System.out.println(s==s1);
		Integer i=new Integer(5);
		System.out.println(i.hashCode());
		Integer i2=i+1;
		System.out.println(i==i2);
	}
}
