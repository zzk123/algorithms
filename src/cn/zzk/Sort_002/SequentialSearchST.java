package cn.zzk.Sort_002;

import edu.princeton.cs.algs4.Queue;

/**
 * 顺序查找（基于无序链表）
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key,Value> {
	//数量节点
	private int n;
	//链表节点
	private Node first;
	//链表节点定义
	private class Node{
		
		Key key;
		
		Value value;
		
		Node next;
		
		public Node(Key key, Value value, Node next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	//查找给定的键，返回相关关联的值
	public Value get(Key key){
		for(Node x = first; x!=null; x = x.next){
			if(key.equals(x.key)){
				return x.value;
			}
		}
		return null;
	}
	//查找给定的键，找到就更新其键，否则在表中新建节点
	public void put(Key key, Value value){
		if(key == null)
			throw new IllegalArgumentException("first argument to put() is null"); 
		if(value==null){
			delete(key);
			return;
		}
		for(Node x = first; x!=null; x = x.next){
			if(key.equals(x.key)){
				x.value = value;
				return;
			}
		}
		first = new Node(key,value,first);
		n++;
	}
	//获取所有的key值
	public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
	//是否为空
	public boolean isEmpty(){
		return size() == 0;
	}
	//返回节点数量
	public int size(){
		return n;
	}
	//删除节点
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
        first = delete(first, key);
    }
    private Node delete(Node x, Key key){
    	if(x==null)
    		return null;
    	if(key.equals(x.key)){
    		n--;
    		return x.next;
    	}
    	x.next = delete(x.next, key);
    	return x;
    }
    //判断是否存在key节点
    public boolean contains(Key key){
    	if(key==null)
    		throw new IllegalArgumentException("argument to contains() is null");
    	return get(key)!=null;
    }
   
}
