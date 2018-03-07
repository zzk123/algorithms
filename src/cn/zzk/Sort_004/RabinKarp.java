package cn.zzk.Sort_004;

import java.math.BigInteger;
import java.util.Random;

/** Rabin-Karp指纹字符串查找算法
 */
public class RabinKarp {
	
	private String pat;			//模式字符串（仅拉斯维加斯算法需要）
	private long patHash;		//模式字符串的散列值
	private int M;				//模式字符串的长度
	private long Q;				//一个很大的素数
	private int R = 256;		//字母表的大小
	private long RM;			//R^(M-1)%Q
	
	public RabinKarp(String pat) {
		this.pat = pat;					//保存模式字符串（仅拉斯维加斯算法需要）
		this.M = pat.length();
		R = 256;
		Q = longRandomPrime();
		RM = 1;
		for(int i = 1; i <= M -1; i++) {//计算R^(M-1)%Q
			RM = (R * RM) % Q;			//用于减去第一个数字时的计算
		}
		patHash = hash(pat, M);
	}
	//Horner方法，用于除留余数计算散列值
	private long hash(String key, int M) {
		long h = 0;
		for(int j = 0; j < M; j++) {
			h = (R * h + key.charAt(j)) % Q;
		}
		return h;
	}
	//对于拉斯维加斯算法，检查模式与txt(i...i-M+1)的匹配
	public boolean check(String txt, int i) {
		  
		return true;
	}
	//在文本中查找相等的散列值
	public int search(String txt) {
		int n = txt.length();
		if(n < M)
			return n;
		long txtHash = hash(txt, M);
		//一开始就匹配成功
		if((patHash == txtHash)&& check(txt, 0))
			return 0;
		for(int i = M; i < n; i++) {
			txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			
			int offset = i - M + 1;
			if((patHash == txtHash) && check(txt, offset)) {
				return offset;
			}
		}
		return n;
	}
	
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
	
}
