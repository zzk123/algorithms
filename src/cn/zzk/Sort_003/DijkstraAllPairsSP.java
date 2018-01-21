package cn.zzk.Sort_003;
/**
 * 给定两点的最短路径
 * 给定一幅加权有向图以及一个起点s和一个终点t，找到从s到t的最短路径
 * @author Administrator
 *
 */
public class DijkstraAllPairsSP {
	
	private DijkstraSP[] all;
	
	public DijkstraAllPairsSP(EdgeWeightedDigraph G){
		all = new DijkstraSP[G.V()];
		for(int v = 0; v < G.V(); v++){
			all[v] = new DijkstraSP(G, v);
		}
	}
	
	public Iterable<DirectedEdge> path(int s, int t){
		validateVertex(s);
		validateVertex(t);
		return all[s].pathTo(t);
	}
	
	public boolean hasPath(int s, int t){
		 validateVertex(s);
	     validateVertex(t);
	     return dist(s, t) < Double.POSITIVE_INFINITY;
	}
	
	public double dist(int s, int t){
		validateVertex(s);
        validateVertex(t);
        return all[s].distTo(t);
	}
	
	private void validateVertex(int v){
		int V = all.length;
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}
}
