package sort;

import java.util.Arrays;

/**
 * 合并排序
 * 合并排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，
 * 每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
 * @author gq
 *
 */
public class Merge {
	private static int[] array = { 49, 38, 65, 97, 76, 13, 27, 65 };
	public static void sort(int[] array){
		int length=array.length;
		int middle=length/2;
		if(length>1){
			int[] leftArray=Arrays.copyOfRange(array, 0, middle);
			int[] rightArray=Arrays.copyOfRange(array, middle, length);
			sort(leftArray);
			sort(rightArray);
			merge(array,leftArray,rightArray);
		}
	}
	private static void merge(int[] array,int[] leftArray, int[] rightArray) {
		int i=0;
		int j=0;
		int k=0;
		while (i<leftArray.length && j<rightArray.length) {
			if(leftArray[i]<=rightArray[j]){
				array[k]=leftArray[i];
				i++;
				k++;
			}else{
				array[k]=rightArray[j];
				j++;
				k++;
			}
		}
		while(i<leftArray.length){
			array[k]=leftArray[i];
			i++;
			k++;
		}
		while(j<rightArray.length){
			array[k]=rightArray[j];
			j++;
			k++;
		}
	}
	public static void main(String[] args) {
		sort(array);
		System.out.println(Arrays.toString(array));
	}

}
