package cn.zzk.Sort_003;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 模拟一个背包链表，收集元素并迭代遍历所有元素（不支持从中删除元素）
 * @author Administrator
 *
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item>{
	private Node<Item> first;
	private int n;
	
	/**
	 * 结点定义
	 * @author Administrator
	 *
	 * @param <Item>
	 */
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public Bag(){
		first = null;
		n = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return n;
	}
	
	public void add(Item item){
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
    /**
     * 返回元素的迭代
     */
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}
	
	/**
	 * 一个迭代器，可以遍历出所有的元素
	 * @author Administrator
	 *
	 * @param <Item>
	 */
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		
		public ListIterator(Node<Item> first){
			current = first;
		}
		
		public boolean hasNext(){
			return current != null;
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
}
