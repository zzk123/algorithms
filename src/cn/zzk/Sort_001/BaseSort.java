package cn.zzk.Sort_001;

import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * 作为父类，建好相关函数
 * @author Administrator
 *
 */
public class BaseSort {
	/**
	 * 比较大小
	 * 注：v.compareTo(w) v<w(-1) v=w(0) v>w(1)
	 * 	只有当v<w，返回true
	 * 	当v=w或v>w，返回false
	 * @param v
	 * @param w
	 * @return
	 */
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
	/**
	 * 调换位置
	 * @param a
	 * @param i
	 * @aram j
	 */
	public static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	/**
	 * 打印数组
	 * @param a
	 */
	public static void show(Comparable[] a){
		//单行中打印数组
		for(int i=0; i<a.length; i++){
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	/**
	 * 打印数组，通过绘图
	 * @param <T>
	 */
	public static void showByDraw(Double[] a){
		int N = a.length;
		for(int i=0; i<N; i++){
			double x = 1.0*i/N;
			double y = a[i]/2.0;
			double rw = 0.5/N;
			double rh = a[i]/2.0;
			//StdDraw.clear();
			StdDraw.setPenRadius(0.5);
			StdDraw.filledRectangle(x, y, rw, rh);
		}
	}
	/**
	 * 判断是否有序
	 * @param a
	 * @return
	 */
	public static boolean isSorted(Comparable[] a){
		//测试数组元素是否有序
		for(int i=1; i<a.length; i++){
			if(less(a[i], a[i-1]))
				return false;
		}
		return true;
	}
}
