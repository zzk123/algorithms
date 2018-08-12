package cn.test.test1.sort;

/**
 * 快速排序
 * @author zzk
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Quick {
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) {
			return;
		}
		int j = partition(a, lo, hi);
		//对左边进行排序
		sort(a, lo, j-1);
		//对右边进行排序
		sort(a, j+1, hi);
	}
	/*
	 * 切分
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		Comparable t;
		while(true) {
			while(a[++i].compareTo(v) < 0) {
				if(i == hi) {
					break;
				}
			} 
			while(v.compareTo(a[--j]) < 0) {
				if(j == lo) {
					break;
				}
			}
			if(i >= j) {
				break;
			}
			t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		t = a[lo];
		a[lo] = a[j];
		a[j] = t;
		System.out.println("最终：");
		show(a);
		return j;
	}
	
	public static void main(String[] args) {
		Comparable[] a = new Comparable[]
				{11,0,3,2,12,14,20,2,3};
				//{3,2,1,8,9,10,0,1,3,1};
		Quick.sort(a);
	}
	
	public static void show(Comparable[] a) {
		System.out.println("show:");
		for(int i=0; i < a.length; i++) {
			System.out.print(a[i] + "   ");
		}
		System.out.println();
	}
}
