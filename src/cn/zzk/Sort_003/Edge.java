package cn.zzk.Sort_003;

/**
 * 带权重的边的数据类型
 * @author Administrator
 *
 */
public class Edge implements Comparable<Edge>{

	/**
	 * 顶点之一
	 */
	private final int v;
	/**
	 * 另一个顶点
	 */
	private final int w;
	/**
	 * 边的权重
	 */
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight(){
		return weight;
	}
	
	public int either(){
		return v;
	}
	/**
	 * 根据已知顶点vertex获取另一个顶点
	 * @param vertex
	 * @return
	 */
	public int other(int vertex){
		if(vertex == v)
			return w;
		else if(vertex == w)
			return v;
		else 
			throw new RuntimeException("Inconsistent edge");
	}

	public int compareTo(Edge that) {
		if(this.weight() < that.weight())
			return -1;
		else if(this.weight() > that.weight())
			return +1;
		else 
			return 0;
	}
	
	public String toString(){
		return String.format("%d-%d %.2f", v, w, weight);
	}
}
