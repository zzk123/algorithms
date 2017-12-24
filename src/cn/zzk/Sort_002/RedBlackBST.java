package cn.zzk.Sort_002;

/**
 * 红黑树的插入算法
 * @author zzk
 * @version 1.0
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	/**
	 * 含有color变量的Node对象
	 */
	private class Node{
		/**
		 * 键
		 */
		Key key;			
		/**
		 * 相关联的值 
		 */
		Value value;		
		/**
		 * 左右子树
		 */
		Node left,right;	
		/**
		 * 子树中的结点总数
		 */
		int N;				
		/**
		 * 由其父节点指向它的链接
		 */
		boolean color;		
		
		Node(Key key, Value value, int N, boolean color){
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x){
		if(x==null)
			return false;
		return x.color = RED;
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node x) {
		if(x==null)
			return 0;
		else
			return x.N;
	}
	
	/**
	 * 向左旋转h的右链接
	 * @param 结点h
	 * @return
	 */
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left)+ size(h.right);
		return x;
	}
	/**
	 * 向右旋转h的左链接
	 * @param 结点h
	 * @return
	 */
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left)+size(h.right);
		return x;
	}
	/**
	 * 颜色转换（转换一个结点的两个红色子结点的颜色）
	 * @param h
	 */
	private void flipColors(Node h){
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	/**
	 * 查找key，找到就更新其值，否则就为他新建一个结点
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value){
		root = put(root, key, value);
		root.color = BLACK;
	}
	
	/**
	 * 1.如果右子结点是红色的而左结点是黑色的，进行左旋转
	 * 2.如果左子结点是红色的且它的左结点也是红色的，进行右旋转
	 * 3.如果左右子结点均为红色，进行颜色转换
	 * @param h
	 * @param key
	 * @param value
	 * @return
	 */
	private Node put(Node h, Key key, Value value){
		if(h==null)
			return new Node(key,value,1,RED);
		int cmp = key.compareTo(h.key);
		if(cmp<0)
			h.left = put(h.left, key, value);
		else if(cmp>0)
			h.right = put(h.right, key, value);
		else
			h.value = value;
		//如果右子结点是红色的而左结点是黑色的，进行左旋转
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		//如果左子结点是红色的且它的左结点也是红色的，进行右旋转
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		//如果左右子结点均为红色，进行颜色转换
		if(isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
}
