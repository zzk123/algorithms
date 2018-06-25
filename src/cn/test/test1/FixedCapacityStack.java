package cn.test.test1;
/**
 * 一种表示泛型定容栈的抽象数据类型
 * @author zzk
 *
 */


public class FixedCapacityStack<Item> {
	private Item[] a;
	private int N;
	
	public FixedCapacityStack(int cap) {
		a = (Item[]) new Object[cap];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void push(Item item) {
		if(N == a.length) {
			resize(2*a.length);
		}
		a[N++] = item;
	}

	/**
	 * 栈顶删除元素
	 * 使用率不低于四分之一
	 * @return
	 */
	public Item pop() {
		Item item = a[--N];
		a[N] = null; //避免对象游离
		if(N > 0 && N == a.length/4) {
			resize(a.length/2);
		}
		return a[--N];
	}
	/**
	 * 将数组改变长度，将原有数据转移到新的数组中
	 * @param max
	 */
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	
}
