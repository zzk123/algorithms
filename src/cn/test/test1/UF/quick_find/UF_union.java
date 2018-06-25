package cn.test.test1.UF.quick_find;
/**
 * 对UF的算法进行改进，修改find()，union()方法
 * 
 * 
 * @author zzk
 *
 */
public class UF_union {
	
	private int[] id;  //分量id
	
	private int count; //分量数量
	
	private int idCount;//访问id数组次数
	
	public UF_union(int N) {
		//初始化分量id
		idCount = 0;
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	public int count() {
		return count;
	}
	/**
	 * 判断两个分量是否相等，也就是相连
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	/**
	 * 查找分量
	 * 换成查找根节点
	 * 如果p与某个节点有关联，那么id[p]存储的就是根节点，否则就是p
	 * @param p
	 * @return
	 */
	public int find(int p) {
		idCount++;
		while(p != id[p]) {
			p = id[p];
		}
		return p;
	}
	/**
	 * 合并分量
	 * 统一根节点的值
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		//将p和q的根节点统一
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) {
			return;
		}
		id[pRoot] = qRoot;
		count --;
	}
	public int getIdCount() {
		return idCount;
	}
	
	
}
