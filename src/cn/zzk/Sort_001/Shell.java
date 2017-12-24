package cn.zzk.Sort_001;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;

/**
 * 希尔排序
 * @author Administrator
 *
 */
public class Shell extends BaseSort{
	/**
	 * 希尔排序
	 * @param a
	 */
	public static void sort(Comparable[] a){
		int N = a.length;
		int h=1;
		while(h<N/3)
			h=h*3+1;
		while(h>=1){
			for(int i=h; i<N; i++){
				for(int j=i; j>=h && less(a[j], a[j-h]); j-=h){
					exch(a, j, j-h);
				}
			}
			h = h/3;
		}
	}
}
