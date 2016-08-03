/**
 * 
 */
package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
leetcode problem number: lc
geeksforgeeks problem number: gk
other: 
*/
public class GraphOperations {

	public void bfs(Graph g, int source) {
		int size = g.getNumberofVertices();
		boolean[] visited = new boolean[size];
		for (int i = 0; i < size; i++) {
			visited[i] = false;
		}
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		while (!queue.isEmpty()) {
			int node = queue.poll();
			System.out.println(node);
			List<Integer> list = g.getEdge(node);
			for (Integer value : list) {
				if (visited[value] != true) {
					queue.add(value);
					visited[value] = true;
				}
			}
		}
	}

	public void dfs(Graph g, int source) {
		int size = g.getNumberofVertices();
		boolean[] visited = new boolean[size];
		for (int i = 0; i < size; i++) {
			visited[i] = false;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(source);
		visited[source] = true;
		while (!stack.isEmpty()) {
			int node = stack.pop();
			System.out.println(node);
			List<Integer> list = g.getEdge(node);
			for (Integer value : list) {
				if (visited[value] != true) {
					stack.push(value);
					visited[value] = true;
				}
			}
		}
	}

	public boolean detectCycle(Graph g, int startVertex) {
		boolean flag = false;
		int size = g.getNumberofVertices();
		boolean[] visited = new boolean[size];
		int[] visitedFrom = new int[size];
		for (int i = 0; i < size; i++) {
			visited[i] = false;
			visitedFrom[i] = i;
		}
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(startVertex);
		visitedFrom[startVertex] = startVertex;
		visited[startVertex] = true;

		while (!arrayList.isEmpty()) {
			Integer node = arrayList.remove(arrayList.size()-1);
			//System.out.println(node + " ");
			List<Integer> vertices = g.getEdge(node);
			for (int i = 0; i < vertices.size(); i++) {
				int vertex = vertices.get(i);
				if (visited[vertex] == true) {
					if (visitedFrom[vertex] != vertex && g.isPresent(vertex, visitedFrom[vertex])) {
						System.out.println("Cycle exists between: " + vertex + " : " + visitedFrom[vertex]);
						flag = true;
					}
				} else {
					arrayList.add(vertex);
					visited[vertex] = true;
					visitedFrom[vertex] = node;
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Graph al = new Graph(4);
		al.setEdge(0, 1);
		al.setEdge(1, 3);
		al.setEdge(2, 3);
		al.setEdge(1, 2);

		GraphOperations go = new GraphOperations();
		go.bfs(al, 0);
		go.dfs(al, 0);
		go.detectCycle(al, 0);
	}

}
