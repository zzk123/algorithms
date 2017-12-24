package cn.zzk.Sort_003;

/**
 * 基于quick-find算法
 * @author Administrator
 *
 */
public class UF_quick_find {
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
	public UF_quick_find(int N){
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
		return id[p];
	}
	/**
	 * 在p和q之间添加一条连接  
	 * @param p
	 * @param q
	 */
	public void union(int p, int q){
		//将p和q归并到相同的分量中
		int pID = find(p);
		int qID = find(q);
		
		//如果p和q已经在相同的分量之中则不需要采取任何行动
		if(pID == qID)
			return;
		
		//将p的分量重命名为q的名称
		for(int i=0; i<id.length; i++)
			if(id[i]==pID)
				id[i] = qID;
		count--;
	}

	
}
