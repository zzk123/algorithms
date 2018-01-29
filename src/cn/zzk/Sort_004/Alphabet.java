package cn.zzk.Sort_004;
/**
 *	字母表
 * @author Administrator
 *
 */
public class Alphabet {
	
	private char[] alphabet;
	private int[] inverse;
	private final int R;
	/**
	 * 根据alpha中的字符创建一张新的字母表
	 * @param alpha
	 */
	public Alphabet(String alpha) {
		//检查字符串没有重复字符
		boolean[] unicode = new boolean[Character.MAX_VALUE];
		for(int i = 0; i < alpha.length(); i++) {
			char c = alpha.charAt(i);
			if(unicode[c])
				throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
			unicode[c] = true;
		}
		//字符数组
		alphabet = alpha.toCharArray();
		R = alpha.length();
		inverse = new int[Character.MAX_VALUE];
		for(int i = 0; i < inverse.length; i++)
			inverse[i] = -1;
		
		for(int c = 0; c < R; c++) {
			inverse[alphabet[c]] = c;
		}
	}
	
	private Alphabet(int radix) {
		this.R = radix;
		alphabet = new char[R];
		inverse = new int[R];
		
		for(int i = 0; i < R; i++) {
			alphabet[i] = (char) i ;
		}
		
		for(int i = 0; i < R; i++) {
			inverse[i] = i;
		}
	}
	
	public Alphabet() {
		this(256);
	}
	/**
	 * c在字母表中吗
	 * @param c
	 * @return
	 */
	public boolean contains(char c) {
		return inverse[c] != -1;
	}
	/**
	 * 基数（字母表中的字符数量）
	 * @return
	 */
	public int R() {
		return R;
	}
	
	public int radix() {
		return R;
	}
	/**
	 * 表示一个索引所需的比特数
	 * @return
	 */
	public int lgR() {
		int lgR = 0;
		for(int t = R-1; t >= 1; t /= 2) {
			lgR++;
		}
		return lgR;
	}
	/**
	 * 获取C的索引，在0到R-1之间
	 * @param c
	 * @return
	 */
	public int toIndex(char c) {
		if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
		return inverse[c];
	}
	/**
	 * 将字符串s转换成R进制的整数
	 * @param s
	 * @return
	 */
	public int[] toIndices(String s) {
		char[] source = s.toCharArray();
		int[] target = new int[s.length()];
		for(int i = 0; i < source.length; i++ ) {
			target[i] = toIndex(source[i]);
		}
		return target;
	}
	/**
	 *获取字母表中的索引位置的字母
	 * @param index
	 * @return
	 */
	public char toChar(int index) {
		if (index < 0 || index >= R) {
	            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
	     }
		 return alphabet[index];
	}
	/**
	 * 将R进制的整数转换为基于该字母表的字符串
	 * @param indices
	 * @return
	 */
	public String toChars(int[] indices) {
		StringBuilder s = new StringBuilder(indices.length);
		for(int i = 0; i < indices.length; i++) {
			s.append(toChar(indices[i]));
		}
		return s.toString();
	}
}
