package cn.zzk.Sort_002;

import edu.princeton.cs.algs4.Queue;

/**
 * 
 * 基于线性探测的符号表
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {
	
	private int N;  //符号表中的键值对的总数
	private int M = 16; //线性探测表的大小
	private Key[] keys; //键
	private Value[] vals;//值
	public LinearProbingHashST(){
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	//调整数组大小(调大)
	private void resize(int cap){
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>();
		for(int i=0; i<M; i++){
			if(keys[i]!=null){
				t.put(keys[i], vals[i]);
			}
		}
		keys = t.keys;
		vals = t.vals;
		M  = t.M;
	}
	
	//添加键值（如果key值对应的位置已有元素，那么会在元素的下一位继续寻找插入位置）
	private void put(Key key, Value val){
		if(N>=M/2)
			resize(2*M);//将M加倍
		int i;
		for(i=hash(key); keys[i]!=null; i = (i+1)%M){
			if(keys[i].equals(key)){
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	//获取值（如果key所在位置为null，那么不存在，如果所在位置key不相等，那么会继续下移找下一个key）
	public Value get(Key key){
		for(int i=hash(key); keys[i]!=null; i = (i+1)%M){
			if(keys[i].equals(key)){
				return vals[i];
			}
		}
		return null;
	}
	//获取所有的键
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i=0; i<M; i++){
			if(keys[i]!=null)
				queue.enqueue(keys[i]);
		}
		return queue;
	}
	//删除操作（删除key值以及对应的value,然后对于key后面的元素进行重新put，并且动态改变数组的大小）
	public void delete(Key key){
		if(!contains(key))
			return;
		int i = hash(key);
		while(!key.equals(keys[i])){
			i = (i+1)%M;
		}
		//删除指定的位置数
		keys[i] = null;
		vals[i] = null;
		
		i = (i+1)%M;
		while(keys[i]!=null){
			Key keyToRedo = keys[i];
			Value valTpRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valTpRedo);
			i = (i+1)%M;
		}
		N--;
		//保证所用的内存量和表中的键值对的比例总在一定范围之内，动态调整数组大小保证N/M不大于1/2
		if(N>0 && N<=M/8)
			resize(M/2);
	}
	//判断是否存在key值
	public boolean contains(Key key){
		if(key==null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key)!=null;
	}
}
