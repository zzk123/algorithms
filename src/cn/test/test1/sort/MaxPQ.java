package cn.test.test1.sort;
/**
 * 基于堆的优先队列
 * @author zzk
 */
public class MaxPQ< Key extends Comparable<Key>> {
	
	private Key[] pq;
	
	private int N = 0;
	
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN+1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	/**
	 * 删除最大值
	 * @return
	 */
	public Key delMax() {
		Key max = pq[1];		//从根节点得到最大元素
		exch(1, N--);			//将其和最后一个节点交换
		pq[N+1] = null;			//防止对象游离
		sink(1);				//恢复堆的有序性
		return max;
	}
	/**
	 * 判断pq[i]是否小于pq[j]
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	/*
	 * 交换
	 */
	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	/**
	 * 由下至上的堆有序化（上浮的实现）
	 * 为了插入元素，在堆的最后面进行插入，然后进行上浮操作
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1 && less(k/2, k)){
			exch(k/2, k);
			k = k/2;
		}
	}
	/**
	 * 由上到下的堆有序化（下沉）
	 * 为了删除操作，删除的元素跟最后一个元素进行交换，然后进行下沉操作
	 * @param k
	 */
	private void sink(int k) {
		while(2*k <= N) {
			int j = 2 * k;
			if(j < N && less(j, j + 1)) {
				j++;
			}
			if(!less(k, j)) {
				break;
			}
			exch(k, j);
			k = j;
		}
	}
}
