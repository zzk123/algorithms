package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.StdOut;

/**
 * 拓扑排序
 * @author Administrator
 *
 */
public class Topological {
	
	private Iterable<Integer> order;//顶点的拓扑排序
	
	//初始化是否是有向无环图，返回所有顶点的逆后序排序
	public Topological(Digraph G){
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if(!cyclefinder.hasCycle()){
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	public boolean isDAG(){
		return order!=null;
	}
	//使用DepthFirstOrder类和DirectedCycle类来返回一幅有向无环图的拓扑排序
	public static void main(String[] args) {
		String filename = args[0];
		String separator = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, separator);
		Topological top = new Topological(sg.G());
		
		for(int v : top.order()){
			StdOut.println(sg.name(v));
		}
	}
}
