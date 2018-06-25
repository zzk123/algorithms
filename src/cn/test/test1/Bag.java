package cn.test.test1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包是一种不支持从中删除元素的集合数据结构
 * 它 的目的是为了帮助用例收集元素并迭代遍历所有收集到的元素
 * 
 * @author zzk
 *
 */
public class Bag<Item> implements Iterable<Item> {
	/**
	 * 元素节点
	 */
	private Node<Item> first;
	/**
	 * 元素个数
	 */
	private int n;
	/**
	 * 定义元素节点
	 * @author zzk
	 *
	 * @param <Item>
	 */
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	/**
	 * 创建一个空的背包
	 */
	public Bag() {
		
	}
	/**
	 * 添加元素
	 */
	public void add() {
		first = null;
		n = 0;
	}
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return first == null;
	}
	/**
	 * 返回元素个数
	 * @return
	 */
	public int size() {
		return n;
	}
	/**
	 * 添加新元素
	 * @param item
	 */
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	/**
	 * 返回元素迭代器
	 */
	public Iterator<Item> iterator() {
		return new ListIterator<>(first);
	}
	/**
	 * 定义内部元素迭代器，不能删除
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
		 * 是否为空
		 */
		public boolean hasNext() {
			return current != null;
		}
		/**
		 * 不能删除
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		/**
		 * 下个元素
		 */
		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			return item;
		}
	}
	
}
