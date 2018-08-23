package cn.test.test1.search;

import edu.princeton.cs.algs4.Queue;

/**
 * 二分查找（基于有序数组）
 * @author zzk
 * 
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	
	private Key[] keys;
	
	private Value[] vals;
	
	private int N;
	
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
	public int size() {
		return N;
	}
	
	public Value get(Key key) {
		if(isEmpty()) {
			return null;
		}
		//通过二分查找算法查找key所在的索引
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		}else {
			return null;
		}
	}
	//根据二分查找算法查找key值
	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0) {
				hi = mid - 1;
			}else if(cmp > 0) {
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		return lo;
	}
	//根据二分查找算法查找key值
	//递归查找
	public int rank(Key key, int lo, int hi) {
		if(hi < lo) {
			return lo;
		}
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]);
		if(cmp < 0) {
			return rank(key, lo, hi);
		}else if(cmp > 0) {
			return rank(key, mid+1, hi);
		}else {
			return mid;
		}
	}
	
	public void put(Key key, Value val) {
		//查找key对应的索引
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		//返回的索引对应的value对应不上val，在索引位置重新存入
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	
	//基于二分查找的有序符号表的其他操作
	public Key min(){
		return keys[0];
	}
	public Key max(){
		return keys[N-1];
	}
	public Key select(int k){
		return keys[k];
	}
	//大于等于key的最小键
	public Key ceiling(Key key){
		if(key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		int i = rank(key);
		if(i==N)
			return null;
		else
			return keys[i];
	}
	//小于等于key的最大键
	public Key floor(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to floor() is null");
		int i = rank(key);
		if(i<N && key.compareTo(keys[i])==0)
			return keys[i];
		if(i==0)
			return null;
		else
			return keys[i-1];
	}
	//是否为空
	public boolean isEmpty(){
		return size()==0;
	}
	public void delete(Key key){
		if(key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		if(isEmpty())
			return;
		
		int i = rank(key);
		
		//如果不存在
		if(i == N || keys[i].compareTo(key) != 0 ){
			return; 
		}
		//向左移动
		for(int j = i; j < N-1; j++){
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
		keys[N] = null;
		vals[N] = null;
	}
	
	//获取lo到hi之间的所有键，已排序
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> q = new Queue<Key>();
		for(int i = rank(lo); i<rank(hi); i++){
			q.enqueue(keys[i]);
		}
		if(constraints(hi)){
			q.enqueue(keys[rank(hi)]);
		}
		return q;
	}

	public boolean constraints(Key key) {
		if(key==null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key)!=null;
	}
}
