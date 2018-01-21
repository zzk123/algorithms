package cn.zzk.Sort_003;

import java.util.Stack;

/**
 * 最短路径的Dijkstra算法
 * @author Administrator
 *
 */
public class DijkstraSP {
	//保存有向边
	private DirectedEdge[] edgeTo;
	//顶点索引数组，保存路径的长度
	private double[] distTo;
	//最小优先队列
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s){
		for(DirectedEdge e : G.edges()){
			if(e.weight() < 0){
				throw new IllegalArgumentException("edge " + e + " has negative weight");
			}
		}
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		
		validateVertex(s);
		
		pq = new IndexMinPQ<Double>(G.V());
		for(int v = 0 ; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(s, distTo[s]);
		while(!pq.isEmpty()){
			int v = pq.delMin();
			for(DirectedEdge e : G.adj(v)){
				relax(e);
			}
		}
		
		assert check(G, s);
	}
	//边的松弛，判断起点不同路线的长度
	private void relax(DirectedEdge e){
		int v = e.from(), w = e.to();
		if(distTo[w] > distTo[v] + e.weight()){
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if(pq.contains(w)){
				pq.decreaseKey(w, distTo[w]);
			}else{
				pq.insert(w, distTo[w]);
			}
		}
	}
	/**
	 * 从起点s到v的距离，如果不存在则路径就无穷大
	 * @param v
	 * @return
	 */
	public double distTo(int v){
		validateVertex(v);
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	/**
	 * 从起点s到v的路径，不存在就为null
	 * @param v
	 * @return
	 */
	public Iterable<DirectedEdge> pathTo(int v){
		validateVertex(v);
		if(!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e !=null; e = edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
	
	private boolean check(EdgeWeightedDigraph G, int s){
		for(DirectedEdge e : G.edges()){
			if(e.weight() < 0){
				System.err.println("negative edge weight detected");
                return false;
			}
		}
		
		if(distTo[s] != 0.0 || edgeTo[s] != null){
			 System.err.println("distTo[s] and edgeTo[s] inconsistent");
	         return false;
		}
		
		for(int v = 0; v < G.V(); v++){
			if(v==s)
				continue;
			if(edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY){
				System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
			}
		}
		
		for(int v = 0; v < G.V(); v++){
			for(DirectedEdge e : G.adj(v)){
				int w = e.to();
				if(distTo[v] + e.weight() < distTo[w]){
					System.err.println("edge " + e + " not relaxed");
                    return false;
				}
			}
		}
		
		for(int w = 0; w < G.V(); w++){
			if(edgeTo[w] == null)
				continue;
			DirectedEdge e = edgeTo[w];
			int v = e.from();
			if(w != e.to())
				return false;
			if(distTo[v] + e.weight() != distTo[w]){
				System.err.println("edge " + e + " on shortest path not tight");
                return false;
			}
		}
		return true;
	}
	
	private void validateVertex(int v){
		int V = distTo.length;
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}
}
