package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.In;

/**
 *  Grap数据类型(邻接表)
 * @author Administrator
 *
 */
public class Graph {
	/**
	 * 顶点数量
	 */
	private final int V;
	/**
	 * 边的数目
	 */
	private int E;
	/**
	 * 邻接的表
	 */
	private Bag<Integer>[] adj;
	
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];   //创建邻接表
		for(int v=0; v<V; v++)				 //将所有链表初始化为空
			adj[v] = new Bag<Integer>();
	}
	
	public Graph(In in){
		this(in.readInt());                //读取V并将图初始化
		int E = in.readInt();			   //读取E
		for(int i=0; i<E; i++){
			//添加一条边
			int v = in.readInt();			//读取一个顶点       
			int w = in.readInt();			//读取另一个顶点
			addEdge(v,w);					//添加一条连接他们的边
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);						//将w添加到v的链表中
		adj[w].add(v);						//将v添加到w的链表中
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public String toString(){
		String s = V + " vertices, "+ E + "edges\n";
		for(int v=0; v<V; v++){
			s += v + ":";
			for(int w:this.adj(v))
				s+=w+" ";
		}
		return s;
	}
}
