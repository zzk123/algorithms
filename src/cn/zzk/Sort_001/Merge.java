package cn.zzk.Sort_001;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;

/**
 * 归并排序
 * 
 * @author Administrator
 *
 */
public class Merge extends BaseSort{
	/**
	 * 自顶向下的归并排序
	 * @param a
	 */
	private static Comparable[] aux;	//归并所需的辅助数组
	
	public static void sort(Comparable[] a){
		aux = new Comparable[a.length]; //一次性分配空间
		
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi<=lo)
			return;
		int mid = lo + (hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a, lo, mid, hi);
	}
	
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		int i = lo,j = mid+1;
		for(int k=lo; k<=hi; k++){
			aux[k] = a[k];
		}
		for(int k=lo; k<=hi; k++){
			if(i>mid)
				a[k] = aux[j++];
			else if(j>hi)
				a[k] = aux[i++];
			else if(less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	
}
