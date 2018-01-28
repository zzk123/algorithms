package cn.zzk.Sort_003;

import java.util.Stack;

/**
 * 判断加权有向图是否存在环的数据结构
 * @author Administrator
 *
 */
public class EdgeWeightedDirectedCycle {
	private boolean[] marked;				//marked[v] v是否已经扫描了
	private DirectedEdge[] edgeTo;			//edgeTo[v] v的上一条边长度
	private boolean[] onStack;				//onStack[v] 是栈中的顶点吗
	private Stack<DirectedEdge> cycle;		//有向环
	
	/**
	 * 初始化加权有向图，寻找是否存在有向环
	 * @param G
	 */
	public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v])
				dfs(G, v);
		}
		assert check();
	}
	
	/**
	 * 深度优先遍历有向图
	 * @param G
	 * @param v
	 */
	private void dfs(EdgeWeightedDigraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			//找到有向环，返回
			if(cycle != null) {
				return;
			}
			//找到新的节点，继续递归循环
			else if(!marked[w]) {
				edgeTo[w] = e;
				dfs(G, w);
			}
			//栈中已存在顶点，证明存在有向环
			else if(onStack[w]) {
				cycle = new Stack<DirectedEdge>();
				
				DirectedEdge f = e;
				while(f.from() != w) {
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				cycle.push(f);
				
				return;
			}
		}
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> cycle() {
		return cycle;
	}
	
	private boolean check() {
		if(hasCycle()) {
			DirectedEdge first = null, last = null;
			for(DirectedEdge e : cycle()) {
				if(first == null)
					first = e;
				if(last != null) {
					if(last.to() != e.from()) {
						System.err.printf("cycle edges %s and %s not incident\n", last, e);
						return false;
					}
				}
				last = e;
			}
			
			if(last.to() != first.from()) {
				System.err.printf("cycle edges %s and %s not incident\n", last, first);
				return false;
			}
		}
		return true;
	}
}
