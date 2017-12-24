package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.Stack;

/**
 * 使用深度优先搜索查找图中的路径
 * @author Administrator
 *
 */
public class DepthFirstPaths {

	/**
	 * 这个顶点上调用过dfs()了吗?
	 */
	private boolean[] marked;
	/**
	 * 从起点到一个顶点的已知路径上的最后一个顶点
	 */
	private int[] edgeTo;
	/**
	 * 起点
	 */
	private final int s;
    
	public DepthFirstPaths(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	/**
	 * 在每一次初始化顶点时候，记录路径
	 * @param G
	 * @param v
	 */
	private void dfs(Graph G, int v){
		marked[v] = true;
		for(int w : G.adj(v)){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	/**
	 * 返回起始点s到v的路径
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x!=s; x = edgeTo[x])
			path.push(x);
		return path;
	}
}
