package cn.test.test1.chart;

import cn.test.test1.Bag;
import edu.princeton.cs.algs4.In;
/**
 * 邻接表的数据结构
 * @author zzk
 *
 */
@SuppressWarnings("unchecked")
public class Graph {
	//顶点数目
	private final int V;
	//边的数目
	private int E;
	//邻接表
	private Bag<Integer>[] adj;
	//创建邻接表，将所有的链表初始化为空
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	//读取一个V并将图初始化
	public Graph(In in) {
		this(in.readInt());		
		int E = in.readInt();			//读取E
		for(int i = 0; i < E; i++) {	
			int v = in.readInt();		//读取一个顶点
			int w = in.readInt();		//读取另外一个顶点
			addEdge(v, w);				//添加一条连接它们的边
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);			//将w添加到v的链表中
		adj[w].add(v);			//将v添加到
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
}
