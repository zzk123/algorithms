package cn.zzk.Sort_003;

import java.beans.Visibility;

import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的Prim算法（即时版本）
 * @author Administrator
 *
 */
public class PrimMST {
	
	private Edge[] edgeTo;			//距离树最近的边
	private double[] distTo;		//distTo[w] = edgeTo[w].weight()
	private boolean[] marked;		//如果v在树中则为true
	private IndexMinPQ<Double> pq;	//有效的横切边
	
	public PrimMST(EdgeWeightedGraph G){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		for(int v=0; v<G.V(); v++)
			//常数，保持类型的正常无穷大
			distTo[v] = Double.POSITIVE_INFINITY;
		pq = new IndexMinPQ<Double>(G.V());
		distTo[0] = 0.0;
		pq.insert(0, 0.0);
		while(!pq.isEmpty()){
			visit(G, pq.delMin());
		}
	}
	//将顶点添加入树中
	private void visit(EdgeWeightedGraph G, int v){
		marked[v] = true;
		//循环查找最小权重的与v相连边，添加树中
		for(Edge e : G.adj(v)){
			int w = e.other(v);
			if(marked[w])
				continue;
			if(e.weight() < distTo[w]){
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if(pq.contains(w)){
					pq.change(w, distTo[w]);
				}else{
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
	
	public Iterable<Edge> edges(){
		Queue<Edge> mst = new Queue<Edge>();
		for(int v = 0; v < edgeTo.length; v++){
			Edge e = edgeTo[v];
			if(e != null){
				mst.enqueue(e);
			}
		}
		return mst;
	}
	/**
	 * 返回权重总数
	 * @return
	 */
	public double weight(){
		double weight = 0.0;
		for(Edge e : edges()){
			weight += e.weight();
		}
		return weight;
	}
}
