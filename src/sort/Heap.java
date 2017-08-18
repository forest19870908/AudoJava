package sort;

import java.util.Arrays;
/**
 * 堆排序
 *  堆排序是另外一种常用的递归排序。因为堆排序有着优秀的排序性能，所以在软件设计中也经常使用。堆排序有着属于自己的特殊性质，
 *  和二叉平衡树基本是一致的。打一个比方说，处于大堆中的每一个数据都必须满足这样一个特性：
    （1）每一个array[n] 不小于array[2*n]
    （2）每一个array[n]不小于array[2 * n + 1]
    构建这样一个堆只是基础，后面我们需要每次从堆的顶部拿掉一个数据，不断调整堆，直到这个数组变成有序数组为主。所以详细的堆排序算法应该是这样的：
    1）构建大堆，使得堆中的每一个数据都满足上面提到的性质
    2）将堆的第一个数据和堆的最后一个数据进行互换，然后重新调整堆，直到堆重新平衡为止
    3）重复2）的过程，直到整个数组有序。
 * @author gq
 *
 */
public class Heap {
	private static int[] array = { 49, 38, 65, 97, 76, 13, 27, 65 };
	public static void sort(int[] array){
		sort(array,array.length);
	}
	public static void sort(int[] array,int length) {
		if(length==1){
			return;
		}
		int i=0;
		while(2*i+1<length){
			boolean isChange=false;//是否交换了
			if(array[i]<array[2*i]){
//				int temp=array[i];
//				array[i]=array[2*i];
//				array[2*i]=temp;
				sortUtils.change(array, i, 2*i);
				isChange=true;
			}
			if(array[i]<array[2*i+1]){
//				int temp=array[i];
//				array[i]=array[2*i+1];
//				array[2*i+1]=temp;
				sortUtils.change(array, i, 2*i+1);
				isChange=true;
			}
			if(isChange){
				i=0;
			}else{
				i++;
			}
		}
		//第一个数和最后一个数调换
//		int temp=array[0];
//		array[0]=array[length-1];
//		array[length-1]=temp;
		sortUtils.change(array, 0, length-1);
		sort(array, length-1);
	}
	public static void main(String[] args) {
		sort(array);
		System.out.println(Arrays.toString(array));
	}

}
