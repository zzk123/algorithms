package cn.test.test1.sort;
/**
 * 归并排序
 * 自顶向下的归并排序
 * @author zzk
 *
 */
public class Merge {
	
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) {
			return;
		}
		int mid = lo + ( hi - lo ) / 2;
		sort(a, lo, mid);
		sort(a, mid+1, lo);
		merge(a, lo, mid, hi);
	}
	
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for(int k = lo; k <= hi; k++) {
			//如果i > mid，
			//a[lo]到a[mid]之间的元素整理好了
			//对于a[lo]到a[mid]之间的元素完全大于a[mid]与a[hi]之间的元素
			//那么接下来整理a[mid]与a[hi]之间的元素
			if(i > mid) {
				a[k] = aux[j++];
			}else if(j > hi) {
				a[k] = aux[i++];
			}
			//进行比较，a[lo]与a[mid]比较
			//a[j] < a[i]
			else if(aux[j].compareTo(a[i]) < 0){
				a[k] = aux[j++];
			}
			//a[j] > a[i]
			else {
				a[k] = aux[i++];
			}
		}
	}
}
