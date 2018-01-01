package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.Stack;
/**
 * 有向图的寻路
 * @author Administrator
 *
 */
public class DepthFirstDirectedPaths {
	
	private boolean[] marked; //这个顶点上调用过dfs（）吗
	private int[] edgeTo;	//从起点到一个顶点的已知路径上的最后一个顶点
	private final int s;	//起点
	
	public DepthFirstDirectedPaths(Digraph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		validateVertex(s);
		dfs(G, s);
	}
	
	private void dfs(Digraph G, int v){
		marked[v] = true;
		for(int w : G.adj(v)){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	public boolean hasPathTo(int v){
		validateVertex(v);
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		validateVertex(v);
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	
	private void validateVertex(int v){
		int V = marked.length;
		if(v < 0 || v >= V){
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
		}
	}
}
