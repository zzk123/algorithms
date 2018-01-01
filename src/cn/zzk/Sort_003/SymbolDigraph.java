package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
/**
 * 符号图的数据类型（有向图）
 * @author Administrator
 *
 */
public class SymbolDigraph {
	
	private ST<String, Integer> st;		//映射顶点名和索引(通过顶点名获取索引)
	private String[] keys;				//数组用来映射索引和顶点名（通过索引获取顶点名）
	private Digraph graph;				//使用索引表示顶点的图
	
	public SymbolDigraph(String filename, String delimiter){
		st = new ST<String, Integer>();
		In in = new In(filename);
		while(in.hasNextLine()){
			String[] a = in.readLine().split(delimiter);
			for(int i=0; i<a.length; i++){
				if(!st.contains(a[i])){
					st.put(a[i], st.size());
				}
			}
		}
			
		keys = new String[st.size()];
		for(String name : st.keys()){
			keys[st.get(name)] = name;
		}
		
		graph = new Digraph(st.size());
		in = new In(filename);
		while(in.hasNextLine()){
			String[] a = in.readLine().split(delimiter);
			int v = st.get(a[0]);
			for(int i=1; i<a.length; i++){
				int w = st.get(a[0]);
				graph.addEdge(v, w);
			}
		}
	}
	
	public boolean contains(String s){
		return st.contains(s);
	}
	
	public int index(String s){
		return st.get(s);
	}
	
	public int  indexOf(String s){
		return st.get(s);
	}
	
	public String name(int v){
		validateVertex(v);
		return keys[v];
	}
	
	public String nameOf(int v){
		validateVertex(v);
		return keys[v];
	}
	
	public Digraph G(){
		return graph;
	}
	
	private void validateVertex(int v){
		 int V = graph.V();
	        if (v < 0 || v >= V)
	            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}
}
