package cn.zzk.Sort_003;

import java.util.Stack;

/**
 * 无环加权有向图的最短路径算法
 * （按照 拓扑顺序 放松所有顶点）
 * 
 * 1.前提：必须是无环加权有向图
 * 2.首先获取到图顶点的拓扑顺序
 * 3.对这些顶点进行深度优先遍历，进行放松
 * 
 * 因为是按照拓扑顺序处理无环有向图的顶点，所以不可能再次遇见已经被放松过的顶点
 * 在拓扑排序后，函数会扫描整幅图并将每条边放松一次，在已知加权图是无环的情况下，
 * 他是找出最短路径的最好方法
 * 
 * @author Administrator
 *
 */
public class AcyclicSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	
	/**
	 * 初始化无环加权有向图
	 * @param G
	 * @param s
	 */
	public AcyclicSP(EdgeWeightedDigraph G, int s){
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		
		validateVertext(s);
		
		for(int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		Topological topological = new Topological(G);
		if(!topological.hasOrder()) {
			throw new IllegalArgumentException("Digraph is not acyclic.");
		}
		for(int v : topological.order()) {
			for(DirectedEdge e : G.adj(v)) {
				relax(e);
			}
		}
	}
	
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if(distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
	
	public double distTo(int v) {
		validateVertext(v);
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		validateVertext(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertext(v);
		if(!hasPathTo(v)){
			return null;
		}
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}

	private void validateVertext(int v) {
		int V = distTo.length;
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}

}
