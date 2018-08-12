package cn.test.test1.sort;

/**
 * 插入排序
 * @author zzk
 *
 */
public class Insertion {
	
	/**
	 * 每一次与前面的元素进行比较，如果小于就进行交换
	 */
	public static void sort(Comparable[] a) {
		int N = a.length;
		Comparable t;
		for(int i = 0; i < N; i++) {
			for(int j = i; j > 0 && a[j].compareTo(a[j-1]) > 0; j--) {
				t = a[j];
				a[j] = a[j-1];
				a[j-1] = a[j];
			}
			
		}
	}
	/**
	 * 对sort()的进行改进，对于较大的元素向右移动，不进行交换操作
	 * 每一次进行a[i]与最前面的元素进行比较，如果找到大于a[i]的元素向右移动，直到找到小于或者等于a[i]的元素停止
	 */
	public static void sort2(Comparable[] a) {
		int N = a.length;
		Comparable t ;
		int j;
		for(int i = 0; i < N; i++) {
			t = a[i];
			for(j = i; j > 0 && t.compareTo(a[j-1]) < 0; j--) {
				a[j] = a[j-1];
			}
			a[j] = t;
		}
	}
}
