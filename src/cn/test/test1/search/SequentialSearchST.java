package cn.test.test1.search;

import edu.princeton.cs.algs4.Queue;

/**
 * 顺序查找（基于无序链表）
 * @author zzk
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {
	
	private int n;				//节点数量
	
	private Node first; 		//链表首节点
	
	private class Node{
		//链表节点定义
		Key key;
		
		Value val;
		
		Node next;
		
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	public Value get(Key key) {
		//查找给定的键，返回相关联的值
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				return x.val;	//找到值
			}
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		//查找给定的键，找到则更新其值，否则在表中新建节点
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				x.val = val;    //找到更新
				return;
			}
		}
		first = new Node(key, val, first); //找不到，新建节点
		n++;
	}
	//是否为空
	public boolean contains(Key key) {
		if(key==null)
    		throw new IllegalArgumentException("argument to contains() is null");
    	return get(key)!=null;
	}
	//获取所有的key值
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(Node x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}
	//删除节点
	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		first = delete(first, key);
	}
	//删除节点
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null;
		}
		if(key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
}
