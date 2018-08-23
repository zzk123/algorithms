package cn.test.test1.search;
/**
 * 基于二叉查找树的符号表
 * @author zzk
 *
 */
public class BST<Key extends Comparable<Key>, Value> {
	
	private Node root;   //二叉查找树的根节点
	
	private class Node{
		
		private Key key;	//键
			
		private Value val;  //值
		
		private Node left, rigeht;   //指向子树的链接
		
		private int N;		//以该节点为根的子树中的节点总数
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	//返回节点数量
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if(x == null) {
			return 0;
		}else {
			return x.N;
		}
	}
	
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			return get(x.left, key);
		}else if(cmp > 0) {
			return get(x.rigeht, key);
		}else {
			return x.val;
		}
	}
	
	public void put(Key key, Value val) {
		//找到key，找到就更新它的值，不然就新建一个节点
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val) {
		//如果key存在于以x为根节点的子树中则更新它的值
		//否则将以key和val为键值对的新节点插入到该子树中
		if(x == null) {
			return new Node(key, val, 1);
		}
		//更新/新建节点
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = put(x.left, key, val);
		}else if(cmp > 0) {
			x.rigeht = put(x.rigeht, key, val);
		}else {
			x.val = val;
		}
		//更新长度
		x.N = size(x.left) + size(x.rigeht) + 1;
		return x;
	}
	//最小值
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node x) {
		if(x.left == null) {
			return x;
		}
		return min(x.left);
	}
	//向下取整
	public Node floor(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
			return x;
		if(cmp < 0)
			return floor(x.left, key);
		Node t = floor(x.rigeht, key);
		if(t!=null)
			return t;
		else
			return x;
	}
	
	public Key select(int k) {
		return select(root, k).key;
	}
	//获取深度
	private Node select(Node x, int k) {
		if(x == null)
			return null;
		int t = size(x.left);
		if(t > k) {
			return select(x.left, k);	//左树
		}else if(t < k) {
			return select(x.rigeht, k-t-1);	//右树
		}else {
			return x;	//刚好是自己
		}
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if(x.left == null) {
			return x.rigeht;
		}
		
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.rigeht) + 1;
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	//删除节点
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = delete(x.left, key);
		}else if(cmp > 0) {
			x.rigeht = delete(x.rigeht, key);
		}else {
			if(x.rigeht == null) {
				return x.left;
			}else if(x.left == null) {
				return x.rigeht;
			}
			Node t = x;
			x = min(t.rigeht);
			x.rigeht = deleteMin(t.rigeht);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.rigeht) + 1;
		return x;
	}

}
