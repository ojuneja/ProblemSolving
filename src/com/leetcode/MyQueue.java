/**
 * 
 */
package com.leetcode;

import java.util.Stack;

/**
 * @author Ojas Juneja Leetcode lc232 Problem Name: Implement Queue using Stacks
 */
public class MyQueue {
	public static void main(String[] args) {
		Queue q = new Queue();
		q.push(1);
		q.push(2);
		q.push(3);
		System.out.println(q.peek());
		q.pop();
		System.out.println(q.peek());
		q.pop();
		q.pop();
		System.out.println(q.empty());
		System.out.println(q.peek());
	}
}

class Queue {

	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();

	public void push(int x) {
		stack1.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
		if (!stack1.empty() || !stack2.isEmpty()) {
			if (stack2.isEmpty()) {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			}
			stack2.pop();
		}
	}

	// Get the front element.
	public int peek() {
		if (!stack1.empty() || !stack2.isEmpty()) {
			if (stack2.isEmpty()) {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			}
			return stack2.peek();
		}
		return -1;
	}

	// Return whether the queue is empty.
	public boolean empty() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			return true;
		}
		return false;
	}
}
