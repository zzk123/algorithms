package cn.zzk.Sort_003;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * 有向图的数据类型
 * @author Administrator
 *
 */
public class Digraph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	//顶点的入度
	private int[] indegree;
	
	public Digraph(int V){
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v<V; v++){
			adj[v] = new Bag<Integer>();
		}
	}
	
	public Digraph(In in){
		try {
			this.V = in.readInt();
			if(V<0)
				throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
			indegree = new int[V];
			adj = (Bag<Integer>[]) new Bag[V];
			for(int v=0; v<V; v++){
				adj[v] = new Bag<Integer>();
			}
			int E = in.readInt();
			if(E < 0)
				throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
			for(int i=0; i<E; i++){
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
		}
	}
	
	public Digraph(Digraph G){
		this(G.V());
		this.E = G.E();
		for(int v= 0; v<V; v++){
			this.indegree[v] = G.indegree(v);
		}
		for(int v = 0; v<G.V(); v++){
			Stack<Integer> reverse = new Stack<Integer>();
			for( int w : G.adj[v]){
				reverse.push(w);
			}
			for(int w : reverse){
				adj[v].add(w);
			}
		}
	}
	
	public int indegree(int v){
		validateVertex(v);
		return indegree[v];
	}

	private void validateVertex(int v) {
		 if (v < 0 || v >= V)
	            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}

	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	/**
	 * 只有v指向w
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w){
		validateVertex(v);
        validateVertex(w);
		adj[v].add(w);
		indegree[w]++;
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		validateVertex(v);
		return adj[v];
	}
	
	public int outdegree(int v){
		validateVertex(v);
		return adj[v].size();
	}
	
	/**
	 * 反向图
	 * @return
	 */
	public Digraph reverse(){
		Digraph R = new Digraph(V);
		for(int v=0; v<V; v++){
			for(int w : adj(v)){
				R.addEdge(w, v);
			}
		}
		return R;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, "+ E + " edges ");
		for(int v=0; v<V; v++){
			s.append(String.format("%d:",v));
			for(int w : adj[v]){
				s.append(String.format("%d ", w));
			}
		}
		return s.toString();
	}
}
