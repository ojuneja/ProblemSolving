/**
 * 
 */
package com.leetcode;

/**
 * @author Ojas Juneja LeetCode: lc109 Problem Name: Convert Sorted List to
 *         Binary Search Tree
 */
class LinkedList {
	int val;
	LinkedList next;

	LinkedList(int val) {
		this.val = val;
	}
}

class TreeClass {
	int val;
	TreeClass left;
	TreeClass right;

	TreeClass(int val) {
		this.val = val;
	}
}

public class SortedListToBST {
	LinkedList head = null;

	void addHead(int val) {
		LinkedList node = new LinkedList(val);
		node.next = head;
		head = node;
	}

	LinkedList getHead() {
		return head;
	}

	public TreeClass sortedListToBST(LinkedList head) {
		if (head == null) {
			return null;
		}
		return sortedListToBST( 0, calculateLength());
	}

	private int calculateLength() {
		int len = 0;
		LinkedList node = head;
		while (node != null) {
			node = node.next;
			len++;
		}
		return len - 1;
	}

	TreeClass sortedListToBST(int start, int end) {
		if (start < end) {
			return null;
		}
		int middle = (start + end) / 2;
		TreeClass left = sortedListToBST(start, middle - 1);
		TreeClass root = new TreeClass(head.val);
		head = head.next;
		TreeClass right = sortedListToBST(middle + 1, end);
		root.left = left;
		root.right = right;
		return root;
	}
	
	public static void main(String [] args)
	{
		SortedListToBST obj = new SortedListToBST();
		obj.addHead(1);
		obj.addHead(2);
		obj.addHead(3);
		obj.sortedListToBST(obj.getHead());
	}

}
