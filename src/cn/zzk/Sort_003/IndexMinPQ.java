package cn.zzk.Sort_003;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 使用二叉堆实现的索引最小优先队列
 * class IndexMinPQ是一个支持泛型的索引优先队列
 * IndexMinPQ支持普通的insert、delete-the-minimum、delete以及change-the-key方法。
 * 用户可以使用队列中的0到maxN-1号索引执行删除和修改方法。
 * IndexMinPQ支持获取队列最小元素，队列最小元素索引操作。
 * IndexMinPQ支持迭代器迭代所有插入的索引号。
 * 
 * @author Administrator
 *
 * @param <Key>
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

	private int maxN;			//索引优先队列中元素的最大个数 （n）
	private int n;				//当前索引队列中元素的个数
	private int[] pq;			//存储元素在Key数组中索引的位置，也就是pq[n] = i
	private int[] qp;			//存储元素在Key数组中排序的位置，也就是qp[i] = n
	private Key[] keys;			//keys[i] = key
	
	
	public IndexMinPQ(int maxN) {
		if(maxN < 0)
			throw new IllegalAccessError();
		n = 0;
		keys = (Key[]) new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		//初始化数组，以便判断数组排序时某位置是否存在元素
		for(int i = 0; i <= maxN; i++)
			qp[i] = -1;
	}
	
	public boolean isEmpty(){
		return n == 0;
	}
	/**
	 * 判断优先队列某位置是否已经存在索引i
	 * @param i
	 * @return
	 */
	public boolean contains(int i){
		if(i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		return qp[i] != -1;
	}
	
	/**
	 * 返回队列当前元素的个数
	 * @return
	 */
	public int size(){
		return n;
	}
	/**
	 *  插入索引i，key
	 * @param i
	 * @param key
	 */
	public  void insert(int i, Key key){
		if(i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		if(contains(i))
			throw new IllegalArgumentException("index is already in the priority queue");
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}
	/**
	 * 返回最小元素的索引
	 * @return
	 */
	public int minIndex(){
		if(n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}
	/**
	 * 返回最小元素
	 * @return
	 */
	public Key minKey(){
		if(n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return keys[pq[1]];
	}
	/**
	 * 删除最小元素
	 * @return
	 */
	public int delMin(){
		if( n==0 )
			throw new NoSuchElementException("Priority queue underflow");
		int min = pq[1];
		exch(1, n--);
		sink(1);
		assert min == pq[n+1];
		qp[min] = -1;
		keys[min] = null;
		pq[n+1] = -1;
		return min;
	}
	/**
	 * 索引i对应优先队列中的键值
	 * @param i
	 * @return
	 */
	public Key keyOf(int i){
		if(i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		if(!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		else
			return keys[i];
	}
	/**
	 * 改变与索引i关联的键值
	 * @param i
	 * @param key
	 */
	public void changeKey(int i, Key key){
		if(i < 0 || i >= maxN)
			throw new IllegalAccessError();
		if(!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}
	/**
	 * 改变与索引i关联的键值
	 * @param i
	 * @param key
	 */
	public void change(int i, Key key){
		changeKey(i, key);
	}
	/**
	 * key小于keys[i],才可以插入
	 * 插入小于keys[i]的值
	 * @param i
	 * @param key
	 */
	public void decreaseKey(int i, Key key){
		if(i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		if(!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		if(keys[i].compareTo(key) <= 0)
			throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
		keys[i] = key;
		swim(pq[i]);
	}
	/**
	 * keys[i]小于key
	 * 插入大于keys[i]的值
	 * @param i
	 * @param key
	 */
	public void increaseKey(int i, Key key){
		if ( i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		if(!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		if(keys[i].compareTo(key)>=0)
			throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
		keys[i] = key;
		sink(qp[i]);
	}
	/**
	 * 删除索引为i的元素
	 * @param i
	 */
	public void delete(int i){
		if(i < 0 || i >= maxN)
			throw new IllegalAccessError();
		if(!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		int index = qp[i];
		exch(index, n--);
		swim(index);
		sink(index);
		keys[i] = null;
		qp[i] = -1;
	}
	
	/**
	 * 比较操作，索引为i的元素大于索引为j的元素返回true，不然就返回false
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean greater(int i, int j){
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}
	/**
	 * 交换操作
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j){
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	/**
	 * 进行上浮操作(针对插入元素操作)
	 * 对于插入底部的元素与父元素（也就是索引为k/2的元素）比较，
	 * 如果小于父元素就进行交换
	 * @param k
	 */
	private void swim(int k){
		while( k > 1 && greater(k/2, k)){
			exch(k, k/2);
			k = k/2;
		}
	}
	/**
	 *   进行下沉操作(针对删除元素操作)
	 *   删除元素操作是指删除数组指定索引元素并且将数组的最后一个元素放在顶端
	 * 此时，就需要减小数组的大小，让这个元素下沉到指定位置
	 *   下沉操作是指父元素（索引为k）与子元素（索引为k/2）的元素不断比较，如果父元素
	 * 大于子元素就进行交换，不然就下一位比较
	 * @param k
	 */
	private void sink(int k){
		while( 2*k <= n){
			int j = 2*k;
			if(j < n && greater(j, j+1))
				j++;
			if(!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	@Override
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Integer>{

		private IndexMinPQ<Key> copy;
		
		public HeapIterator() {
			copy = new IndexMinPQ<Key>(pq.length - 1);
			for(int i = 1; i <= n; i++){
				copy.insert(pq[i], keys[pq[i]]);
			}
		}
		
		public boolean hasNext(){
			return !copy.isEmpty();
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		public Integer next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}
	}

}
