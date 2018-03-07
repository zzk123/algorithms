package cn.zzk.Sort_004;

import edu.princeton.cs.algs4.StdOut;

/**
 * Knuth-Morris-Pratt字符串查找算法
 * @author Administrator
 *
 */
public class KMP {
	private final int R;
	private char[] pattern;
	private String pat;
	private int[][] dfa;
	
	//由模式字符串构造DFA
	public KMP(String pat) {
		R = 256;
		this.pat = pat;
		int M = pat.length();
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for(int X = 0, j = 1; j < M; j++) {
			for(int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][X];		//复制匹配失败情况下的值
			}	
			dfa[pat.charAt(j)][j] = j + 1;	//设置匹配成功情况下的值
			X = dfa[pat.charAt(j)][X];		//更新重启状态
		}
	}
	
	public KMP(char[] pattern, int R) {
		this.R = R;
		this.pattern = new char[pattern.length];
		for(int j = 0; j < pattern.length; j++) {
			this.pattern[j] = pattern[j];
		}
		int M = pattern.length;
		dfa = new int[R][M];
		dfa[pattern[0]][0] = 1;
		for(int x = 0, j = 1; j < M; j++) {
			for(int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][x];		
			}
			dfa[pattern[j]][j] = j + 1;
			x = dfa[pattern[j]][x];
		}
	}
	public int search(String txt) {
		int i, j, N = txt.length(), M = pat.length();
		for(i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if(j == M)
			return i - M;				//找到匹配（到达模式字符串的结尾）
		else
			return M;					//未找到匹配（到达文本字符串的结尾）
	}
	
	public int search(char[] text) {
		int m = pattern.length;
		int n = text.length;
		int i, j;
		for(i = 0, j = 0; i < n && j < m; i++) {
			j = dfa[text[i]][j];
		}
		if(j == m)
			return i - m;
		return n;
	}
	
	public static void main(String[] args) {
		String pat = args[0];
		String txt = args[1];
		char[] pattern = pat.toCharArray();
		char[] text = txt.toCharArray();
		
		KMP kmp1 = new KMP(pat);
		int offset1 = kmp1.search(text);
		
		KMP kmp2 = new KMP(pattern, 256);
		int offset2 = kmp2.search(text);
		
		StdOut.println("text:		"+text);
		
		StdOut.print("pattern:  ");
		for(int i = 0; i < offset1; i++) {
			StdOut.print("		");
		}
		StdOut.println(pat);
	}
	
}
