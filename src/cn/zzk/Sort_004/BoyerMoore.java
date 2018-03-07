package cn.zzk.Sort_004;
/**
 * Boyer-Moore字符串匹配算法（启发式地处理不匹配算法）
 * 
 * @author Administrator
 *
 */
public class BoyerMoore {
	
	private final int R;
	private int[] right;
	
	private char[] pattern;
	private String pat;
	
	public BoyerMoore(String pat) {
		
		this.R = 256;
		this.pat = pat;
		
		right = new int[R];
		//不包含在模式字符串中的字符的值为-1
		for(int c = 0; c < R; c++) {
			right[c] = -1;							
		}
		//包含在模式字符串中的字符的值
		for(int j = 0; j < pat.length(); j++) {
			//它在其中出现的最右位置
			right[pat.charAt(j)] = j;
		}
	}
	
	public BoyerMoore(char[] pattern, int R) {
		this.R = R;
		this.pattern = new char[pattern.length];
		for(int j = 0; j < pattern.length; j++) {
			this.pattern[j] = pattern[j];
		}
		right = new int[R];
		for(int c = 0; c < R; c++) {
			right[c] = -1;
		}
		for(int j = 0; j < pattern.length; j++) {
			right[pattern[j]] = j;
		}
	}
	
	public int search(String txt) {
		//在txt中查找模式字符串
		int m = pat.length();
		int n = txt.length();
		int skip;
		for(int i = 0; i <= n - m; i += skip) {
			//模式字符串和文本在位置i匹配吗？
			skip = 0;
			for(int j = m-1; j >= 0; j--) {
				if(pat.charAt(j) != txt.charAt(i+j)) {
					skip = Math.max(1, j - right[txt.charAt(i+j)]);
					break;
				}
			}
			//找到匹配
			if(skip == 0)
				return i;
		}
		//未找到匹配
		return n; 
	}
	
	public int search(char[] text) {
		int m= pattern.length;
		int n = text.length;
		int skip;
		for(int i = 0; i <= n - m; i += skip) {
			skip = 0;
			for(int j = m-1; j >= 0; j--) {
				if(pattern[j] != text[i+j]) {
					skip = Math.max(1, j - right[text[i+j]]);
					break;
				}
			}
			if(skip == 0)
				return i;
		}
		return n;
	}
}
