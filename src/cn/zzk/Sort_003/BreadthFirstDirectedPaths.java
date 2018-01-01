package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
/**
 * 使用广度优先搜索查找图中的路径
 * @author Administrator
 *
 */
public class BreadthFirstDirectedPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;//到达该顶点的最短路径已知吗？
	private int[] edgeTo;//到达该顶点的已知路径上的最后一个顶点（edgeTo[w]=v 也就是从起点s到w的路径上的最后一条已知边 v-w）
	private int[] distTo;//最短路径，s到v
	
	/**
	 * 初始化起点s和图G
	 * @param G
	 * @param s
	 */
	public BreadthFirstDirectedPaths(Digraph G, int s){
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++){
			distTo[v] = INFINITY;
			validateVertex(s);
			bfs(G, s);
		}
	}
	
	/**
	 * 初始化起点组sources和图G
	 * @param G
	 * @param sources
	 */
	public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources){
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++){
			distTo[v] = INFINITY;
		}
		validateVertices(sources);
		bfs(G, sources);
	}
	/**
	 * 
	 * 进行广度优先搜索路径
	 * @param G
	 * @param s
	 */
	private void bfs(Digraph G, int s){
		Queue<Integer> q = new Queue<Integer>();
		marked[s] = true;
		distTo[s] = 0;
		q.enqueue(s);
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w : G.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
	/**
	 * 进行广度优先搜索路径
	 * @param G
	 * @param sources
	 */
	private void bfs(Digraph G, Iterable<Integer> sources){
		Queue<Integer> q = new Queue<Integer>();
		for(int s : sources){
			marked[s] = true;
			distTo[s] = 0;
			q.enqueue(s);
		}
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w : G.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v){
		validateVertex(v);
		return marked[v];
	}
	
	/**
	 * 返回起点s到v的最短路径长度
	 * @param v
	 * @return
	 */
	public int distTo(int v){
		validateVertex(v);
		return distTo[v];
	}
	/**
	 * 返回最短路径路线
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		validateVertex(v);
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for(x=v; distTo[x]!=0; x = edgeTo[x]){
			path.push(x);
		}
		path.push(x);
		return path;
	}
	
	private void validateVertex(int v){
		int V = marked.length;
		if(v<0 || v>=V){
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
		}
	}
	
	private void validateVertices(Iterable<Integer> vertices){
		if(vertices == null){
			 throw new IllegalArgumentException("argument is null");
		}
		int V = marked.length;
		for(int v : vertices){
			if(v<0 || v>=V){
				throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
			}
		}
			
	}
}
