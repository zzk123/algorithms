package cn.zzk.Sort_003;

/**
 * 顶点可达性
 * @author Administrator
 *
 */
public class TransitiveClosure {
	
	private DirectedDFS[] all;
	//预设构造函数
	TransitiveClosure(Digraph G){
		all = new DirectedDFS[G.V()];
		for(int v = 0; v < G.V(); v++){
			all[v] = new DirectedDFS(G, v);
		}
	}
	//w是从v可达吗
	boolean reachable(int v, int w){
		return all[v].marked(w);
	}
}
