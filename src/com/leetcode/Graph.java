/**
 * 
 */
package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ojas Juneja
 *
 */
public class Graph {

	private Map<Integer, List<Integer>> adjacencyList;
	private int numberOfVertices = 0;

	public int getNumberofVertices()
	{
		return numberOfVertices;
	}
	
	public Graph(int numberOfVertices) {
		adjacencyList = new HashMap<Integer, List<Integer>>();
		this.numberOfVertices = numberOfVertices;
		for (int i = 0; i < numberOfVertices; i++) {
			List<Integer> linkedList = new LinkedList<Integer>();
			adjacencyList.put(i, linkedList);
		}
	}

	public void setEdge(int source, int destination) {
		if (source > adjacencyList.size() || destination > adjacencyList.size()) {
			System.out.println("The vertex you entered is not present");
			return;
		}
		List<Integer> s = adjacencyList.get(source);
		s.add(destination);
		List<Integer> d = adjacencyList.get(destination);
		d.add(source);
	}

	public List<Integer> getEdge(int source) {
		if (adjacencyList.containsKey(source)) {
			return adjacencyList.get(source);
		} else
			return null;
	}

	public boolean isPresent(int source, int destination) {
		if (adjacencyList.containsKey(source)) {
			List<Integer> listVertices = adjacencyList.get(source);
			for (Integer vertex : listVertices) {
				if (vertex == destination) {
					return true;
				}
			}
		}
		return false;
	}
	
}
