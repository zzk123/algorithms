package cn.zzk.Sort_003;


import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

/**
 * 基于队列的Bellman-Ford算法
 * 解决最短路径的问题（负权重环的检测）
 * 注：在存在负权重环的情况下，最短路径问题是没有意义的
 * @author Administrator
 *
 */
public class BellmanFordSP {
	private double[] distTo;				//从起点到某个顶点的路径长度	
	private DirectedEdge[] edgeTo;			//从起点到某个顶点的最后一条边
	private boolean[] onQueue;				//该顶点是否存在于队列中
	private Queue<Integer> queue;			//正在被放松的顶点
	private int cost;						//relax()的调用次数
	private Iterable<DirectedEdge> cycle;	//edgeTo[]中是否有负权重环
	/**
	 * 初始化加权有向图
	 * 首先将起点s加入队列中，然后进入一个循环，其中每次都从队列中取出一个顶点，并将其放松
	 * @param G
	 * @param s
	 */
	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo  = new double[G.V()];
		edgeTo  = new DirectedEdge[G.V()];
		onQueue = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		queue = new Queue<Integer>();
		queue.enqueue(s);
		onQueue[s] = true;
		while (!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.dequeue();
			onQueue[v] = false;
			relax(G, v);
		}
	}
	/**
	 * 对每个顶点进行放松，并且记录放松轮数，判断是否存在负权重环，找到就结果运行。
	 * @param G
	 * @param v
	 */
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(!onQueue[w]) {
					queue.enqueue(w);
					onQueue[w] = true;
				}
			}
			if(cost++ % G.V() == 0) {
				findNegativeCycle();
				if(hasNegativeCycle())
					return;
			}
		}
	}
	/**
	 * 是否含有负权重环
	 * @return
	 */
	public boolean hasNegativeCycle() {
		return cycle != null;
	}
	/**
	 * 得到负权重环
	 * @return
	 */
	public Iterable<DirectedEdge> negativeCycle(){
		return cycle;
	}
	/**
	 * 判断是否存在负权重环
	 */
	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
		for(int v = 0; v < V; v++) {
			if(edgeTo[v] != null) {
				spt.addEdge(edgeTo[v]);
			}
		}
		
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
		cycle = finder.cycle();
	}
	
	public double distTo(int v) {
		validateVertex(v);
		if(hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertex(v);
		if(hasNegativeCycle()) 
			throw new UnsupportedOperationException("Negative cost cycle exists");
		if(!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
	
	private boolean check(EdgeWeightedDigraph G, int s) {
		if(hasNegativeCycle()) {
			double weight = 0.0;
			for(DirectedEdge e : negativeCycle()) {
				weight += e.weight();
			}
			if(weight >= 0.0){
				 System.err.println("error: weight of negative cycle = " + weight);
	             return false;
			}
		}else {
			if(distTo[s] != 0.0 || edgeTo[s] != null) {
				System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
                return false;
			}
			for(int v = 0; v < G.V(); v++) {
				if (v == s) continue;
                if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
                }
			}
			
			for(int v = 0; v < G.V(); v++) {
				for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (distTo[v] + e.weight() < distTo[w]) {
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    }
                }
			}

            // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
            for (int w = 0; w < G.V(); w++) {
                if (edgeTo[w] == null) continue;
                DirectedEdge e = edgeTo[w];
                int v = e.from();
                if (w != e.to()) return false;
                if (distTo[v] + e.weight() != distTo[w]) {
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
                }
            }
        }

        StdOut.println("Satisfies optimality conditions");
        StdOut.println();
        return true;
	}
	private void validateVertex(int v) {
		// TODO Auto-generated method stub
		int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}
	
	
}
