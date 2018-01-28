package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.Stack;

/**
 * 无环加权有向图的最长路径算法
 * @author Administrator
 *
 */
public class AcyclicLP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	
	public AcyclicLP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		
		validateVertex(s);
		
		for( int v = 0; v < G.V(); v++) {
			distTo[v] = Double.NEGATIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		//获取顶点拓扑顺序，对每一条边进行relax
		Topological topological = new Topological(G);
		if(!topological.hasOrder()) {
			throw new  IllegalArgumentException("Digraph is not acyclic.");
		}
		for(int v : topological.order()){
			for(DirectedEdge e : G.adj(v)) {
				relax(e);
			}
		}
	}
	/**
	 * 放松每一条边，获取最长路径的边
	 * @param e
	 */
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if(distTo[w] < distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		return distTo[v] > Double.NEGATIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		if(!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null ; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
	
	private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


}
