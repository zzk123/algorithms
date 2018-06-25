package cn.test.test1.UF.quick_find;
/**
 * 对UF_union进行改进
 * 加权quick_union的算法
 * @author zzk
 *
 */
public class WeightedQuickUnionUF {
	private int[] id;		//父链接数组(由触点索引)
	private int[] sz;		//(由触点索引的)各个根节点所对应的分量的大小
	private int count;		//连接分量的数量
	private int idCount;	//访问id数组次数
	public WeightedQuickUnionUF(int N) {
		idCount = 0;
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
		sz = new int[N];
		for(int i = 0; i < N; i++) {
			sz[i] = 1;
		}
	}
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	//跟随连接找到根节点
	public int find(int p) {
		idCount++;
		while(p != id[p]) {
			p = id[p];
		}
		return p;
	}
	//连接
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if(i == j) {
			return;
		}
		//将小树的根节点连接到大树的节点
		if(sz[i] < sz[j]) {
			idCount++;
			id[i] = j;
			sz[j] += sz[i];
		}else {
			idCount++;
			id[j] = i;
			sz[i] = sz[j];
		}
		count --;
	}
	public int getIdCount() {
		return idCount;
	}
	
}
