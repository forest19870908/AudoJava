package sort;

public class sortUtils {
	/**
	 * 交换数据i和j2个位置的值
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void change(int[] array, int i,int j){
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	/**
	 * 数据往后移
	 * @param array
	 * @param from
	 * @param to
	 * @return
	 */
	public static int moveFromTo(int[] array,int from,int to){
		int result=array[to];
		int j=to-1;
		do{
			array[j+1]=array[j];
			j--;
		}while(j>=from);
		return result;
	}
}
