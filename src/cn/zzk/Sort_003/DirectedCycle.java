package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.Stack;

/**
 * 寻找有向环
 * 该类为标准的递归dfs（）方法添加了一个布尔类型的数组onStack[]来保存递归调用期间栈上的所有顶点。
 * 当他找到一条边v->w且w在栈中时，它就找到了一个有向环，环上所有的顶点可以通过edgeTo[]中的链接得到
 * @author Administrator
 *
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;	//有向环中的所有顶点
	private boolean[] onStack;		//递归调用的栈上的所有顶点
	
	public DirectedCycle(Digraph G){
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v=0; v<G.V(); v++)
			if(!marked[v])
				dfs(G, v);
	}
	
	private void dfs(Digraph G, int v){
		//存储起始点V
		onStack[v] = true;
		marked[v] = true;
		for(int w : G.adj((v))){
			 //判断是否已经找到有向环
			if(this.hasCycle()){
				return;
			}else if(!marked[w]){
				//未访问的顶点继续访问
				edgeTo[w] = v;
				dfs(G, w);
			}else if(onStack[w]){
				//如果下一个顶点已经访问过并且存在栈中（也就是下个顶点与起始点相同），表示存在有向环
				cycle = new Stack<Integer>();
				for(int x = v; x!=w; x = edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
			onStack[v] = false;
		}
	}
	
	//判断是否在有向环
	public boolean hasCycle(){
		return cycle!=null;
	}
	//存储有向环路径
	public Iterable<Integer> cycle(){
		return cycle;
	}
}
