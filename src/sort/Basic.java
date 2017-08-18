package sort;

import java.util.Arrays;
/**
 * 基数排序
 * @author gq
 *基数排序是另外一种比较有特色的排序方式，它是怎么排序的呢？我们可以按照下面的一组数字做出说明：12、 104、 13、 7、 9
    （1）按个位数排序是12、13、104、7、9
    （2）再根据十位排序104、7、9、12、13
    （3）再根据百位排序7、9、12、13、104
    这里注意，如果在某一位的数字相同，那么排序结果要根据上一轮的数组确定，举个例子来说：07和09在十分位都是0，但是上一轮排序的时候09是排在07后面的；
    同样举一个例子，12和13在十分位都是1，
    但是由于上一轮12是排在13前面，所以在十分位排序的时候，12也要排在13前面。
    所以，一般来说，10基数排序的算法应该是这样的？
    （1）判断数据在各位的大小，排列数据；
    （2）根据1的结果，判断数据在十分位的大小，排列数据。如果数据在这个位置的余数相同，那么数据之间的顺序根据上一轮的排列顺序确定；
    （3）依次类推，继续判断数据在百分位、千分位......上面的数据重新排序，直到所有的数据在某一分位上数据都为0。
 */
public class Basic {
	private static int[] array = { 49, 38, 65, 97, 76, 13, 27, 65};
	private static void sort(int[] array) {
		int i=1;
		while(true){
			int t=(int)Math.pow(10, i);
			sort(array,i);//选择排序 按位数排序 i=1 按个位排序 i=10 按十位排序...
			boolean isReturn=true;//是否退出
			for (int j = 0; j < array.length; j++) {
				if(array[j]/t>0){
					isReturn=false;
					break;
				}
			}
			if(isReturn){
				break;
			}
			i++;
		}
	}
	/**
	 * 选择排序 按位数排序 i=1 按个位排序 i=10 按十位排序...
	 * @param array
	 * @param t0
	 */
	private static void sort(int[] array, int t0) {
		int t=(int)Math.pow(10, t0);
		for (int i = 0; i < array.length; i++) {
			for (int j = i+1; j < array.length; j++) {
				if(array[i]/t>array[j]/t){
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
