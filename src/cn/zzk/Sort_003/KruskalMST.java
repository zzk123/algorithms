package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.UF;

/**
 * 最小生成树的Kruskal算法
 * @author Administrator
 *
 */
public class KruskalMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;
	private double weight;
	private Queue<Edge> mst = new Queue<Edge>();
	
	public KruskalMST(EdgeWeightedGraph G){
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e : G.edges()){
			pq.insert(e);
		}
		UF uf = new UF(G.V());
		while(!pq.isEmpty() && mst.size() < G.V() - 1){
			//从pq得到权重最小的边和它的顶点
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			//忽略失效的边
			if(!uf.connected(v, w)){
				uf.union(v, w);		//合并分量
				mst.enqueue(e);		//添加到最小树 中
				weight += e.weight();
			}
		}
		assert check(G);
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		return weight;
	}
	
	private boolean check(EdgeWeightedGraph G){
		double total = 0.0 ;
		for(Edge e : edges()){
			total += e.weight();
		}
		if(Math.abs(total - weight()) > FLOATING_POINT_EPSILON){
			 System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
	         return false;
		}
		
		UF uf = new UF(G.V());
		for(Edge e : edges()){
			int v = e.either(), w = e.other(v);
			if(uf.connected(v, w)){
				 System.err.println("Not a forest");
	             return false;
			}
			uf.union(v, w);
		}
		
		for(Edge e : G.edges()){
			int v = e.either(), w = e.other(v);
			if(!uf.connected(v, w)){
				System.err.println("Not a spanning forest");
                return false;
			}
		}
		
		for(Edge e : edges()){
			uf = new UF(G.V());
			for(Edge f : mst){
				int x = f.either(), y = f.other(x);
				if(f != e)
					uf.union(x, y);
			}
		
			for(Edge f : G.edges()){
				int x = f.either(), y = f.other(x);
				if(!uf.connected(x, y)){
					if (f.weight() < e.weight()) {
	                    System.err.println("Edge " + f + " violates cut optimality conditions");
	                    return false;
	                	}
				}
			}
		}
		return true;
	}
}
