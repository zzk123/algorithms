package cn.zzk.Sort_004;
/**
 * 低位优先的字符串排序
 * @author Administrator
 *
 */
public class LSD {
	public static void sort(String[] a, int W) {
		//通过前W个字符将a[]排序
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
		
		for(int d = W-1; d >= 0; d-- ) {
			//根据第d个字符用键索引
			int[] count = new int[R+1];
			for(int i = 0; i < N; i++) {
				System.out.println(a[i]+":"+d+":"+a[i].charAt(d));
				count[a[i].charAt(d)+1]++;
			}
			//将频率转换为索引
			for(int r = 0; r < R; r++) {
				count[r+1] += count[r];
				System.out.println((r+1)+":"+count[r+1]);
			}
			//将元素分类
			for(int i = 0; i < N; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];
			}
			//回写
			for(int i = 0; i < N; i++) {
				a[i] = aux[i];
			}
		}
	}
	public static void main(String[] args) {
		String a[] = new String[] {
				"4PGC938","2IYE230","3CIO720","1ICK750",
				"10HV845","4JZY524","1ICK750","3CIO720",
				"10HV845","2RLA629","3ATW723"};
		sort(a, 7);
	}
}
