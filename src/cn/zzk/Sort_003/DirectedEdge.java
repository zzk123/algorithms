package cn.zzk.Sort_003;

import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的有向边的数据结构
 * @author Administrator
 *
 */
public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight){
		if(v<0)
			throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if(w<0)
			throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if(Double.isNaN(weight))
			throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	//起始点
	public int from(){
		return v;
	}
	//指向点
	public int to(){
		return w;
	}
	
	public double weight(){
		return weight;
	}
	
	public String toString(){
		return v + "->" + w + " " + String .format("%5.2f", weight);
	}
	
	public static void main(String[] args){
		DirectedEdge e = new DirectedEdge(12, 34, 5.67);
		StdOut.print(e);
	}
}
