package cn.zzk.Sort_001;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;

/**
 * 自底向上的归并算法（自顶向上）
 * @author Administrator
 *
 */
public class MergeBU extends BaseSort{
	
	/**
	 * 归并所需的辅助数组
	 */
	private static Comparable[] aux;
	
	/**
	 * 归并算法
	 * @param a
	 */
	public static void sort(Comparable[] a){
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 0; sz<N; sz = sz+sz)
			for(int lo = 0; lo<N-sz; lo+=sz+sz){
				merge(a, lo, lo+sz-1, Math.min(lo+sz-1,N-1));
			}
	}
	/**
	 * 原地归并的抽象方法
	 * @param a
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		int i = lo,j = mid+1;
		
		for(int k=lo; k<=hi; k++){
			aux[k] = a[k];
		}
		for(int k =lo; k<=hi; k++){
			if( i>mid ) 
				a[k] = aux[j++];
			else if(j>hi)
				a[k] = aux[i++];
			else if(less(aux[j],aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	
}
