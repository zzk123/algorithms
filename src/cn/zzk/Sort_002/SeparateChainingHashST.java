package cn.zzk.Sort_002;

import edu.princeton.cs.algs4.Queue;
/**
 * 基于拉链法的符号表
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
	 private static final int INIT_CAPACITY = 4;
	private int N; 	//键值对总数
	private int M;	//散列表的大小
	private SequentialSearchST<Key, Value>[] st; //存放链表对象的数组
	
	public SeparateChainingHashST(){
		this(997);
	}
	
	public SeparateChainingHashST(int N){
		//创建M条链表
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i=0; i<M; i++){
			st[i] = new SequentialSearchST(); 
		}
	}
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff)%M;
	}
	
	public Value get(Key key){
		return (Value)st[hash(key)].get(key);
	}
	//添加元素
	public void put(Key key, Value val){
		 if (key == null) throw new IllegalArgumentException("first argument to put() is null");
         if (val == null) {
            delete(key);
            return;
        }
         // double table size if average length of list >= 10
         if(N>=10*M)
        	 resize(2*M);
         
         int i = hash(key);
         if(!st[i].contains(key))
        	 N++;
		st[i].put(key, val);
	}
	//获取所有的键
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i=0; i<M; i++){
			for(Key key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}
	//删除
	public void delete(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to delete() is null");
		int i = hash(key);
		if(st[i].contains(key))
			N--;
		st[i].delete(key);
		
		//调整链表
		//保证查找效率
		if(M > INIT_CAPACITY && N <= 2*M)
			resize(M/2);
	}
	//改变数组长度
	private void resize(int chains){
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
		for(int i = 0; i < M; i++){
			for(Key key :st[i].keys()){
				temp.put(key, st[i].get(key));
			}
		}
		this.M = temp.M;
		this.N = temp.N;
		this.st = temp.st;
	}
	
}
