package sort;

import java.util.Arrays;
/**
 * 插入排序
 * @author gq
 *插入排序的基本思想是：每步将一个待排序的纪录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。
 *直接插入排序的算法思路：
（1） 设置监视哨r[0]，将待插入纪录的值赋值给r[0]；
（2） 设置开始查找的位置j；
（3） 在数组中进行搜索，搜索中将第j个纪录后移，直至r[0].key≥r[j].key为止；
（4） 将r[0]插入r[j+1]的位置上。
 */
public class Insert {
	private static int[] array = { 49, 38, 65, 97, 76, 13, 27, 65};
	private static void sort(int[] array) {
		int[] r=new int[array.length+1];
		for (int i = 0; i < array.length; i++) {
			r[i+1]=array[i];
		}
		r[0]=array[array.length-1];
		for (int i = 1; i < r.length-1; i++) {	
			sort(r,i);
			r[0]=r[r.length-1];
			System.out.println("r:"+Arrays.toString(r));
		}
		for (int i = 1; i < r.length; i++) {
			array[i-1]=r[i];
		}
	}
	public static void sort(int[] r,int length){
		for (int i = 1; i <= length; i++) {
			if(r[0]<=r[i]){
				sortUtils.moveFromTo(r, i, r.length-1);
				r[i]=r[0];
				break;
			}else if(i==length){
				sortUtils.moveFromTo(r, i+1, r.length-1);
				r[i+1]=r[0];
			}
		}
	}
	public static void main(String[] args) {
		sort(array);
		System.out.println("array:"+Arrays.toString(array));
	}

}
