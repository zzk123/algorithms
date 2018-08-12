package cn.test.test1.sort;
/**
 * 希尔排序
 * @author zzk
 *
 */
public class Shell {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		Comparable t;
		while( h < N/3 ) {
			h = 3*h + 1;
		}
		while( h >= 1) {
			for(int i = h; i < N; i++) {
				for(int j = i; j > 0 && a[j].compareTo(a[j-h]) < 0; j-=h) {
					t = a[j-h];
					a[j-h] = a[j];
					a[j] = t;
				}
			}
			h = h / 3;
		}
	}
}
