package cn.zzk.Sort_003;
/**
 * 使用深度优先搜索处理图实例
 * 
 * 输入图顶点，判断是否存在自环或者平行边
 * 
 * G是无环图吗？（假设不存在自环或者平行边）
 * 
 * @author Administrator
 *
 */
public class Cycle {
	
	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(Graph G){
		marked = new boolean[G.V()];
		for(int s = 0; s<G.V(); s++){
			if(!marked[s])
				dfs(G,s,s);
		}
	}
	
	private void dfs(Graph G, int v, int u){
		marked[v] = true;
		for(int w : G.adj(v)){
			if(!marked[w]){
				dfs(G, w, v);
			}else if(w!=u){
				hasCycle = true;
			}
		}
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
}
