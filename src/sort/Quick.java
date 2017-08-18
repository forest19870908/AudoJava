package sort;

import java.util.Arrays;

/**
 * 快速排序
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @author gq
 *
 */
public class Quick {
	private static int[] array = { 49, 38, 65, 97, 76, 13, 27, 65 };
	public static void sort(int[] array){
		int length =array.length;
		if(length==1){
			return;
		}
		sort(array,0,length-1);
	}
	public static void sort(int[] array,int start,int end){
		int length =end-start;
		if(length<1){
			return;
		}
		int middle=start;//数组第一个的下标
		for (int i = start+1; i <= end; i++) {
			if(array[middle]>array[i]){
//				int temp=array[i];
//				int j=i-1;
//				do{
//					array[j+1]=array[j];
//					j--;
//				}while(j>=middle);
				int temp=sortUtils.moveFromTo(array, middle, i);
				array[middle]=temp;
				middle++;
			}
		}
		System.out.println(Arrays.toString(array));
		if(middle>start){
			sort(array,start,middle);
		}
		if(end>middle+1){
			sort(array,middle+1,end);
		}
	}
	public static void main(String[] args) {
		sort(array);
	}

}
