package cn.zzk.Sort_001;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 三向切分的快速算法
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class Quick3way extends BaseSort{
	
	/**
	 * 快速排序算法
	 * @param a
	 */
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	
	/**
	 * 三向切分的快速算法
	 * @param a
	 */
	public static void sort(Comparable[] a,int lo, int hi){
		if(hi<=lo)
			return;
		int lt = lo,i = lo+1, gt = hi;
		Comparable v = a[lo];
		while(i<=gt){
			int cmp = a[i].compareTo(v);
			if(cmp<0)
				exch(a, lt++, i++);
			else if(cmp>0)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}
}
