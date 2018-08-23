package cn.test.test1.search;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	
	private static final boolean RED = true;
	
	private static final boolean BLACK = false;
	
	private Node root;
	
	private class Node{
		
		Key key;
		
		Value value;
		
		Node left, right;
		
		int N;
		
		boolean color;
		
		Node(Key key, Value value, int N, boolean color){
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if(x == null) {
			return false;
		}
		return x.color == RED;
	}
	
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
	/**
	 * 向左旋转h
	 * @param h
	 * @return
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	/**
	 * 向右旋转
	 * @param h
	 * @return
	 */
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	/**
	 * 颜色转换（转换一个节点的两个红色子节点的颜色）
	 * @param h
	 */
	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	//添加节点
	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = BLACK;
	}
	//添加节点
	private Node put(Node h, Key key, Value value) {
		if(h == null) {
			return new Node(key, value, 1, RED);
		}
		int cmp = key.compareTo(h.key);
		//h节点的值大于key，向左子树继续递归
		if(cmp < 0) {
			h.left = put(h.left, key, value);
		}else if(cmp > 0) {
			//h节点的值小于key，向右子树继续递归
			h.right = put(h.right, key, value);
		}else {
			//相等直接赋值
			h.value = value;
		}
		//对树进行旋转操作
		//如果右子节点是红色，左节点是黑色，左旋转
		if(isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		//如果左子结点是红色的且它的左结点也是红色的，进行右旋转
		if(isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		//如果左右子结点均为红色，进行颜色转换
		if(isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
}
