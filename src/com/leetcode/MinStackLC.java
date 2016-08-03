/**
 * 
 */
package com.leetcode;

/**
 * @author Ojas Juneja
 * Leetcode lc155
 * Problem Name: Min Stack
 * status: yellow
 */

class Node {
	int val;
	Node next;
	int minimum;

	Node(int x) {
		val = x;
		next = null;
		minimum = x;
	}
}

class MinStack {

	Node head;

	public void push(int x) {
		if (head == null) {
			head = new Node(x);
		} else {
			Node node = new Node(x);
			node.minimum = Math.min(head.minimum, x);
			node.next = head;
			head = node;
		}
	}

	public void pop() {
		if (head != null) {
			head = head.next;
		}
	}

	public int top() {
		if (head != null) {
			return head.val;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	public int getMin() {
		if (head != null) {
			return head.minimum;
		} else {
			return Integer.MAX_VALUE;
		}
	}
}

public class MinStackLC
{
	public static void main(String [] args)
	{
			MinStack stack = new MinStack();
			stack.push(2);
			stack.push(3);
			System.out.println(stack.top());
			stack.push(1);
			System.out.println(stack.getMin());
			stack.pop();
			System.out.println(stack.getMin());
	}
}
