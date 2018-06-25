package cn.test.test1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 先进先出队列（队列）是一种基于先进先出(FIFO)策略的集合类型
 * @author zzk
 *
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item> {

	private Node<Item> first;
	
	private Node<Item> last;
	
	private int n;
	
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public Queue(){
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
	/**
	 * 查看队列第一个元素
	 * @return
	 */
	public Item peek() {
		if(isEmpty())
			throw new NoSuchElementException();
		return first.item;
	}
	/**
	 * 添加元素，入队
	 * @param item
	 */
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if(isEmpty()) {
			first = last;
		}else {
			oldlast.next = last;
		}
		n++;		
	}
	/**
	 * 头元素出队，类似删除头元素
	 * @return
	 */
	public Item dequeue() {
		if(isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if(isEmpty())
			last = null;
		return item;
	}
	/**
	 * 返回队列字符串
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Item item : this) {
			s.append(item);
			s.append(" ");
		}
		return s.toString();
	}
	 
	/**
	 * 返回元素迭代器
	 */
	public Iterator<Item> iterator() {
		return new ListIterator<>(first);
	}
	/**
	 * 定义对象内部迭代器
	 * @author zzk
	 *
	 * @param <Item>
	 */
	private class ListIterator<Item> implements Iterator<Item>{
		/**
		 * 索引节点
		 */
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		/**
		 * 下个元素
		 */
		public boolean hasNext() {
			return current != null;
		}
		/**
		 * 删除操作，不可删除
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		/**
		 * 下个元素
		 */
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
