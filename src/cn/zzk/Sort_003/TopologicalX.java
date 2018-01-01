package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Queue;

public class TopologicalX {
	
	private Queue<Integer> order;
	private int[] ranks;
	
	public TopologicalX(Digraph G){
		//顶点的入度
		int[] indegree = new int[G.V()];
		for(int v=0; v<G.V(); v++){
			indegree[v] = G.indegree(v);
		}
		
		ranks = new int[G.V()];
		order = new Queue<Integer>();
		int count = 0;
		
		Queue<Integer> queue = new Queue<Integer>();
		for(int v = 0 ; v < G.V(); v++){
			if(indegree[v] == 0){
				queue.enqueue(v);
			}
		}
		
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			order.enqueue(v);
			ranks[v] = count++;
			for(int w : G.adj(v)){
				indegree[w]--;
				if(indegree[w] == 0)
					queue.enqueue(w);
			}
		}
		
		if(count!=G.V()){
			order = null;
		}
		
		assert check(G);
	}
	
	public TopologicalX(EdgeWeightedDigraph G){
		int[] indegree = new int[G.V()];
		for(int v=0; v<G.V(); v++){
			indegree[v] = G.indegree(v);
		}
		
		ranks = new int[G.V()];
		order = new Queue<Integer>();
		int count = 0;
		
		Queue<Integer> queue = new Queue<Integer>();
		for(int v = 0; v<G.V(); v++){
			if(indegree[v]==0)
				queue.enqueue(v);
		}
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			order.enqueue(v);
			ranks[v] = count++;
			for(DirectedEdge e : G.adj(v)){
				int w = e.to();
				indegree[w]--;
				if(indegree[w]==0)
					queue.enqueue(w);
			}
		}
			
		if(count!=G.V()){
			order = null;
		}
			
			assert check(G);
	}
		
		
	public Iterable<Integer> order(){
		return order;
	}
		
	public boolean hasOrder(){
		return order!=null;
	}
	
	public int rank(int v){
		validateVertex(v);
		if(hasOrder())
			return ranks[v];
		else
			return -1;
	}
		
	private boolean check(Digraph G){
		if(hasOrder()){
			boolean[] found = new boolean[G.V()];
			for(int i = 0; i<G.V(); i++){
				found[rank(i)] = true;
			}
			for(int i=0; i<G.V(); i++){
				if(!found[i]){
					 System.err.println("No vertex with rank " + i);
	                 return false;
				}
			}
			
			for(int v=0; v<G.V(); v++){
				for(int w : G.adj(v)){
					if(rank(v) > rank(w)){
						 System.err.printf("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
                                 v, w, v, rank(v), w, rank(w));
						 return false;
					}
				}
			}
			
			int r = 0;
			for(int v: order()){
				if(rank(v) != r){
					System.err.println("order() and rank() inconsistent");
                    return false;
				}
				r++;
			}
		}
		return true;
	}
		
	private boolean check(EdgeWeightedDigraph G){
		if(hasOrder()){
			boolean[] found = new boolean[G.V()];
			for(int i=0; i<G.V(); i++){
				found[rank(i)] = true;
			}
			for(int i=0; i < G.V(); i++){
				if(!found[i]){
					System.err.println("No vertex with rank " + i);
                    return false;
				}
			}
			
			for(int v = 0; v < G.V(); v++){
				for(DirectedEdge e : G.adj(v)){
					int w = e.to();
					if(rank(v) > rank(w)){
						System.err.printf("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
                                v, w, v, rank(v), w, rank(w));
						return false;
					}
				}
			}
			
			int r = 0;
			for(int v : order()){
				if(rank(v) != r){
					System.err.println("order() and rank() inconsistent");
                    return false;
				}
				r++;
			}
		}
		return true;
	}
	
	private void validateVertex(int v){
		int V = ranks.length;
		if(v < 0 || v >= V){
			 throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
		}
	}
}
