package cn.test.test1.UF.quick_find;
/**
 * union-find实现用于处理网络问题，即两个分量（整数）之间的相连关系处理
 * 只要存在两个相连，就会进行合并
 * 
 * 该算法的运行时间对于最终只能得到少数连通分量的一般应用是平方级别的。
 * 
 * @author zzk
 *
 */
public class UF {
	
	private int[] id; // 分量id
	
	private int count;// 分量数量
	
	private int idCount = 0;
	
	public UF(int N) {
		//初始化分量id
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
	 * @param p
	 * @return
	 */
	public int find(int p) {
		return id[p];
	}
	/**
	 * 合并分量
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		//将p和q归并到相同分量中
		int pId = find(p);
		int qId = find(q);
		
		//如果p和q已经在相同的分量之中则不需要采取任何行动
		if(pId == qId) {
			return;
		}
		//将p的分量重名为q的名称
		for(int i=0; i<id.length; i++) {
			idCount++;
			if(id[i] == pId) {
				id[i] = qId;
			}
		}
		count --;
	}
	public int getIdCount() {
		return idCount;
	}
	
	
}
