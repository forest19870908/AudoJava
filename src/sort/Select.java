package sort;

import java.util.Arrays;
/**
 * 选择排序
 * @author gq
 *（1）每次排序的时候都需要寻找第n小的数据，并且和array[n-1]发生交换
    （2）等到n个数据都排序好，那么选择排序结束。
 */
public class Select {
	private static int[] array = { 49, 38, 65, 97, 76, 13, 27, 65};
	private static void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i+1; j < array.length; j++) {
				if(array[i]>array[j]){
					sortUtils.change(array, i, j);
				}
			}
		}
	}
	public static void main(String[] args) {
		sort(array);
		System.out.println(Arrays.toString(array));
	}

}
