package cn.zzk.Sort_001;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class Quick extends BaseSort{
	/**
	 * 快速排序算法
	 * @param a
	 */
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi<=lo)
			return;
		int j = partition(a, lo, hi);    //切分
		sort(a,lo,j-1);					 //将左半部分切分
		sort(a,j+1,hi);					 //将右半部分切分
	}
	
	/**
	 * 快速排序的切分
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi+1;
		Comparable v = a[lo];
		while(true){
			//扫描左右，检查扫描是否结束并交换元素
			while(less(a[++i],v))
				if(i==hi)
					break;
			while(less(v,a[--j]))
				if(j==lo)
					break;
			if(i>=j)
				break;
			exch(a,i,j);
		}
		exch(a, lo, j);
		return j;
	}
}
