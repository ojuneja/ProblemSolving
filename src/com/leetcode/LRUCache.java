/**
 * 
 */
package com.leetcode;

import java.util.HashMap;

/**
 * @author Ojas Juneja
 *
 */

class LinkNode {
	int value, key;
	LinkNode next, pre;

	LinkNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

public class LRUCache {

	private int capacity;
	HashMap<Integer, LinkNode> hashMap = new HashMap<Integer, LinkNode>();
	LinkNode head = null, end = null;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (hashMap.containsKey(key)) {
			LinkNode node = hashMap.get(key);
			setHead(node);
			return node.value;
		} else {
			return -1;
		}
	}

	private void setHead(LinkNode node) {
		if (head == null) {
			node.next = null;
			node.pre = null;
			head = node;
		} else if (node != head) {
			if (node.next != null) {
				node.next.pre = node.pre;
			}
			if (node.pre != null) {
				node.pre.next = node.next;
			}
			node.pre = null;
			node.next = head;
			head.pre = node;
			head = node;
		}
		if (end == null) {
			end = head;
		}
	}

	private void remove(LinkNode node) {
		if (node.pre != null) {
			node.pre.next = node.next;
		} else {
			head = node.next;
		}
		if (node.next != null) {
			node.next.pre = node.pre;
		} else {
			end = node.pre;
		}
	}

	public void set(int key, int value) {
		if (hashMap.containsKey(key)) {
			LinkNode node = hashMap.get(key);
			remove(node);
			node.value = value;
			setHead(node);
			hashMap.put(key, node);
		} else {
			if (hashMap.size() >= capacity) {
				hashMap.remove(end.key);
				remove(end);
			}
			LinkNode node = new LinkNode(key, value);
			hashMap.put(key, node);
			setHead(node);
		}
	}

	public static void main(String[] args) {
		LRUCache lru = new LRUCache(5);
		lru.set(0, 1);
		lru.set(1, 2);
		lru.set(2, 3);
		lru.set(3, 4);
		lru.get(2);
		lru.set(4, 5);
		lru.set(5, 6);
		lru.get(0);
		lru.set(4, 4);
	}
}
