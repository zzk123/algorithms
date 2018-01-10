package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
	
	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for(int v = 0; v < V; v++){
			adj[v] = new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		if(E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for(int i=0; i<E; i++){
			int v = in.readInt();
			int w = in.readInt();
			validateVertex(v);
			validateVertex(w);
			double weight = in.readDouble();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(Edge e){
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	//加权无向图中的所有边
	public Iterable<Edge> edges(){
		Bag<Edge> b = new Bag<Edge>();
		for(int v = 0; v < V; v++){
			for(Edge e : adj[v]){
				if(e.other(v) > v)
					b.add(e);
			}
		}
		return b;
	}
	private void validateVertex(int v){
		if( v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E );
		for(int v = 0; v < V; v++){
			s.append(v + ":");
			for(Edge e : adj[v]){
				s.append(e + " ");
			}
		}
		return s.toString();
	}
}
