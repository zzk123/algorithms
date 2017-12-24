package cn.zzk.Sort_003;

public class UF_quick_union {
	/**
	 * 分量id（以触点作索引）
	 */
	private int[] id;
	/**
	 * 分量数量
	 */
	private int count;
	
	/**
	 * 以整数标识（0到N-1）初始化N个触点
	 * @param N
	 */
	public UF_quick_union(int N){
		//初始化分量id数组
		count = N;
		id = new int[N];
		for(int i=0; i<N; i++)
			id[i] = i;
	}
	/**
	 * 连通分量的数量
	 * @return
	 */
	public int count(){
		return count;
	}
	/**
	 * 如果p和q存在于同一分量中就返回true
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q){
		return find(p)==find(q);
	}
	/**
	 * p(0到N-1)所在分量的标识符
	 * @param p
	 * @return
	 */
	public int find(int p){
		//找出分量的名称
		while(p!=id[p])
			p = id[p];
		return p;
	}
	/**
	 * 在p和q之间添加一条连接  
	 * @param p
	 * @param q
	 */
	public void union(int p, int q){
		//将p和q的根节点统一
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot)
			return;
		
		id[pRoot] = qRoot;
		
		count--;
	}
}
