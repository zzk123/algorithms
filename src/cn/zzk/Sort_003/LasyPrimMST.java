package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的Prim算法的延时实现
 * 
 * @author Administrator
 *
 */
public class LasyPrimMST {
	
	/**
	 * 最小生成树的权重
	 */
	private double weight;
	
	/**
	 * 最小生成树的顶点
	 */
	private boolean[] marked;
	/**
	 * 最小生成树的边
	 */
	private Queue<Edge> mst;
	/**
	 * 横切边（包括失效的边）
	 */
	private MinPQ<Edge> pq;
	
	public LasyPrimMST(EdgeWeightedGraph G){
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
		
		visit(G, 0);
		while(!pq.isEmpty()){
			Edge e = pq.delMin();
			
			int v = e.either(), w = e.other(v);
			if(marked[v] && marked[w])
				continue;
			mst.enqueue(e);
			weight += e.weight();
			if(!marked[v])
				visit(G, v);
			if(!marked[w])
				visit(G, w);
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v){
		marked[v] = true;
		for(Edge e : G.adj(v)){
			if(!marked[e.other(v)]){
				pq.insert(e);
			}
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		return weight;
	}
}
