/**
 * 
 */
package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
leetcode problem number: lc170
Problem Name: Two Sum III - Data structure design
status: red
geeksforgeeks problem number: gk
other: 
*/
/**
 * @author Ojas Juneja
 *
 */
class TwoSum {
	Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

	// Add the number to an internal data structure.
	public void add(int number) {
		hashMap.put(number, hashMap.containsKey(number) ? hashMap.get(number) + 1 : 1);
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
			int number = entry.getValue();
			int searchValue = value - number;
			if ((number == searchValue && hashMap.get(searchValue) > 1)
					|| (number != searchValue && hashMap.containsKey(searchValue))) {
				return true;
			}
		}
		return false;
	}
}

public class TwoSumImpl {
	public static void main(String[] args) {
		TwoSum ts = new TwoSum();
		ts.add(1);
		ts.add(2);
		ts.add(-1);
		ts.add(1);
		ts.add(3);
		System.out.println(ts.find(2));
		System.out.println(ts.find(5));
		System.out.println(ts.find(6));
	}
}
