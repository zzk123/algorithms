package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图
 * 1.顶点名为字符串
 * 2.用指定的分隔符来隔开顶点名（允许顶点名含有空格）
 * 3.每一行都表示一组边的集合，每一条边都连接着这一行的第一个名称表示的顶点和其他名称所表示的顶点
 * 4.顶点总数V和边的总数E都是隐式定义的
 * 
 * @author Administrator
 *
 * 实现：
 * 	一个符号表st，键的类型为String（顶点名），值的类型为int（索引）
 *  一个数组keys[],用作反向索引，保存每个顶点索引所对应的顶点名
 *  一个Graph对象G,使用索引来引用图中的顶点
 */
public class SysmbolGraph {
	
	/**
	 * 符号名 -> 索引
	 */
	private ST<String, Integer> st;
	/**
	 * 索引 -> 符号名
	 */
	private String[] keys;
	/**
	 * 图
	 */
	private Graph G;
	
	public SysmbolGraph(String stream, String sp){
		st = new ST<String, Integer>();
		//第一遍
		In in = new In(stream);
		//构造索引
		while(in.hasNextLine()){
			String[] a = in.readLine().split(sp);
			//为每个不同的字符串关联一个索引
			for(int i=0; i<a.length; i++){
				if(!st.contains(a[i])){
					st.put(a[i], st.size());
				}
			}
		}
		
		//用来获顶点名的反向索引是一个数组
		keys = new String[st.size()];
		for(String name : st.keys()){
			keys[st.get(name)] = name;
		}
		
		//第二遍
		//构造图
		G = new Graph(st.size());
		in = new In(stream);
		while(in.hasNextLine()){
			//将每一行的第一个顶点和该行的其他顶点相连
			String[] a = in.readLine().split(sp);
			int v = st.get(a[0]);
			for(int i = 1; i < a.length; i++){
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	
	public boolean contains(String s){
		return st.contains(s);
	}
	
	public int index(String s){
		return st.get(s);
	}
	
	public String name(int v){
		return keys[v];
	}
	
	public Graph G(){
		return G;
	}
}
