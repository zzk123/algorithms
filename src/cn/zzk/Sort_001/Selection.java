package cn.zzk.Sort_001;

import edu.princeton.cs.algs4.StdOut;

/**
 * 选择排序
 * @author Administrator
 *
 */
public class Selection extends BaseSort{
	
	/**
	 * 选择排序算法
	 * 用了N次交换-交换次数和数组的大小是线性排序
	 * @param a
	 */
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i=0; i<N; i++){
			int min = i;
			for(int j=i+1; j<N; j++){
				if(less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
		}
	}
}
