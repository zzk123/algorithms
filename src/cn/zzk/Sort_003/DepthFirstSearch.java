package cn.zzk.Sort_003;

/**
 * Tremaux搜索
 * @author Administrator
 *
 */
public class DepthFirstSearch {
	
	private boolean[] marked;
	private int count;
	
	/**
	 * 初始化图G和起始点s
	 * @param G
	 * @param s
	 */
	public DepthFirstSearch(Graph G, int s){
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	/**
	 * 初始化s所在的marked数组位置为true，并且与之相连的也为true
	 * @param G
	 * @param v
	 */
	private void dfs(Graph G, int v){
		marked[v] = true;
		count++;
		//循环初始化相连的点
		for(int w : G.adj(v)){
			if(!marked[w])
				dfs(G, w);
		}
	}
	
	public boolean marked(int w){
		return marked[w];
	}
	
	public int count(){
		return count;
	}
}
