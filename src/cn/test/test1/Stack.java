package cn.test.test1;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 下压栈，一种基于后进先出策略的集合类型
 * @author zzk
 *
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item> {

	private Node<Item> first;
	
	private int n;
	
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
		private Node<Item> pre;
	}
	
	public Stack() {
		first = null;
		n = 0;
	}
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * 添加一个元素
	 * @param item
	 */
	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	/**
	 * 删除最近添加的元素；
	 * @return
	 */
	public Item pop() {
		if(isEmpty())
			throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}

	/**
	 * 返回元素数量
	 * @return
	 */
	public int size() {
		return n;
	}
	/**
	 * 返回栈顶元素
	 * @return
	 */
	public Item peek() {
		if(isEmpty())
			throw new NoSuchElementException();
		return first.item;
	}
	
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator<Item>(first);
	}
	/**
	 * 内部类，迭代器
	 * @author zzk
	 *
	 * @param <Item>
	 */
	private class ListIterator<Item> implements Iterator<Item>{

		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;  
		}
		public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
	}
}
