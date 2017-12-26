package cn.zzk.Sort_003;
/**
 * 
 * 使用深度优先搜索处理图实例
 * 
 * G是二分图吗？能够用两种颜色将图的所有顶点着色，使得任意一条边的两个端点的颜色都不相同
 * @author Administrator
 *
 */
public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable = true;
	public TwoColor(Graph G){
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s=0; s<G.V(); s++){
			if(!marked[s])
				dfs(G, s);
		}
	}
	
	private void dfs(Graph G, int v){
		marked[v] = true;
		for(int w : G.adj(v)){
			if(!marked[w]){
				color[w] = !color[v];
				dfs(G, w);
			}else if(color[w] == color[v]){
				isTwoColorable = false;
			}
		}
	}
	
	public boolean isBipartite(){
		return isTwoColorable;
	}
}
