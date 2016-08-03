/**
 * 
 */
package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Queue;

/**
 * @author Ojas Juneja
 *
 */
public class TaskOrder {

	static class Task {
		int courseNumber;
		List<Integer> dependent = new ArrayList<Integer>();
		boolean visited = false;
	}

	public static int[] findOrder(int numCourses, int[][] prequisties) {
		Map<Integer, Task> map = new HashMap<>();
		Queue<Integer> q = new LinkedList<>();
		int counter = 0;
		int[] result = new int[numCourses];
		if (prequisties == null || prequisties.length == 0) {
			return result;
		}
		for (int i = 0; i < numCourses; i++) {
			Task task = new Task();
			task.courseNumber = i;
			map.put(i, task);
		}
		for (int[] course : prequisties) {
			if (map.containsKey(course[0])) {
				Task oldTask = map.get(course[0]);
				oldTask.dependent.add(course[1]);
				map.put(course[0], oldTask);
			} else {
				Task task = new Task();
				task.courseNumber = course[0];
				task.dependent.add(course[1]);
				map.put(course[0], task);
			}
		}
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Task> item = (Map.Entry<Integer, Task>) it.next();
			if (item.getValue().dependent.size() == 0) {
				it.remove();
				q.offer(item.getKey());
			}
		}
		while (!q.isEmpty()) {
			result[counter] = q.poll();
			System.out.println(result[counter]);
			removeDependency(result[counter], map, q);
			counter++;
		}
		return result;
	}

	private static void removeDependency(int course, Map<Integer, Task> map, Queue<Integer> q) {
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Task> item = (Map.Entry<Integer, Task>) it.next();
			for (int i = 0; i < item.getValue().dependent.size(); i++) {
				if (course == item.getValue().dependent.get(i)) {
					item.getValue().dependent.remove(i);
				}
			}
			if (item.getValue().dependent.size() == 0) {
				it.remove();
				q.offer(item.getValue().courseNumber);

			}

		}
	}
	
	public static void main(String [] args)
	{
		int[][] input = new int[][]{{0,1},{0,2},{0,3},{2,1},{2,3},{1,3},{1,4},{3,4},{3,5}}; 
		findOrder(6, input);
	}
}
