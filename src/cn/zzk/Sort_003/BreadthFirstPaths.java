package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 使用广度优先搜索查找图中的路径
 * @author Administrator
 *
 */
public class BreadthFirstPaths {
	//到达该顶点的最短路径已知吗
	private boolean[] marked;
	//到达该顶点的已知路径上的最后一个顶点
	private int[] edgeTo;
	//起点
	private final int s;
	
	public BreadthFirstPaths(Graph G,int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s){
		Queue<Integer> queue = new Queue<Integer>();
		//标记起点
		marked[s] = true;
		//将它加入队列
		queue.enqueue(s);
		while(!queue.isEmpty()){
			//从队列中删去下一个顶点
			int v = queue.dequeue();
			for(int w : G.adj(v)){
				//对于每个未被标记的相邻顶点
				if(!marked[w]){
					//保存最短路径的最后一条边
					edgeTo[w] = v;
					//标记它，因为最短路径已经添加
					marked[w] = true;
					//并将它添加到队列中
					queue.enqueue(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	/**
	 * 返回起始点s到v的路径
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x!=s; x = edgeTo[x])
			path.push(x);
		return path;
	}
}
