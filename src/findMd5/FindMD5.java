package findMd5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 保存9位以及9位一下的密码
 * @author gq
 * @time 2016年11月28日
 */
public class FindMD5 {
	public static String[] shuffle = new String[] { "0","1","2", "3", "4", "5", "6", "7",  
            "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",  
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",  
            "w", "x", "y", "z"};
	public static String[] shuffle2 = new String[] { "0","1","2", "3", "4", "5", "6", "7",  
        "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",  
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",  
        "w", "x", "y", "z","A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
        "W", "X", "Y", "Z","_","-","=","+",".","~","!","@","#","$","%","^","&","*","(",")","/","|","\\" };
	public static int shuffleLength=shuffle.length;
	public static void main(String[] args) {
//		//密码有几个数据或者字母组成的
//		for (int i = 1; i < shuffle.length; i++) {
//			//有多少种组合情况的
//		}
//		while(true){
//			StringBuffer password=new StringBuffer();
//			int len=(int) (Math.random()*9);
//			if(len>=3){
//				for (int i = 0; i < len; i++) {
//					int select=(int) (Math.random()*(shuffleLength-1));
//					password.append(shuffle[select]);
//				}
//				String md5=DigestUtils.md5Hex(password.toString());
//				JdbcUtils.insert("insert into passwordmd5 (password,md5,mysql) values ('"+password+"','"+md5+"',password('"+password+"'))");
//			}
//		}
		List<String> list=getCoopList(1,Arrays.asList(test));
//		list=getCoopList(2,list);
		System.out.println(list.toString());
	}
	public static String[] test = new String[]{"1","2","3","4","5"};
	/**
	 * 按循序得到排列组合列表
	 * @param num 秘密是几位数的
	 * @return
	 * @author gq
	 * @time 2016年11月30日
	 */
	public static List<String> getCoopList(int n,List<String> fromList){
		if(n==0){
			return fromList;
		}
		List<String> toList=new ArrayList<String>();
		for (int i = 0; i < test.length; i++) {
			if(fromList.size()==0){
				toList.add(test[i]);
			}else{
				for (int j = 0; j < fromList.size(); j++) {				
					toList.add(fromList.get(j)+test[i]);
				}
			}
		}
		return getCoopList(n-1,toList);
	}
}