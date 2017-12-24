package cn.zzk.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * 二分查找
 * @author Administrator
 *
 */
public class BinarySearch {
	public static int rank(int key, int[] a) {
		int  lo = 0;
		int hi = a.length -1;
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			if(key<a[mid]){
				hi = mid-1;
		    }else if(key>a[mid]){ 
				lo = mid+1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String r = null;
		try{
			r = reader.readLine();//按行读取输入的信息
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] str = r.split(",");
		int[] a = new int[str.length];//用来存储输入字符
		
		for(int i=0;i<str.length;i++) {
			a[i] = Integer.parseInt(String.valueOf(str[i]));
		}
		Arrays.sort(a);//只有当数组有序时才能进行二分查找
		int key = 5;
		key = rank(key, a);
		StdOut.println(key);
	}
	/*public static void main(String[] args) {
		int[] whitelist = In.readInts(args[0]);
		Arrays.sort(whitelist);
		while(!StdIn.isEmpty()){
			int key = StdIn.readInt();
			if(rank(key, whitelist)<0)
				StdOut.println(key);
		}
	}*/
}
