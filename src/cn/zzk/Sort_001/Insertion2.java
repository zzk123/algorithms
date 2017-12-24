package cn.zzk.Sort_001;

import java.nio.file.Watchable;

import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;

/**
 * 插入排序（改进）2.1.25
 * @author Administrator
 *
 */
public class Insertion2 extends BaseSort{
	/**
	 * 插入排序算法(升序)
	 * @param a
	 */
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i=1; i<N; i++){
			//用一个变量记录a[i],逐一去跟a[j],a[j-1]比较，如果大于就向右
			Comparable temp = a[i];
			int j = i;
			for(; j>0 && less(temp,a[j-1]); j--){
				a[j] = a[j-1];
			}
			temp = a[j];
		}
	}
	
}
