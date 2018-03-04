package cn.zzk.Sort_004;

import cn.zzk.Sort_003.Queue;
/**
 * 单词查找树
 * 1.查找命中所需的时间与被查找的键的长度成正比
 * 2.查找未命中只检查若干个字符
 * 
 * 字符的查找是通过charAt方法得到一个扩展ASCII码，
 * 这个码值和结点数组的索引是一一对应的，
 * 每个不同的字符对应着数组中的唯一索引。
 * next[c]指代的就是扩展ASCII码为c的字符
 * （比如小写的a，ASCII码为97，next[97]这个结点就指代了字符a）。
 * @author Administrator
 *
 * @param <Value>
 */
public class TrieST<Value> {
	
	private static int R = 256; //基数
	
	private Node root;			//单词查找树的根节点
	private int n;
	
	private static class Node{
		private Object val;
		private Node[] next = new Node[R];
	}
	/**
	 * 创建一个单词查找树
	 */
	public TrieST() {
		
	}
	/**
	 * 根据key值获取键
	 * @param key
	 * @return
	 */
	private Value get(String key) {
		if(key == null)
			throw new IllegalArgumentException("argument to get() is null");
		Node x = get(root, key, 0);
		if(x == null)
			return null;
		return (Value) x.val;
	}
	/**
	 * 判断key对应的键不为空
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		if(key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key)!=null;
	}
	/**
	 * 可以把key当作单词的前缀，d为单词树的深度，从刚开始的root开始，d不断增加，x不断指向下一个节点
	 * 直到找到对应的key值
	 * @param x
	 * @param key
	 * @param d
	 * @return
	 */
	private Node get(Node x, String key, int d) {
		//返回以x为根节点的子单词查找树中与key相关联的值
		if(x == null)
			return null;
		if(d == key.length())
			return x;
		//找到第d个字符所对应的子单词查找树
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	/**
	 * 添加单词key，和对应的值
	 * @param key
	 * @param val
	 */
	public void put(String key, Value val) {
		if(key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if(val == null)
			delete(key);
		else
			root = put(root, key, val, 0);
	}
	
	private Node put(Node x, String key, Value val, int d) {
		if(x == null)
			 x = new Node();
		if(d == key.length()) {
			if(x.val == null)
				n++;
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	/**
	 * 所有以prefix为前缀的键
	 * @param prefix
	 * @return
	 */
	public Iterable<String> keysWithPrefix(String prefix){
		Queue<String> results = new Queue<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}
	
	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		if(x == null)
			return;
		if(x.val != null)
			results.enqueue(prefix.toString());
		for(char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	/**
	 * 所有和pattern匹配的键（其中‘.’可以匹配任意字符）
	 * @param pattern
	 * @return
	 */
	public Iterable<String> keysThatMatch(String pattern){
		Queue<String> results = new Queue<String>();
		collect(root, new StringBuilder(), pattern, results);
		return results;
	}
	
	private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
		if(x == null)
			return;
		int d = prefix.length();
		if(d == pattern.length() && x.val != null) {
			results.enqueue(prefix.toString());
		}
		if(d == pattern.length())
			return ;
		char c = pattern.charAt(d);
		if(c == '.') {
			for(char ch = 0; ch < R; ch++) {
				prefix.append(ch);
				collect(x.next[ch], prefix, pattern, results);
				prefix.deleteCharAt(prefix.length()-1);
			}
		}else {
			prefix.append(c);
			collect(x.next[c], prefix, pattern, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	/**
	 * query的前缀中最长的键
	 * @param query
	 * @return
	 */
	public String longestPrefixOf(String query) {
		if(query == null)
			throw new IllegalArgumentException("argument to longestPrefixOf() is null");
		int length = longestPrefixOf(root, query, 0, -1);
		if(length == -1)
			return null;
		else
			return query.substring(0, length);
	}
	
	public int longestPrefixOf(Node x, String query, int d, int length) {
		if(x == null)
			return length;
		if(x.val != null)
			length = d;
		if(d == query.length())
			return length;
		char c = query.charAt(d);
		return longestPrefixOf(x.next[c], query, d+1, length);
	}
	/**
	 * 删除节点
	 * @param key
	 */
	public void delete(String key) {
		if(key == null)
			throw new IllegalArgumentException("argument to delete() is null"); 
		root = delete(root, key, 0);
	}
	
	private Node delete(Node x, String key, int d) {
		if(x == null)
			return null;
		if(d == key.length()) {
			if(x.val != null)
				n--;
			x.val = null;
		}else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d+1);
		}

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

}
