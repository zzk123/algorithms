package cn.test.test1.sort;

/**
 * 选择排序
 * @author zzk
 *
 */
public class Selection {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
			int min = i;
			//循环查找最小的值
			for(int j = i+1; j < N; j++) {
				if(a[i].compareTo(a[j]) > 0) {
					min = j;
				}
			}
			//交换位置
			Comparable t = a[i];
			a[i] = a[min];
			a[min] = t;
		}
	}
}
