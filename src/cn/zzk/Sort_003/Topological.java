package cn.zzk.Sort_003;

/**
 * 拓扑排序
 * @author Administrator
 *
 */
public class Topological {
	
	private Iterable<Integer> order;   //顶点的拓扑排序
	private int[] rank;					//rank[v] = 点v在拓扑序中的位置
	
	//初始化是否是有向无环图，返回所有顶点的逆后序排序
	public Topological(Digraph G){
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if(!cyclefinder.hasCycle()){
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
			rank = new int[G.V()];
			int i = 0;
			for(int v : order)
				rank[v] = i++;
		}
	}
	
	public Topological(EdgeWeightedDigraph G) {
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
		if(!finder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	public boolean hasOrder() {
		return order != null;
	}
	
	public boolean isDAG(){
		return hasOrder();
	}
	
	public int rank(int v) {
		if(hasOrder())
			return rank[v];
		else
			return -1;
	}
	
	//使用DepthFirstOrder类和DirectedCycle类来返回一幅有向无环图的拓扑排序
	public static void main(String[] args) {
		/*String filename = args[0];
		String separator = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, separator);
		Topological top = new Topological(sg.G());
		
		for(int v : top.order()){
			StdOut.println(sg.name(v));
		}*/
	}
}
