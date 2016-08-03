/**
 * 
 */
package com.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/*
leetcode problem number: lc
geeksforgeeks problem number: gk
other: 
*/
/**
 * @author Ojas Juneja
 *
 */
public class NAryOperations {

	public int dfs(NAryNode root, int level, int max) {
		if (root == null) {
			return max;
		}
		System.out.println(root.val);
		for (NAryNode child : root.childrens) {
			dfs(child, level++, level > max ? level : max);
		}
		return max;
	}

	public int bfs(NAryNode root) {
		if (root == null) {
			return 0;
		}
		int counter = 0;
		int level = 0;
		Queue<NAryNode> q = new LinkedList<NAryNode>();
		q.offer(root);
		while(!q.isEmpty())
		{
			counter = 0;
			NAryNode node = q.poll();
			System.out.println(node.val);
			while(counter < node.childrens.size())
			{
				q.offer(node.childrens.get(counter));
				counter++;
			}
		}
		return level;
	}

	private static NAryNode createNAryTree() {
		NAryNode root = new NAryNode(1);
		NAryNode child1 = new NAryNode(2);
		NAryNode child2 = new NAryNode(3);
		NAryNode child3 = new NAryNode(4);
		NAryNode child4 = new NAryNode(5);
		NAryNode grandChild1 = new NAryNode(6);
		NAryNode grandChild2 = new NAryNode(7);
		NAryNode grandChild3 = new NAryNode(8);
		NAryNode grandChild4 = new NAryNode(9);
		NAryNode grandChild5 = new NAryNode(10);
		NAryNode grandChild6 = new NAryNode(11);
		NAryNode grandChild7 = new NAryNode(12);
		NAryNode grandgrandChild = new NAryNode(13);
		root.childrens.add(child1);
		root.childrens.add(child2);
		root.childrens.add(child3);
		root.childrens.add(child4);
		child1.childrens.add(grandChild1);
		child1.childrens.add(grandChild2);
		child1.childrens.add(grandChild3);
		child1.childrens.add(grandChild4);
		child3.childrens.add(grandChild5);
		child3.childrens.add(grandChild6);
		child4.childrens.add(grandChild7);
		grandChild5.childrens.add(grandgrandChild);
		return root;
	}

	public static void main(String[] args) {
		NAryNode root = createNAryTree();
		NAryOperations object = new NAryOperations();
		System.out.println(object.dfs(root, 1, 1));
		//System.out.println(object.bfs(root));
	}

}
