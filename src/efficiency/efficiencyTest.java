package efficiency;

public class efficiencyTest {
	public static void main(String[] args) {
		long a=System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			String s="1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1"
					+"1";
		}
		long b=System.currentTimeMillis();
		System.out.println("第一种方式"+(b-a)+"ms");
		for (int i = 0; i < 1000000; i++) {
			String s="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
			s+="1";
		}
		long c=System.currentTimeMillis();
		System.out.println("第二种方式"+(c-b)+"ms");
		for (int i = 0; i < 1000000; i++) {
			StringBuffer s=new StringBuffer("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
			s=s.append("1");
		}
		long d=System.currentTimeMillis();
		System.out.println("第三种方式"+(d-c)+"ms");
	}
	
}
