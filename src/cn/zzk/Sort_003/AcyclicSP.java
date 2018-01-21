package cn.zzk.Sort_003;
/**
 * 无环加权有向图的最短路径算法
 * @author Administrator
 *
 */
public class AcyclicSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	
	public AcyclicSP(EdgeWeightedDigraph G, int s){
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		
		validateVertext(s);
		
		for(int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		//Topolog
	}

	private void validateVertext(int s) {
		// TODO Auto-generated method stub
		
	}

}
