package cn.zzk.Sort_001;

import java.nio.file.Watchable;
import java.util.Arrays;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 插入排序
 * @author Administrator
 *
 */
public class Insertion extends BaseSort{
	/**
	 * 插入排序算法(升序)
	 * @param a
	 */
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i=1; i<N; i++){
			for(int j=i; j>0 && less(a[j],a[j-1]); j--){
				exch(a, j, j-1);
				//showByDraw(a);
			}
		}
	}

	public static void sort(String[] a, int lo, int hi, int d) {
		// TODO Auto-generated method stub
		
	}
}
