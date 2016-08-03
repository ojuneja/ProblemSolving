/**
 * 
 */
package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Deque;

/*
leetcode problem number: lc
geeksforgeeks problem number: gk
other: 
*/
/**
 * @author Ojas Juneja
 *
 */

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

public class ArrayLC {

	private int sum = 0;
	private static Set<Integer> visited;

	/**
	 * Leetcode problem number: lc1 Problem Name: Two Sum status: green
	 */
	public int[] twoSum(int[] nums, int target) {
		int[] array = new int[2];
		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (hashMap.containsKey(complement)) {
				array[0] = hashMap.get(complement);
				array[1] = i;
				break;
			}
			hashMap.put(complement, i);
		}
		return array;
	}

	/**
	 * Leetcode problem number: lc15 Problem Name: three sum status: yellow
	 */
	public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (num == null || num.length < 3) {
			return result;
		}
		if (num.length == 3) {
			if (num[0] + num[1] + num[2] != 0) {
				return result;
			} else {
				List<Integer> arrayList = new ArrayList<Integer>();
				arrayList.add(num[0]);
				arrayList.add(num[1]);
				arrayList.add(num[2]);
				result.add(arrayList);
			}
		}
		Arrays.sort(num);
		for (int i = 0; i < num.length - 2; i++) {
			if (i == 0 || (num[i] > num[i - 1])) {
				int start = i + 1;
				int end = num.length - 1;
				while (start < end) {
					if (num[i] + num[start] + num[end] == 0) {
						List<Integer> arrayList = new ArrayList<Integer>();
						arrayList.add(num[i]);
						arrayList.add(num[start]);
						arrayList.add(num[end]);
						result.add(arrayList);
						start++;
						end--;
						while (start < end && num[end] == num[end + 1]) {
							end--;
						}
						while (start < end && num[start] == num[start - 1]) {
							start++;
						}
					} else if (num[i] + num[start] + num[end] < 0) {
						start++;
					} else {
						end--;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc169 Problem Name: Majority Element status:
	 * green
	 */
	public int majorityElement(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		int number = nums[0];
		int count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (count == 0) {
				number = nums[i];
				count = 1;
			} else if (number != nums[i]) {
				count--;
			} else {
				count++;
			}
		}
		count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (number == nums[i]) {
				count++;
			}
		}
		return count > nums.length / 2 ? number : -1;
	}

	/**
	 * Leetcode problem number: lc292 Problem Name: Nim Game status: red
	 */
	public boolean canWinNim(int n) {
		return n % 4 > 0;
	}

	/**
	 * Leetcode problem number: lc121 Problem name: Best Time to Buy and Sell
	 * Stock status: green
	 */
	public int maxProfit(int[] prices) {
		int profit = 0;
		int minimum = prices[0];
		if (prices.length < 2) {
			return 0;
		}
		for (int i = 1; i < prices.length; i++) {
			profit = Math.max(Math.max(profit, prices[i] - prices[i - 1]), prices[i] - minimum);
			minimum = Math.min(minimum, prices[i]);
		}
		return profit;
	}

	/**
	 * Leetcode problem number: lc122 Problem name: Best Time to Buy and Sell
	 * Stock II status: green
	 */
	public int maxProfit2(int[] prices) {
		int profit = 0;
		if (prices.length < 2) {
			return 0;
		}
		for (int i = 1; i < prices.length; i++) {
			int value = prices[i] - prices[i - 1];
			if (value > 0) {
				profit = profit + value;
			}
		}
		return profit;
	}

	/**
	 * Leetcode problem number: lc53 Problem Name: Maximum Subarray status:
	 * green
	 */
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int prev = nums[0];
		int max = nums[0];
		if (nums.length == 1) {
			return nums[0];
		}
		for (int i = 1; i < nums.length; i++) {
			prev = Math.max(prev + nums[i], nums[i]);
			max = Math.max(max, prev);
		}
		return max;
	}

	/**
	 * Leetcode problem number: lc189 Problem Name: Rotate Array status: green
	 */
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int size = nums.length - 1;
		if (k >= size) {
			return;
		}
		reverse(nums, 0, k);
		reverse(nums, k + 1, size);
		reverse(nums, 0, size);
	}

	/**
	 * Leetcode problem number: lc153 Problem Name: Find Minimum in Rotated
	 * Sorted Array status: yellow
	 */
	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		return findMinimum(nums, 0, nums.length - 1);
	}

	/**
	 * Leetcode problem number: lc153 Problem Name: Find Minimum in Rotated
	 * Sorted Array status: yellow
	 */
	int findMinimum(int[] nums, int left, int right) {
		if (left == right) {
			return nums[right];
		}
		if (right - left == 1) {
			return Math.min(nums[left], nums[right]);
		}
		int mid = (left + right) / 2;
		if (nums[left] < nums[right]) {
			return nums[left];
		} else if (nums[mid] > nums[left]) {
			return findMinimum(nums, mid, right);
		} else {
			return findMinimum(nums, left, mid);
		}
	}

	/**
	 * Leetcode problem number: lc189 Problem Name: Rotate Array status: green
	 */
	public void reverse(int[] nums, int begin, int end) {
		while (begin < end) {
			int temp = nums[begin];
			nums[begin] = nums[end];
			nums[end] = temp;
			begin++;
			end--;
		}
	}

	/**
	 * Leetcode problem number: lc136 Problem Name: Single Number
	 */
	public int singleNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			result = result ^ nums[i];
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc7 Problem Name: Reverse Integer status: green
	 */
	public int reverse(int x) {
		int result = 0;
		while (x > 0) {
			result = (result * 10) + x % 10;
			x = x / 10;
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc5 Problem Name: Longest Palindromic Substring
	 * status: green
	 */
	public String longestPalindrome(String s) {

		if (s == null || s.length() == 0) {
			return "";
		}
		if (s.length() == 1) {
			return s;
		}
		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			String temp = calculateLongest(s, i, i + 1);
			if (temp.length() > longest.length()) {
				longest = temp;
			}
			temp = calculateLongest(s, i, i);
			if (temp.length() > longest.length()) {
				longest = temp;
			}
		}
		return longest;
	}

	/**
	 * Leetcode problem number: lc5 Problem Name: Longest Palindromic Substring
	 * status: green
	 */
	public String calculateLongest(String s, int begin, int end) {
		while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}

	/**
	 * Leetcode problem number: lc8 Problem Name: String to Integer (atoi)
	 * status: green
	 */
	public int myAtoi(String str) {
		int i = 0;
		boolean negativeFlag = false;
		double result = 0;
		if (str.charAt(i) == '-') {
			i = 1;
			negativeFlag = true;
		}
		while (i < str.length()) {
			double val = str.charAt(i) - '0';
			if (val >= 0 && val <= 9) {
				result = result * 10 + val;
			}
			i++;
		}
		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (result < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return (int) (negativeFlag == true ? -result : result);
	}

	/**
	 * Leetcode problem number: lc191 Problem Name: Number of 1 Bits
	 */
	public int hammingWeight(int n) {
		int count = 0;
		for (int i = 1; i < 33; i++) {
			int val = (1 << i);
			if ((n & val) != 0) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Leetcode problem number: lc13 Problem Name: Roman to Integer status :
	 * yellow
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] nums = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case 'M':
				nums[i] = 1000;
				break;
			case 'D':
				nums[i] = 500;
				break;
			case 'C':
				nums[i] = 100;
				break;
			case 'L':
				nums[i] = 50;
				break;
			case 'X':
				nums[i] = 10;
				break;
			case 'V':
				nums[i] = 5;
				break;
			case 'I':
				nums[i] = 1;
				break;
			}
		}
		int sum = nums[0];
		for (int i = nums.length - 1; i > 0; i--) {
			if (nums[i] <= nums[i - 1]) {
				sum = sum + nums[i];
			} else {
				sum = sum - nums[i];
			}
		}
		return sum;
	}

	/**
	 * Leetcode problem number: lc20 Problem Number: Valid Parentheses status:
	 * green
	 */
	public boolean isValid(String s) {
		HashMap<Character, Character> hashMap = new HashMap<>();
		Stack<Character> stack = new Stack<>();
		hashMap.put(')', '(');
		hashMap.put('}', '{');
		hashMap.put(']', '[');
		if (s.length() % 2 != 0) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (hashMap.get(ch) != null) {
				if (stack.empty() || hashMap.get(ch) != stack.pop()) {
					return false;
				}
			} else {
				stack.push(ch);
			}
		}
		return stack.isEmpty() ? true : false;
	}

	/**
	 * Leetcode problem number: lc14 Problem Name: Longest Common Prefix status:
	 * green
	 */
	public String longestCommonPrefix(String[] strs) {
		for (int i = 0; i < strs[0].length(); i++) {
			int j = 1;
			char ch = strs[0].charAt(i);
			while (j < strs.length) {
				if (strs[j].length() - 1 < i || ch != strs[j].charAt(i)) {
					return strs[0].substring(0, i);
				}
				j++;
			}
		}
		return strs[0];
	}

	/**
	 * Leetcode problem number: lc9 Problem Name: Palindrome status: green
	 */
	public boolean isPalindrome(int x) {
		int result = 0;
		int temp = x;
		while (temp > 0) {
			result = (result * 10) + temp % 10;
			temp = temp / 10;
		}
		return result == x ? true : false;
	}

	/**
	 * Leetcode problem number: lc38 Problem Name: Count and Say Different
	 * status: green
	 */
	public String countAndSayDifferent(int n) {
		String result = "";
		if (n <= 0)
			return null;
		Stack<Integer> stack = new Stack<>();
		int count = 1;
		stack.push(n % 10);
		n = n / 10;
		while (n > 0) {
			int temp = n % 10;
			if (stack.peek() != temp) {
				stack.push(count);
				stack.push(temp);
				count = 1;
			} else {
				count++;
			}
			n = n / 10;
		}
		stack.push(count);
		while (!stack.isEmpty()) {
			result = result + stack.pop();
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc258 Problem Name: Add Digits status: green
	 * method: iteration
	 */
	public int addDigits(int num) {
		int result = 0;
		if (num < 9) {
			return num;
		}
		while (true) {
			while (num > 0) {
				result = result + num % 10;
				num = num / 10;

			}
			if (result > 9) {
				num = result;
				result = 0;
			} else {
				break;
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc258 Problem Name: Add Digits status: green
	 * method: iteration
	 */
	public int addDigitsIter(int num) {
		if (num / 10 == 0) {
			return num;
		}
		addDigitsRecur(num);
		return sum;
	}

	/**
	 * Leetcode problem number: lc258 Problem Name: Pow(x, n) status: green
	 * method: iteration
	 */
	void addDigitsRecur(int num) {
		if (sum / 10 == 0 && num == 0) {
			return;
		} else if (num == 0) {
			num = sum;
			sum = 0;
		}
		sum = sum + num % 10;
		addDigitsRecur(num / 10);
	}

	/**
	 * Leetcode problem number: lc50 Problem Name: Pow(x, n) status: yellow
	 * method: iteration
	 */
	public double power(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double v = power(x, n / 2);
		if (n % 2 == 0) {
			return v * v;
		} else {
			return v * v * x;
		}
	}

	/**
	 * Leetcode problem number: lc50 Problem Name: Pow(x, n) status: yellow
	 * method: iteration
	 */
	public double myPow(double x, int n) {
		if (n < 0) {
			return 1 / power(x, -n);
		} else {
			return power(x, n);
		}
	}

	/**
	 * Leetcode problem number: lc283 Problem Name: Move Zeroes status: yellow
	 * method: iteration
	 */
	public void moveZeroes(int[] nums) {
		int index = 0;
		if (nums == null || nums.length == 0) {
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[index] = nums[i];
				index++;
			}
		}
		while (index < nums.length) {
			nums[index] = 0;
			index++;
		}
	}

	/**
	 * Leetcode problem number: lc27 Problem name: Remove Element status: yellow
	 */
	public int removeElement(int[] nums, int val) {
		int index = 0;
		if (nums == null || nums.length == 0) {
			return 0;
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[index] = nums[i];
				index++;
			}
		}
		return index;
	}

	/**
	 * Leetcode problem number: lc217 Problem Name: Contains Duplicate status:
	 * green
	 */
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		if (nums == null || nums.length == 0) {
			return false;
		}
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				return false;
			} else {
				set.add(nums[i]);
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc26 Problem Name: Remove Duplicates from Sorted
	 * Array status : green
	 */
	public int removeDuplicates(int[] nums) {
		int index = 1;
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				nums[index] = nums[i];
				index++;
			}
		}
		return index;
	}

	/**
	 * Leetcode problem number: no lc
	 */
	public void merge(int[] nums1, int[] nums2) {
		int varNums1 = 0, varNums2 = 0;
		int[] result = new int[nums1.length + nums2.length];
		int index = 0;
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return;
		}
		while (varNums1 < nums1.length && varNums2 < nums2.length) {
			if (nums1[varNums1] <= nums2[varNums2]) {
				result[index] = nums1[varNums1];
				varNums1++;
			} else {
				result[index] = nums2[varNums2];
				varNums2++;
			}
			index++;
		}
		while (varNums1 < nums1.length) {
			result[index] = nums1[varNums1];
			varNums1++;
			index++;
		}
		while (varNums2 < nums2.length) {
			result[index] = nums2[varNums2];
			varNums2++;
			index++;
		}
	}

	/**
	 * Leetcode problem number: lc88 Problem name: Merge Sorted Array status:
	 * yellow
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return;
		}
		while (m > 0 && n > 0) {
			if (nums1[m - 1] > nums2[n - 1]) {
				nums1[m + n - 1] = nums1[m - 1];
				m--;
			} else {
				nums1[m + n - 1] = nums2[n - 1];
				n--;
			}
		}
		while (n > 0) {
			nums1[m + n - 1] = nums2[n - 1];
			n--;
		}
	}

	/**
	 * Leetcode problem number: lc150 Problem name: Evaluate Reverse Polish
	 * Notation status: green
	 */
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		Set<String> validOp = new HashSet<>();
		validOp.add("+");
		validOp.add("-");
		validOp.add("*");
		validOp.add("/");
		if (tokens == null || tokens.length == 0) {
			return 0;
		}
		for (String token : tokens) {
			if (!stack.isEmpty() && validOp.contains(token)) {
				int number2 = stack.pop();
				int number1 = stack.pop();
				if (token.equals("+")) {
					stack.push(number1 + number2);
				} else if (token.equals("-")) {
					stack.push(number1 - number2);
				} else if (token.equals("*")) {
					stack.push(number1 * number2);
				} else if (token.equals("/")) {
					stack.push(number1 / number2);
				}
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}

	/**
	 * Leetcode problem number: lc42 Problem Name: Trapping Rain Water status:
	 * red
	 */
	public int trap(int[] height) {
		int result = 0;
		if (height == null || height.length <= 2) {
			return result;
		}
		int len = height.length;
		int[] left = new int[len];
		int[] right = new int[len];
		left[0] = height[0];
		right[0] = height[len - 1];
		for (int i = 1; i < len - 1; i++) {
			if (height[i] > left[i - 1]) {
				left[i] = height[i];
			} else {
				left[i] = left[i - 1];
			}
		}
		for (int i = len - 2; i >= 0; i--) {
			if (height[i] > right[i + 1]) {
				right[i] = height[i];
			} else {
				right[i] = right[i + 1];
			}
		}

		for (int i = 1; i < len - 1; i++) {
			result = result + Math.min(left[i], right[i]) - height[i];
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc202 Problem Name: Happy Number status : green
	 */
	public boolean isHappy(int n) {
		int result = 0;
		Set<Integer> set = new HashSet<>();
		if (n < 0) {
			return false;
		}
		if (n == 1) {
			return true;
		}
		while (true) {
			while (n > 0) {
				result = result + ((n % 10) * (n % 10));
				n = n / 10;
			}
			if (result != 1) {
				if (set.contains(result)) {
					break;
				} else {
					set.add(result);
				}
				n = result;
				result = 0;
			} else
				return true;
		}
		return false;
	}

	/**
	 * Leetcode problem number: lc49 Problem Name: Group Anagrams status: yellow
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<List<String>>();
		HashMap<String, ArrayList<Integer>> hashMap = new HashMap<String, ArrayList<Integer>>();
		if (strs == null || strs.length == 1) {
			return result;
		}
		for (int i = 0; i < strs.length; i++) {
			char[] array = strs[i].toCharArray();
			Arrays.sort(array);
			String word = String.valueOf(array);
			if (hashMap.get(word) == null) {
				ArrayList<Integer> arrayList = new ArrayList<>();
				arrayList.add(i);
				hashMap.put(word, arrayList);
			} else {
				hashMap.get(word).add(i);
			}
		}
		for (ArrayList<Integer> arr : hashMap.values()) {
			List<String> resultSubList = new ArrayList<>();
			for (int i = 0; i < arr.size(); i++) {
				resultSubList.add(strs[arr.get(i)]);
			}
			result.add(resultSubList);
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc67 Problem Name: Add Binary status: green
	 */
	public String addBinary(String a, String b) {
		StringBuilder result = new StringBuilder();
		int value = 0;
		int sumA = 0;
		int sumB = 0;
		int carry = 0;
		if (a == null || a.length() == 0) {
			return b;
		}
		if (b == null || b.length() == 0) {
			return a;
		}
		int sizeA = a.length() - 1;
		int sizeB = b.length() - 1;
		while (sizeA >= 0 || sizeB >= 0) {
			if (sizeA >= 0) {
				sumA = a.charAt(sizeA) - '0';
				sizeA--;
			}
			if (sizeB >= 0) {
				sumB = b.charAt(sizeB) - '0';
				sizeB--;
			}
			value = sumA + sumB + carry;
			sumA = 0;
			sumB = 0;
			if (value >= 2) {
				carry = 1;
				result.append(value - 2);
			} else {
				carry = 0;
				result.append(value);
			}
		}
		if (carry == 1) {
			result.append(carry);
		}
		return result.reverse().toString();
	}

	/**
	 * Leetcode problem number: lcno Problem Name: Add Binary status: green
	 */
	int convertToDecimal(int binary) {
		int result = 0;
		int index = 0;
		while (binary > 0) {
			result = (int) (result + ((binary % 10) * (Math.pow(2, index))));
			binary = binary / 10;
			index++;
		}
		return result;
	}

	/**
	 * Leetcode problem number: lcno Problem Name: Add Binary status: green
	 */
	String convertToBinary(int decimal) {
		if (decimal == 0) {
			return "0";
		}
		String result = "1";
		Stack<Integer> stack = new Stack<>();
		while (decimal != 1) {
			stack.push(decimal % 2);
			decimal = decimal / 2;
		}
		while (!stack.isEmpty()) {
			result = result + stack.pop();
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc33 Problem name: Search in Rotated Sorted
	 * Array status: red
	 */
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		return search(nums, 0, nums.length - 1, target);
	}

	/**
	 * Leetcode problem number: lc33 Problem name: Search in Rotated Sorted
	 * Array status: red
	 */
	int search(int[] nums, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		if (target == nums[mid]) {
			return mid;
		}
		if (nums[left] <= nums[mid]) {
			if (nums[left] <= target && target < nums[mid]) {
				return search(nums, left, mid - 1, target);
			} else {
				return search(nums, mid + 1, right, target);
			}
		} else {
			if (nums[mid] < target && target <= nums[right]) {
				return search(nums, mid + 1, right, target);
			} else {
				return search(nums, left, mid - 1, target);
			}
		}
	}

	/**
	 * Leetcode problem number: lc152 Problem Name: Maximum Product Subarray
	 * status: yellow
	 */
	public int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int minLocal = nums[0];
		int maxLocal = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int temp = maxLocal;
			maxLocal = Math.max(Math.max(maxLocal * nums[i], nums[i]), minLocal * nums[i]);
			minLocal = Math.min(Math.min(temp * nums[i], nums[i]), minLocal * nums[i]);
			max = Math.max(max, maxLocal);
		}
		return max;
	}

	/**
	 * Leetcode problem number: lc171 Problem Name: Excel Sheet Column Number
	 * status : yellow
	 */
	public int titleToNumber(String s) {
		int result = 0;
		int index = 0;
		if (s == null || s.length() == 0) {
			return 0;
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			int value = s.charAt(i) - 'A' + 1;
			result = (int) (result + Math.pow(26, index) * value);
			index++;
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc198 Problem Name: House Robber status: yellow
	 */
	public int rob(int[] nums) {
		int even = 0;
		int odd = 0;
		if (nums == null || nums.length == 0) {
			return 0;
		}
		for (int i = 0; i < nums.length; i++) {
			if (i % 2 == 0) {
				even = even + nums[i];
				even = even > odd ? even : odd;
			} else {
				odd = odd + nums[i];
				odd = even > odd ? even : odd;
			}
		}
		return even > odd ? even : odd;
	}

	/**
	 * Leetcode problem number: lc17 Problem Name: Letter Combinations of a
	 * Phone Number status: yellow
	 */
	public List<String> letterCombinations(String digits) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, "");
		ArrayList<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0)
			return result;

		ArrayList<Character> temp = new ArrayList<Character>();
		getString(digits, temp, result, map);
		return result;
	}

	/**
	 * Leetcode problem number: lc17 Problem Name: Letter Combinations of a
	 * Phone Number status: yellow
	 */
	public void getString(String digits, ArrayList<Character> temp, ArrayList<String> result,
			HashMap<Integer, String> map) {
		if (digits.length() == 0) {
			String strResult = "";
			for (char ch : temp) {
				strResult = strResult + ch;
			}
			result.add(strResult);
			return;
		}
		int number = Integer.parseInt(digits.substring(0, 1));
		String letters = map.get(number);
		for (int i = 0; i < letters.length(); i++) {
			temp.add(letters.charAt(i));
			getString(digits.substring(1), temp, result, map);
			temp.remove(temp.size() - 1);
		}
	}

	/**
	 * Leetcode problem number: lc12 Problem Name:Integer to Roman status:
	 * yellow
	 */
	public String intToRoman(int num) {
		StringBuilder result = new StringBuilder();
		String data[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int value[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		int base = -1;
		for (int i = 0; i < data.length; i++) {
			if (num / value[i] != 0) {
				base = num / value[i];
				while (base-- != 0) {
					result.append(data[i]);
				}
				num = num % value[i];
			}
		}
		return result.toString();
	}

	/**
	 * Leetcode problem number: lc242 Problem Name: Valid Anagram status : green
	 */
	public boolean isAnagram(String s, String t) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		if (s == null && t == null) {
			return false;
		}
		if (s.length() == 0 && t.length() == 0) {
			return true;
		}
		if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() != t.length()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (map.get(ch) == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, map.get(ch) + 1);
			}
		}
		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			if (map.get(ch) == null) {
				return false;
			} else {
				if (map.get(ch) == 1) {
					map.remove(ch);
				} else {
					map.put(ch, map.get(ch) - 1);
				}
			}
		}
		return map.isEmpty() ? true : false;
	}

	/**
	 * Leetcode problem number: lc46 Problem name: Permutations status: red
	 * methid: iteration
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		if (nums == null || nums.length == 0) {
			return result;
		}
		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> current = new ArrayList<List<Integer>>();
			for (List<Integer> l : result) {
				for (int j = 0; j < l.size() + 1; j++) {
					l.add(j, nums[i]);
					List<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);
					l.remove(j);
				}
			}
			result = new ArrayList<List<Integer>>(current);
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc46 Problem name: Permutations status: red
	 * methid: recursion
	 */
	public List<List<Integer>> permuteRec(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		makePermutations(nums, result, 0);
		return result;
	}

	/**
	 * Leetcode problem number: lc46 Problem name: Permutations status: red
	 * methid: recursion
	 */
	void makePermutations(int[] nums, List<List<Integer>> result, int start) {
		if (start >= nums.length) {
			List<Integer> temp = convertArrayToList(nums);
			result.add(temp);
		}
		for (int i = start; i < nums.length; i++) {
			swap(nums, start, i);
			makePermutations(nums, result, start + 1);
			swap(nums, start, i);
		}
	}

	/**
	 * Leetcode problem number: lc46 Problem name: Permutations status: red
	 * methid: recursion
	 */
	private ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}

	/**
	 * Leetcode problem number: lc46 Problem name: Permutations status: red
	 * methid: recursion
	 */
	private void swap(int[] nums, int start, int end) {
		int temp = nums[start];
		nums[start] = nums[end];
		nums[end] = temp;
	}

	/**
	 * Leetcode problem number: lc54 Problem Name:Spiral Matrix status: yellow
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> spiral = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}
		if (matrix.length == 1) {
			for (int i = 0; i < matrix[0].length; i++) {
				spiral.add(matrix[0][i]);
			}
			return spiral;
		}
		if (matrix[0].length == 1) {
			for (int i = 0; i < matrix.length; i++) {
				spiral.add(matrix[i][0]);
			}
			return spiral;
		}
		int rowStart = 0, coloumnStart = 0;
		int rowEnd = matrix.length - 1, coloumnEnd = matrix[0].length - 1;
		while (rowStart <= rowEnd && coloumnStart <= coloumnEnd) {
			for (int i = coloumnStart; i <= coloumnEnd; i++) {
				spiral.add(matrix[rowStart][i]);
			}
			rowStart++;
			for (int i = rowStart; i <= rowEnd; i++) {
				spiral.add(matrix[i][coloumnEnd]);
			}
			coloumnEnd--;
			for (int i = coloumnEnd; i >= coloumnStart; i--) {
				spiral.add(matrix[rowEnd][i]);
			}
			rowEnd--;
			for (int i = rowEnd; i >= rowStart; i--) {
				spiral.add(matrix[i][coloumnStart]);
			}
			coloumnStart++;
		}
		return spiral;
	}

	/**
	 * Leetcode problem number: lc219 Problem Name: Contains Duplicate II
	 * status: green
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (hashmap.containsKey(nums[i])) {
				if (hashmap.get(nums[i]) < i) {
					// System.out.println("First: " + hashmap.get(nums[i]) +
					// "\n" + i);
					if (i - hashmap.get(nums[i]) <= k)
						return true;
				} else {
					// System.out.println(hashmap.get(nums[i]) + "\n" + i);
					if (hashmap.get(nums[i]) - i <= k)
						return true;
				}
			}
			hashmap.put(nums[i], i);
		}
		return false;
	}

	/**
	 * Leetcode problem number: lc204 Problem Name: Count Primes status: red
	 */
	public int countPrimes(int n) {
		int count = 0;
		if (n <= 2) {
			return count;
		}
		boolean[] array = new boolean[n];
		for (int i = 1; i < array.length; i++) {
			array[i] = true;
		}
		for (int i = 2; i < Math.sqrt(n); i++) {
			int j = 2;
			int k = i * j;
			while (k < n) {
				if (array[k] == true) {
					array[k] = false;
				}
				j = j + 1;
				k = i * j;
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Leetcode problem number: lc36 Problem Name: Valid Sudoku status: yellow
	 */
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			boolean[] m = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					if (m[(int) (board[i][j] - '1')]) {
						return false;
					}
				} else {
					m[(int) (board[i][j] - '1')] = true;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			boolean[] m = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[j][i] != '.') {
					if (m[(int) (board[j][i] - '1')]) {
						return false;
					}
				} else {
					m[(int) (board[j][i] - '1')] = true;
				}
			}
		}
		for (int bord = 0; bord < 9; bord++) {
			boolean[] m = new boolean[9];
			for (int i = bord / 3 * 3; i < bord / 3 * 3 + 3; i++) {
				for (int j = bord % 3 * 3; j < bord % 3 + 3; j++) {
					if (board[i][j] != '.') {
						if (m[(int) (board[i][j] - '1')]) {
							return false;
						}
					} else {
						m[(int) (board[i][j] - '1')] = true;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc228 Problem Name: Summary Ranges statuis:
	 * yellow
	 */
	public List<String> summaryRanges(int[] nums) {
		List<String> result = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		if (nums.length == 1) {
			result.add("" + nums[0]);
		}
		int begin = nums[0];
		int previous = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == previous + 1) {
				if (i == nums.length - 1) {
					String strResult = nums[begin] + "->" + nums[i];
					result.add(strResult);
				}
			} else {
				if (nums[begin] == previous) {
					result.add(nums[begin] + "");
				} else {
					String strResult = nums[begin] + "->" + previous;
					result.add(strResult);
					begin = i;
				}
				if (i == nums.length - 1) {
					result.add(nums[i] + "");
				}
			}
			previous = nums[i];
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc238 Problem Name:Product of Array Except Self
	 * status: yellow
	 */
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length == 0 || nums.length == 1) {
			return nums;
		}
		int[] result = new int[nums.length];
		int left = 1;
		Arrays.fill(result, 1);
		for (int i = nums.length - 2; i >= 0; i--) {
			result[i] = nums[i + 1] * result[i + 1];
		}
		for (int i = 0; i < nums.length; i++) {
			result[i] = left * result[i];
			left = left * nums[i];
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc231 Problem Name: Power of Two status: yellow
	 */
	public boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		while (n > 2) {
			int leftShift = n >> 1;
			int rightShift = leftShift << 1;
			if (n - rightShift != 0) {
				return false;
			}
			n = n >> 1;
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc16 problem Name: 3Sum Closest status: green
	 */
	public int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			return -1;
		}
		int result = 0;
		int min = Integer.MAX_VALUE;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[left] + nums[i] + nums[right];
				if (sum == target) {
					return target;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
				int diff = Math.abs(sum - target);
				if (min > diff) {
					min = diff;
					result = sum;
				}
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc162 Problem Name: Find Peak Element status:
	 * green
	 */
	public int findPeakElement(int[] num) {
		if (num == null || num.length == 0 || num.length < 3) {
			return -1;
		}
		int prev = 0;
		int next = 2;
		int peak = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < num.length - 1; i++) {
			if (num[i] > num[prev] && num[i] > num[next]) {
				if (max < num[i]) {
					peak = i;
					max = num[i];
				}
			}
			prev = i;
			next = i + 2;
		}
		return peak;
	}

	/**
	 * Leetcode problem number: lc43 Problem Name:Multiply Strings status: red
	 */
	public String multiply(String num1, String num2) {
		if (num1 == null || num2 == null || num1 == "" || num2 == "") {
			return "";
		}
		String numB1 = new StringBuilder(num1).reverse().toString();
		String numB2 = new StringBuilder(num2).reverse().toString();
		StringBuilder result = new StringBuilder();
		int[] d = new int[numB1.length() + numB2.length()];
		for (int i = 0; i < numB1.length(); i++) {
			for (int j = 0; j < numB2.length(); j++) {
				d[i + j] += (numB1.charAt(i) - '0') * (numB2.charAt(j) - '0');
			}
		}
		for (int i = 0; i < d.length; i++) {
			int mod = d[i] % 10;
			int carry = d[i] / 10;
			if (i < d.length - 1) {
				d[i + 1] += carry;
			}
			result.insert(0, mod);
		}
		while (result.charAt(0) == '0' && result.length() > 1) {
			result.deleteCharAt(0);
		}
		return result.toString();
	}

	/**
	 * Leetcode problem number: lc118 Problem Name: Pascal's Triangle status:
	 * red
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (numRows <= 0) {
			return result;
		}
		List<Integer> prev = new ArrayList<Integer>();
		prev.add(1);
		result.add(prev);
		for (int i = 2; i <= numRows; i++) {
			int j = 1;
			List<Integer> current = new ArrayList<Integer>();
			current.add(1);
			while (j < prev.size()) {
				current.add(prev.get(j - 1) + prev.get(j));
				j++;
			}
			current.add(1);
			prev = current;
			result.add(current);
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc69 Problem Name: Sqrt(x) status: red
	 */
	public int mySqrt(int x) {
		if (x < 4)
			return x == 0 ? 0 : 1;
		int res = 2 * mySqrt(x / 4);
		if ((res + 1) * (res + 1) <= x && (res + 1) * (res + 1) >= 0)
			return res + 1;
		return res;
	}

	/**
	 * Leetcode problem number: lc134 Problem Name: Gas Station status: red
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int sumRemaining = 0;
		int remaining = 0;
		int total = 0;
		int start = 0;
		if (gas == null || cost == null) {
			return -1;
		}
		for (int i = 0; i < gas.length; i++) {
			remaining = gas[i] - cost[i];
			if (sumRemaining >= 0) {
				sumRemaining = sumRemaining + remaining;
			} else {
				sumRemaining = remaining;
				start = i;
			}
			total = total + sumRemaining;
		}
		if (total >= 0) {
			return start;
		} else {
			return -1;
		}
	}

	/**
	 * Leetcode problem number: lc75 Problem Name: Sort Colors status: green
	 */
	public void sortColors(int[] nums) {
		if (nums == null || nums.length < 3) {
			return;
		}
		int mid = 0, lo = 0, high = nums.length - 1;
		int temp = 0;
		while (mid <= high) {
			switch (nums[mid]) {
			case 0: {
				temp = nums[lo];
				nums[lo] = nums[mid];
				nums[mid] = temp;
				lo++;
				mid++;
				break;
			}
			case 1:
				mid++;
				break;
			case 2: {
				temp = nums[mid];
				nums[mid] = nums[high];
				nums[high] = temp;
				high--;
				break;
			}
			}
		}
	}

	/**
	 * Leetcode problem number: lc76 Problem Name: Minimum Window Substring
	 * status: red
	 */
	public String minWindow(String s, String t) {
		if (s == null || t == null || s.length() == 0 || t.length() == 0) {
			return "";
		}
		String result = s;
		int count = 0;
		int minLen = s.length();
		int start = 0;
		HashMap<Character, Integer> target = new HashMap<Character, Integer>();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			if (target.get(ch) != null) {
				target.put(ch, target.get(ch) + 1);
			} else {
				target.put(ch, 1);
			}
		}
		for (int j = 0; j < s.length(); j++) {
			char ch = s.charAt(j);
			if (target.get(ch) != null) {
				if (map.get(ch) != null) {
					if (map.get(ch) < target.get(ch)) {
						count++;
					}
					map.put(ch, map.get(ch) + 1);
				} else {
					map.put(ch, 1);
					count++;
				}
			}
			if (count == t.length()) {
				char chStart = s.charAt(start);
				while (!map.containsKey(chStart) || map.get(chStart) > target.get(chStart)) {
					if (map.get(chStart) != null && map.get(chStart) > target.get(chStart)) {
						map.put(chStart, map.get(chStart) - 1);
					}
					start++;
					chStart = s.charAt(start);
				}
				if (j - start + 1 < minLen) {
					minLen = j - start + 1;
					result = s.substring(start, j + 1);
				}
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc55 Problem name: Jump Game status: red
	 */
	public boolean canJump(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return true;
		}
		int max = nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (max <= i && nums[i] == 0) {
				return false;
			}
			max = Math.max(max, i + nums[i]);
			if (max >= nums.length - 1) {
				return true;
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc45 problem Name: jump game 2 status: red
	 */
	public int jump(int[] nums) {
		int steps = 0;
		int lastreach = 0;
		int max = 0;
		if (nums == null || nums.length <= 1) {
			return 0;
		}
		for (int i = 0; i < nums.length; i++) {
			if (max <= i && nums[i] == 0) {
				break;
			}
			if (i > lastreach) {
				steps++;
				lastreach = max;
			}
			max = Math.max(max, i + nums[i]);
		}
		if (max < nums.length - 1) {
			return -1;
		} else {
			return steps;
		}
	}

	/**
	 * Leetcode problem number: lc135 Problem Name: candy status: yellow
	 */
	public int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0) {
			return 0;
		}
		int[] left = new int[ratings.length];
		left[0] = 1;
		int result = 0;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i - 1] < ratings[i]) {
				left[i] = left[i - 1] + 1;
			} else {
				left[i] = 1;
			}
		}
		result = left[ratings.length - 1];
		for (int i = ratings.length - 2; i >= 0; i--) {
			int cur = 1;
			if (ratings[i] > ratings[i + 1]) {
				cur = left[i + 1] + 1;
			}

			result += Math.max(cur, left[i]);
			left[i] = cur;
		}
		return result;
	}

	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length < k) {
			return -1;
		}
		minHeap(nums, k);
		for (int i = k; i < nums.length; i++) {
			if (nums[0] < nums[i]) {
				nums[0] = nums[i];
				minHeap(nums, k);
			}
		}
		return nums[0];
	}

	/**
	 * Leetcode problem number: lc215 problem Name: Kth Largest Element in an
	 * Array status: red
	 */
	private void minHeap(int[] nums, int k) {
		int parent = k / 2;
		for (int i = parent; i >= 0; i--) {
			int leftchild = 2 * i + 1;
			int righchild = 2 * i + 2;
			int child = leftchild;
			if (righchild < k) {
				if (nums[righchild] < nums[leftchild]) {
					child = righchild;
				}
			}
			if (child < k && nums[child] < nums[i]) {
				int temp = nums[child];
				nums[child] = nums[i];
				nums[i] = temp;
			}
		}
	}

	/**
	 * Leetcode problem number: lc268 Problem name: Missing Number status: green
	 */
	public int missingNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int size = nums.length;
		int total = (size * (size + 1)) / 2;
		for (int i = 0; i < size; i++) {
			total = total - nums[i];
		}
		return total;
	}

	/**
	 * Leetcode problem number: lc263 Problem name: Ugly Number status: green
	 */
	public boolean isUgly(int num) {
		boolean flag = true;
		if (num == 2 || num == 3 || num == 5) {
			return true;
		}
		if (num == 1) {
			return false;
		}
		while (num > 1 && flag) {
			if (num % 5 == 0) {
				num = num / 5;
			} else if (num % 3 == 0) {
				num = num / 3;
			} else if (num % 2 == 0) {
				num = num / 2;
			} else {
				flag = false;
			}
		}
		return num == 1 ? true : false;
	}

	/**
	 * Leetcode problem number: lc205 Problem Name: Isomorphic Strings status:
	 * green
	 */
	public boolean isIsomorphic(String s, String t) {
		HashMap<Character, Character> map = new HashMap<>();
		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}
		if (s.length() == 0 && t.length() == 0) {
			return true;
		}
		for (int i = 0; i < s.length(); i++) {
			char chS = s.charAt(i);
			char chT = t.charAt(i);
			Character ch = getKey(map, chT);
			if (ch != null && ch != chS) {
				return false;
			} else if (map.containsKey(chS)) {
				if (map.get(chS) != chT) {
					return false;
				}
			} else {
				map.put(chS, chT);
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc205 Problem Name: Isomorphic Strings status:
	 * green
	 */
	private Character getKey(HashMap<Character, Character> map, char chT) {
		for (Map.Entry<Character, Character> entry : map.entrySet()) {
			if (entry.getValue().equals(chT)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Leetcode problem number: lc240 Prolem Name: Search a 2D Matrix II status:
	 * green
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		int rows = matrix.length;
		int coloumn = matrix[0].length;
		int startRow = rows - 1;
		int startColoumn = 0;
		while (startRow >= 0 && startColoumn < coloumn) {
			if (matrix[startRow][startColoumn] == target) {
				return true;
			} else if (matrix[startRow][startColoumn] < target) {
				startColoumn = startColoumn + 1;
			} else {
				startRow = startRow - 1;
			}
		}
		return false;
	}

	/**
	 * Leetcode problem number: lc78 ProblemName: Subsets status: yellow
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 1) {
			return result;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> temp = new ArrayList<List<Integer>>();
			for (List<Integer> list : result) {
				temp.add(new ArrayList<Integer>(list));
			}
			for (List<Integer> a : temp) {
				a.add(nums[i]);
			}
			List<Integer> single = new ArrayList<Integer>();
			single.add(nums[i]);
			temp.add(single);
			result.addAll(temp);
		}
		result.add(new ArrayList<Integer>());
		return result;
	}

	/**
	 * Leetcode problem number: lc31 Problem Name: Next Permutation status: red
	 */
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int position1 = -1;
		int position2 = -1;
		int begin = position1;
		int end = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				position1 = i;
				break;
			}
		}
		if (position1 != -1) {
			for (int i = nums.length - 1; i >= 0; i--) {
				if (nums[i] > nums[position1]) {
					position2 = i;
					break;
				}
			}
			swap(nums, position1, position2);
		}
		begin = position1 + 1;
		while (begin < end) {
			swap(nums, begin, end);
			begin++;
			end--;
		}
	}

	/**
	 * Leetcode problem number: lc80 Problem Name: Remove Duplicates from Sorted
	 * Array II status: green
	 */
	public int removeDuplicates2(int[] nums) {
		int count = 0;
		int length = 0;
		if (nums.length < 3) {
			return nums.length;
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] && count == 0) {
				nums[length] = nums[i - 1];
				length++;
				count = 1;
			} else if (nums[i] != nums[i - 1]) {
				nums[length] = nums[i - 1];
				count = 0;
				length++;
			}
		}
		return ++length;
	}

	/**
	 * Leetcode problem number: lc229 Problem Name: Majority Element II status:
	 * yellow
	 */
	public List<Integer> majorityElement2(int[] nums) {
		List<Integer> arrayList = new ArrayList<Integer>();
		int count1 = 0;
		int count2 = 0;
		Integer number1 = null;
		Integer number2 = null;
		if (nums == null || nums.length < 3) {
			return arrayList;
		}
		int size = nums.length;
		for (int i = 0; i < size; i++) {
			if (number1 != null && number1 == nums[i]) {
				count1++;
			} else if (number2 != null && number2 == nums[i]) {
				count2++;
			} else if (count1 == 0) {
				number1 = nums[i];
				count1 = 1;
			} else if (count2 == 0) {
				number2 = nums[i];
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = 0;
		count2 = 0;
		for (int i = 0; i < size; i++) {
			if (nums[i] == number1) {
				count1++;
			} else if (nums[i] == number2) {
				count2++;
			}
		}
		if (count1 > size / 3) {
			for (int i = 0; i < count1; i++) {
				arrayList.add(number1);
			}
		} else if (count2 > size / 3) {
			for (int i = 0; i < count2; i++) {
				arrayList.add(number2);
			}
		}
		return arrayList;
	}

	/**
	 * Leetcode problem number: lc299 Problem Name: Bulls and Cows status:
	 * yellow
	 */
	public String getHint(String secret, String guess) {
		int bulls = 0;
		int cows = 0;
		int[] array = new int[10];
		if (secret == null || guess == null || secret.length() == 0 || guess.length() == 0) {
			return "";
		}
		for (int i = 0; i < secret.length(); i++) {
			char chS = secret.charAt(i);
			char chG = guess.charAt(i);
			if (chS == chG) {
				bulls++;
			} else {
				if (array[chS - '0'] < 0) {
					cows++;
				}
				array[chS - '0']++;
				if (array[chG - '0'] > 0) {
					cows++;
				}
				array[chG - '0']--;
			}
		}
		return bulls + "A" + cows + "B";
	}

	/**
	 * Leetcode problem number: lc131 Problem Name: Palindrome Partitioning
	 * status: yellow
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (s == null || s.length() == 0) {
			return result;
		}
		List<String> temp = new ArrayList<>();
		partitionHelper(s, result, temp, 0);
		return result;
	}

	/**
	 * Leetcode problem number: lc131 Problem Name: Palindrome Partitioning
	 * status: yellow
	 */
	private void partitionHelper(String s, List<List<String>> result, List<String> temp, int start) {
		if (start > s.length() - 1) {
			List<String> current = new ArrayList<>(temp);
			result.add(current);
			return;
		}
		for (int i = start + 1; i <= s.length(); i++) {
			String str = s.substring(start, i);
			if (isPalindrome(str)) {
				temp.add(str);
				partitionHelper(s, result, temp, i);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/**
	 * Leetcode problem number: lc131 Problem Name: Palindrome Partitioning
	 * status: yellow
	 */
	private boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}

		return true;
	}

	/**
	 * Leetcode problem number: lc47 Problem Name: Permutations II status:
	 * yellow
	 */
	public List<List<Integer>> permuteUnique(int[] num) {
		List<List<Integer>> result = new ArrayList<>();
		permuteUnique(num, 0, result);
		return result;
	}

	/**
	 * Leetcode problem number: lc47 Problem Name: Permutations II status:
	 * yellow
	 */
	private void permuteUnique(int[] num, int start, List<List<Integer>> result) {

		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToListUnique(num);
			result.add(item);
		}
		for (int j = start; j <= num.length - 1; j++) {
			if (containsDuplicateUnique(num, start, j)) {
				swapUnique(num, start, j);
				permuteUnique(num, start + 1, result);
				swapUnique(num, start, j);
			}
		}
	}

	/**
	 * Leetcode problem number: lc47 Problem Name: Permutations II status:
	 * yellow
	 */
	private ArrayList<Integer> convertArrayToListUnique(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}

	/**
	 * Leetcode problem number: lc47 Problem Name: Permutations II status:
	 * yellow
	 */
	private boolean containsDuplicateUnique(int[] arr, int start, int end) {
		for (int i = start; i <= end - 1; i++) {
			if (arr[i] == arr[end]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc47 Problem Name: Permutations II status:
	 * yellow
	 */
	private void swapUnique(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/**
	 * Leetcode problem number: lc40 Problem Name: Combination Sum II status:
	 * yellow
	 */
	public List<List<Integer>> combinationSum2(int[] num, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (num == null || num.length == 0) {
			return result;
		}
		List<Integer> temp = new ArrayList<Integer>();
		combinationSum2Helper(num, temp, result, 0, target);
		HashSet<List<Integer>> set = new HashSet<>(result);
		result.clear();
		result.addAll(set);
		return result;
	}

	/**
	 * Leetcode problem number: lc40 Problem Name: Combination Sum II status:
	 * yellow
	 */
	private void combinationSum2Helper(int[] num, List<Integer> temp, List<List<Integer>> result, int start,
			int target) {
		if (target == 0) {
			List<Integer> current = new ArrayList<>(temp);
			result.add(current);
			return;
		}
		for (int i = start; i < num.length; i++) {
			if (num[i] > target) {
				continue;
			}
			temp.add(num[i]);
			combinationSum2Helper(num, temp, result, i + 1, target - num[i]);
			temp.remove(temp.size() - 1);
		}
	}

	/**
	 * Leetcode problem number: lc254 Problem Name: Factor Combinations status:
	 * yellow
	 */
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		getFactorsHelper(result, new ArrayList<Integer>(), n, 2);
		return result;
	}

	/**
	 * Leetcode problem number: lc254 Problem Name: Factor Combinations status:
	 * yellow
	 */
	private void getFactorsHelper(List<List<Integer>> result, List<Integer> temp, int n, int factor) {
		if (n <= 1) {
			List<Integer> current = new ArrayList<>(temp);
			result.add(current);
			return;
		}
		for (int i = factor; i <= n; i++) {
			int number = n % i;
			if (number == 0) {
				temp.add(i);
				getFactorsHelper(result, temp, n / i, i);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/**
	 * Leetcode problem number: lcno Problem Name:Maximum Size Subarray Sum Non
	 * Continuous Equals k status: yellow
	 */
	public int maxSubArrayLenNoLeetCode(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] longest = new int[1];
		List<Integer> temp = new ArrayList<Integer>();
		maxSubArrayLenHelperNoLeetCode(nums, k, 0, longest, temp);
		return longest[0];
	}

	/**
	 * Leetcode problem number: lc325 Problem Name:Maximum Size Subarray Sum
	 * Equals k status: yellow
	 */
	private void maxSubArrayLenHelperNoLeetCode(int[] nums, int k, int start, int[] longest, List<Integer> temp) {
		if (k == 0) {
			longest[0] = Math.max(longest[0], temp.size());
			return;
		}
		for (int i = start; i < nums.length; i++) {
			temp.add(nums[i]);
			maxSubArrayLenHelperNoLeetCode(nums, k - nums[i], i + 1, longest, temp);
			temp.remove(temp.size() - 1);
		}
	}

	/**
	 * Leetcode problem number: lc325 Problem Name: Maximum Size Subarray Sum
	 * Equals k status: yellow
	 */
	public int maxSubArrayLen(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int max = 0;
		if (nums == null || nums.length == 0) {
			return 0;
		}
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			if (sum == k) {
				max = i + 1;
			} else if (map.containsKey(sum - k)) {
				max = Math.max(max, i - map.get(sum - k));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return max;
	}

	/**
	 * Leetcode problem number: lc266 Problem Name: Palindrome Permutation
	 * status: yellow
	 */
	public boolean canPermutePalindrome(String s) {
		int oddValues = 0;
		HashMap<Character, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (hashMap.containsKey(ch)) {
				hashMap.put(ch, hashMap.get(ch) + 1);
			} else
				hashMap.put(ch, 1);
		}
		for (int value : hashMap.values()) {
			if (value % 2 != 0)
				oddValues++;
		}
		if (oddValues <= 1)
			return true;
		else
			return false;
	}

	/**
	 * Leetcode problem number: lc209 Problem Name: Minimum Size Subarray Sum
	 * status: yellow
	 */
	public int minSubArrayLen(int s, int[] nums) {
		int left = 0, right = 0;
		int sum = nums[left];
		int result = nums.length;
		if (nums == null || nums.length == 0) {
			return -1;
		}
		while (right < nums.length) {
			if (left == right) {
				if (sum >= s) {
					return 1;
				} else {
					right++;
				}
				if (right < nums.length) {
					sum = sum + nums[right];
				} else {
					return result;
				}
			} else {
				if (sum >= s) {
					result = Math.min(right - left + 1, result);
					sum = sum - nums[left];
					left++;
				} else {
					right++;
					if (right < nums.length) {
						sum = sum + nums[right];
					} else {
						if (left == 0) {
							return 0;
						}
						return result;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc293 Problem Name: Flip Game status: red
	 */
	public List<String> generatePossibleNextMoves(String s) {
		List<String> result = new ArrayList<>();
		for (int i = 1; i < s.length() - 1; i++) {
			if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
				result.add(s.substring(0, i - 1) + "--" + s.substring(i, s.length()));
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc294 Problem Name: Flip Game 2 status: red
	 */
	public boolean canWin(String s) {
		if (s == null || s.length() < 2) {
			return false;
		}

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.startsWith("++", i)) {
				String t = s.substring(0, i) + "--" + s.substring(i + 2);

				if (!canWin(t)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Leetcode problem number: lc249 ProblemName: Group Shifted Strings status:
	 * yellow
	 */
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		if (strings == null || strings.length == 0) {
			return result;
		}
		for (String str : strings) {
			String strResult = "";
			int offset = str.charAt(0) - 'a';
			for (int i = 0; i < str.length(); i++) {
				char ch = (char) (str.charAt(i) - offset);
				if (ch < 'a') {
					ch += 26;
				}
				strResult = strResult + ch;
			}
			if (!map.containsKey(strResult)) {
				List<String> indexList = new ArrayList<String>();
				map.put(strResult, indexList);
			}
			map.get(strResult).add(str);
		}
		for (List<String> list : map.values()) {
			Collections.sort(list);
			result.add(list);
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc167 Problem Name: Two Sum II - Input array is
	 * sorted status: green
	 */
	public int[] twoSum2(int[] numbers, int target) {
		if (numbers == null || numbers.length < 2) {
			return null;
		}
		int begin = 0;
		int end = numbers.length - 1;
		while (begin < end) {
			if (numbers[begin] + numbers[end] < target) {
				begin++;
			} else if (numbers[begin] + numbers[end] > target) {
				end--;
			} else {
				int[] result = { begin + 1, end + 1 };
				return result;
			}
		}
		return null;
	}

	/**
	 * Leetcode problem number: lc277 Problem Name:Find the Celebrity status:
	 * yellow
	 */
	private boolean knows(int a, int b) {
		if (a == 1) {
			return false;
		} else
			return true;
	}

	/**
	 * Leetcode problem number: lc277 Problem Name:Find the Celebrity status:
	 * yellow
	 */
	public int findCelebrity(int n) {
		Stack<Integer> stack = new Stack<Integer>();
		int member = -1;
		for (int i = 0; i < n; i++) {
			stack.push(i);
		}
		int guessMember1 = -1, guessMember2 = -1;
		while (stack.size() > 1) {
			guessMember1 = stack.pop();
			guessMember2 = stack.pop();
			if (knows(guessMember1, guessMember2)) {
				stack.push(guessMember2);
			} else {
				stack.push(guessMember1);
			}
		}
		member = stack.pop();
		for (int i = 0; i < n; i++) {
			if (member != i && (knows(member, i) || !knows(i, member))) {
				return -1;
			}
		}
		return member;
	}

	/**
	 * Leetcode problem number: lc252 Problem Name: Meeting Rooms status: red
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
		int[] start = new int[intervals.length];
		int[] end = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}
		Arrays.sort(start);
		Arrays.sort(end);
		for (int i = 1; i < intervals.length; i++) {
			if (start[i] < end[i - 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc253 Problem Name: Meeting Rooms 2 status: red
	 */
	public int minMeetingRooms(Interval[] intervals) {
		int rooms = 0;
		int endIter = 0;
		int[] start = new int[intervals.length];
		int[] end = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}
		Arrays.sort(start);
		Arrays.sort(end);
		for (int i = 1; i < intervals.length; i++) {
			if (start[i] < end[endIter]) {
				rooms++;
			} else
				endIter++;
		}
		return rooms;
	}

	/**
	 * Leetcode problem number: lc3 Problem name: Longest Substring Without
	 * Repeating Characters status: red
	 */
	public int lengthOfLongestSubstring(String s) {
		boolean[] flag = new boolean[256];
		int start = 0, result = 0;
		if (s.length() < 2) {
			return s.length();
		}
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (flag[ch]) {
				result = Math.max(result, i - start);
				for (int k = start; k < i; k++) {
					if (s.charAt(k) == ch) {
						start = k + 1;
						break;
					}
					flag[s.charAt(k)] = false;
				}
			} else {
				flag[ch] = true;
			}
		}
		return Math.max(result, s.length() - start);
	}

	/**
	 * Leetcode problem number: lc336 Problem Name: Palindrome Pairs status: red
	 */
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			String s = words[i];

			// if the word is a palindrome, get index of ""
			if (isPalindrome(s)) {
				if (map.containsKey("")) {
					if (map.get("") != i) {
						ArrayList<Integer> l = new ArrayList<Integer>();
						l.add(i);
						l.add(map.get(""));
						result.add(l);

						l = new ArrayList<Integer>();

						l.add(map.get(""));
						l.add(i);
						result.add(l);
					}

				}
			}

			// if the reversed word exists, it is a palindrome
			String reversed = new StringBuilder(s).reverse().toString();
			if (map.containsKey(reversed)) {
				if (map.get(reversed) != i) {
					ArrayList<Integer> l = new ArrayList<Integer>();
					l.add(i);
					l.add(map.get(reversed));
					result.add(l);
				}
			}

			for (int k = 1; k < s.length(); k++) {
				String left = s.substring(0, k);
				String right = s.substring(k);

				// if left part is palindrome, find reversed right part
				if (isPalindrome(left)) {
					String reversedRight = new StringBuilder(right).reverse().toString();
					if (map.containsKey(reversedRight)) {
						if (map.get(reversedRight) != i) {
							ArrayList<Integer> l = new ArrayList<Integer>();
							l.add(map.get(reversedRight));
							l.add(i);
							result.add(l);
						}
					}
				}

				// if right part is a palindrome, find reversed left part
				if (isPalindrome(right)) {
					String reversedLeft = new StringBuilder(left).reverse().toString();
					if (map.containsKey(reversedLeft)) {
						if (map.get(reversedLeft) != i) {

							ArrayList<Integer> l = new ArrayList<Integer>();
							l.add(i);
							l.add(map.get(reversedLeft));
							result.add(l);
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Leetcode problem number: lc11 Problem Name: Container With Most Water
	 * status: red
	 */
	public int maxArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int max = 0;
		int left = 0;
		int right = height.length - 1;
		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return max;
	}

	/**
	 * Leetcode problem number: lc74 Problem Name:Search a 2D Matrix status:
	 * yellow
	 */
	public boolean searchMatrixSimple(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		int row = matrix.length;
		int coloumn = matrix[0].length;
		int start = 0;
		int end = (row * coloumn) - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			int m = mid / coloumn;
			int n = mid % coloumn;
			if (matrix[m][n] == target) {
				return true;
			} else if (matrix[m][n] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	/**
	 * Leetcode problem number: lc48 Problem Name: Rotate Image status: red
	 */
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
	}

	/**
	 * Leetcode problem number: lc91 Problem Name: Decode Ways status: red
	 */
	public int numDecodings(String s) {
		if (s == null || s.length() == 0 || s.charAt(0) == '0')
			return 0;
		if (s.length() == 1)
			return 1;

		int[] dp = new int[s.length()];
		dp[0] = 1;
		if (Integer.parseInt(s.substring(0, 2)) > 26) {
			if (s.charAt(1) != '0') {
				dp[1] = 1;
			} else {
				dp[1] = 0;
			}
		} else {
			if (s.charAt(1) != '0') {
				dp[1] = 2;
			} else {
				dp[1] = 1;
			}
		}

		for (int i = 2; i < s.length(); i++) {
			if (s.charAt(i) != '0') {
				dp[i] += dp[i - 1];
			}

			int val = Integer.parseInt(s.substring(i - 1, i + 1));
			if (val <= 26 && val >= 10) {
				dp[i] += dp[i - 2];
			}
		}

		return dp[s.length() - 1];
	}

	/**
	 * Leetcode problem number: lc97 Problem Name: Interleaving String status:
	 * red
	 */
	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length())
			return false;
		visited = new HashSet<Integer>();
		return isInterleave(s1, 0, s2, 0, s3, 0);
	}

	/**
	 * Leetcode problem number: lc97 Problem Name: Interleaving String status:
	 * red
	 */
	private static boolean isInterleave(String s1, int i1, String s2, int i2, String s3, int i3) {
		int hash = i1 * s3.length() + i2;
		if (visited.contains(hash))
			return false;

		if (i1 == s1.length())
			return s2.substring(i2).equals(s3.substring(i3));
		if (i2 == s2.length())
			return s1.substring(i1).equals(s3.substring(i3));

		if (s3.charAt(i3) == s1.charAt(i1) && isInterleave(s1, i1 + 1, s2, i2, s3, i3 + 1)
				|| s3.charAt(i3) == s2.charAt(i2) && isInterleave(s1, i1, s2, i2 + 1, s3, i3 + 1))
			return true;

		visited.add(hash);
		return false;
	}

	/**
	 * Leetcode problem number: lc
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (nums == null || nums.length < 4)
			return result;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {
			if (i != 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1; j < nums.length - 2; j++) {
				if (j != i + 1 && nums[j] == nums[j - 1])
					continue;
				int k = j + 1;
				int l = nums.length - 1;
				while (k < l) {
					if (nums[i] + nums[j] + nums[k] + nums[l] < target) {
						k++;
					} else if (nums[i] + nums[j] + nums[k] + nums[l] > target) {
						l--;
					} else {
						List<Integer> t = new ArrayList<Integer>();
						t.add(nums[i]);
						t.add(nums[j]);
						t.add(nums[k]);
						t.add(nums[l]);
						result.add(t);

						k++;
						l--;

						while (k < l && nums[l] == nums[l + 1]) {
							l--;
						}

						while (k < l && nums[k] == nums[k - 1]) {
							k++;
						}
					}

				}
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc179 Problem Name: Largest Number status:
	 * yellow
	 */
	public String largestNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return "";
		}
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(strs, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String leftRight = o1 + o2;
				String rightLeft = o2 + o1;
				return -leftRight.compareTo(rightLeft);
			}

		});
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s);
		}
		while (sb.charAt(0) == '0' && sb.length() > 1) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Leetcode problem number: lc41 Problem Name: First Missing Positive
	 * status: yellow
	 */
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			while (nums[i] != i + 1) {
				if (nums[i] <= 0 || nums[i] >= n)
					break;

				if (nums[i] == nums[nums[i] - 1])
					break;

				int temp = nums[i];
				nums[i] = nums[temp - 1];
				nums[temp - 1] = temp;
			}
		}

		for (int i = 0; i < n; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}

		return n + 1;
	}

	/**
	 * ProblemName:Bulb Switcher Leetcode problem number: lc319
	 */
	public int bulbSwitch(int n) {
		return (int) Math.sqrt(n);
	}

	/**
	 * Leetcode problem number: lc123 Problem Name: Best Time to Buy and Sell
	 * Stock III status: yellow
	 */
	public int maxProfit3(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}

		// highest profit in 0 ... i
		int[] left = new int[prices.length];
		int[] right = new int[prices.length];

		// DP from left to right
		left[0] = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			left[i] = Math.max(left[i - 1], prices[i] - min);
		}

		// DP from right to left
		right[prices.length - 1] = 0;
		int max = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			right[i] = Math.max(right[i + 1], max - prices[i]);
		}

		int profit = 0;
		for (int i = 0; i < prices.length; i++) {
			profit = Math.max(profit, left[i] + right[i]);
		}

		return profit;
	}

	/**
	 * Leetcode problem number: lc365 Problem Name: Water and Jug Problem
	 * status: red
	 */
	public boolean canMeasureWater(int x, int y, int z) {
		if (x + y < z) {
			return false;
		}
		if (x == z || y == z || x + y == z)
			return true;
		return z % gcd(x, y) == 0;
	}

	int gcd(int x, int y) {
		while (y != 0) {
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}

	/**
	 * Leetcode problem number: lc213
	 * Problem Name: House Robber II
	 * status: yellow
	 */
	public int rob2(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		if (nums.length == 1)
			return nums[0];

		int max1 = robHelper(nums, 0, nums.length - 2);
		int max2 = robHelper(nums, 1, nums.length - 1);

		return Math.max(max1, max2);
	}

	public int robHelper(int[] nums, int i, int j) {
		if (i == j) {
			return nums[i];
		}

		int[] dp = new int[nums.length];
		dp[i] = nums[i];
		dp[i + 1] = Math.max(nums[i + 1], dp[i]);

		for (int k = i + 2; k <= j; k++) {
			dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
		}

		return dp[j];
	}

	public static void main(String[] args) {
		ArrayLC obj = new ArrayLC();
		int[] height = { 2, 3, 5, 3, 4, 3, 6, 3 };
		System.out.println(obj.maxArea(height));

		ArrayLC lc1 = new ArrayLC();
		int[] array1 = { 2, 3, -1, 3, 4 };
		lc1.twoSum(array1, 6);

		ArrayLC lc15 = new ArrayLC();
		int[] array15 = { -1, 0, 2, 1, -1, 4 };
		lc15.threeSum(array15);

		ArrayLC lc169 = new ArrayLC();
		int[] array169 = { 1, 2, 2, 1, 2 };
		System.out.println(lc169.majorityElement(array169));

		ArrayLC lc121 = new ArrayLC();
		int[] array121 = { 7, 1, 5, 3, 6, 4 };
		System.out.println(lc121.maxProfit(array121));

		ArrayLC lc122 = new ArrayLC();
		int[] array122 = { 7, 1, 5, 3, 6, 4 };
		System.out.println(lc122.maxProfit2(array122));

		ArrayLC lc53 = new ArrayLC();
		int[] array53 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(lc53.maxSubArray(array53));

		ArrayLC lc189 = new ArrayLC();
		int[] array189 = { 1, 2, 3, 4, 5, 6, 7 };
		lc189.rotate(array189, 3);

		ArrayLC lc153 = new ArrayLC();
		int[] array153 = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(lc153.findMin(array153));

		ArrayLC lc136 = new ArrayLC();
		int[] array136 = { 1, 2, 3, 3, 1 };
		System.out.println(lc136.singleNumber(array136));

		ArrayLC lc7 = new ArrayLC();
		System.out.println(lc7.reverse(123));

		ArrayLC lc5 = new ArrayLC();
		System.out.println(lc5.longestPalindrome("ahagahbbcbbi"));

		ArrayLC lc8 = new ArrayLC();
		System.out.println(lc8.myAtoi("-11111111"));

		ArrayLC lc191 = new ArrayLC();
		lc191.hammingWeight(3);

		ArrayLC lc13 = new ArrayLC();
		System.out.println(lc13.romanToInt("DCXXI"));

		ArrayLC lc20 = new ArrayLC();
		System.out.println(lc20.isValid("({)()"));

		ArrayLC lc14 = new ArrayLC();
		String[] str14 = { "abc", "ab", "abcc", "abv" };
		System.out.println(lc14.longestCommonPrefix(str14));

		ArrayLC lc9 = new ArrayLC();
		System.out.println(lc9.isPalindrome(121));

		ArrayLC lc38 = new ArrayLC();
		System.out.println(lc38.countAndSayDifferent(122));

		ArrayLC lc258 = new ArrayLC();
		System.out.println(lc258.addDigits(38));

		ArrayLC lc50 = new ArrayLC();
		System.out.println(lc50.myPow(5, 2));

		ArrayLC lc283 = new ArrayLC();
		int[] arr283 = { 0, 1, 0, 3, 12 };
		lc283.moveZeroes(arr283);

		ArrayLC lc27 = new ArrayLC();
		int[] arr27 = { 3, 2, 2, 3 };
		System.out.println(lc27.removeElement(arr27, 3));

		ArrayLC lc217 = new ArrayLC();
		int[] arr217 = { 3, 2, 2, 4 };
		System.out.println(lc217.containsDuplicate(arr217));

		ArrayLC lc26 = new ArrayLC();
		int[] arr26 = { 2, 2, 2, 2 };
		System.out.println(lc26.removeDuplicates(arr26));

		ArrayLC lc88 = new ArrayLC();
		int[] arr88_1 = { 1, 91, 100, 0, 0, 0 };
		int[] arr88_2 = { -2, 2, 4 };
		lc88.merge(arr88_1, arr88_1.length - arr88_2.length, arr88_2, arr88_2.length);

		ArrayLC lc150 = new ArrayLC();
		String[] token150_1 = { "2", "1", "+", "3", "*" };
		String[] token150_2 = { "4", "13", "5", "/", "+" };
		System.out.println(lc150.evalRPN(token150_2));

		ArrayLC lc42 = new ArrayLC();
		int[] arr42 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(lc42.trap(arr42));

		ArrayLC lc202 = new ArrayLC();
		System.out.println(lc202.isHappy(19));

		ArrayLC lc49 = new ArrayLC();
		String[] str49 = { "eat", "tea", "tan", "ate", "nat", "bat" };
		lc49.groupAnagrams(str49);

		ArrayLC lc67 = new ArrayLC();
		System.out.println(lc67.addBinary("11", "1"));

		ArrayLC lc33 = new ArrayLC();
		int[] arr33_1 = { 0, 1, 2, 4, 5, 6, 7 };
		int[] arr33_2 = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(lc33.search(arr33_2, 2));

		ArrayLC lc152 = new ArrayLC();
		int[] arr152_1 = { 2, 3, -2, 4 };
		int[] arr152_2 = { 2, 3, -2, 4, -3, -3 };
		System.out.println(lc152.maxProduct(arr152_2));

		ArrayLC lc171 = new ArrayLC();
		System.out.println(lc171.titleToNumber("BA"));

		ArrayLC lc198 = new ArrayLC();
		int[] arr198 = { 5, 1, 10, 100 };
		System.out.println(lc198.rob(arr198));

		ArrayLC lc17 = new ArrayLC();
		lc17.letterCombinations("23");

		ArrayLC lc242 = new ArrayLC();
		System.out.println(lc242.isAnagram("oow", "oow"));

		ArrayLC lc12 = new ArrayLC();
		System.out.println(lc12.intToRoman(11));

		ArrayLC lc46 = new ArrayLC();
		int[] arr46 = { 1, 2, 3 };
		lc46.permute(arr46);
		int[] arrrec46 = { 1, 2, 3 };
		lc46.permuteRec(arrrec46);

		ArrayLC lc54 = new ArrayLC();
		int[][] arr54 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		lc54.spiralOrder(arr54);

		ArrayLC lc204 = new ArrayLC();
		System.out.println(lc204.countPrimes(9));

		ArrayLC lc228 = new ArrayLC();
		int[] arr228 = { 0, 1, 2, 4, 5, 7 };
		lc228.summaryRanges(arr228);

		ArrayLC lc238 = new ArrayLC();
		int[] arr238 = { 1, 2, 3, 4 };
		lc238.productExceptSelf(arr238);

		ArrayLC lc231 = new ArrayLC();
		System.out.println(lc231.isPowerOfTwo(16));

		ArrayLC lc16 = new ArrayLC();
		int[] arr16 = { -1, 2, 1, -4 };
		System.out.println(lc16.threeSumClosest(arr16, 1));

		ArrayLC lc162 = new ArrayLC();
		int[] arr162 = { -1, 2, 1, -4, 3, 1 };
		System.out.println(lc162.findPeakElement(arr162));

		ArrayLC lc43 = new ArrayLC();
		System.out.println(lc43.multiply("11", "12"));

		ArrayLC lc118 = new ArrayLC();
		lc118.generate(5);

		ArrayLC lc134 = new ArrayLC();
		int[] gas134 = { 1, 2 };
		int[] cost134 = { 2, 1 };
		System.out.println(lc134.canCompleteCircuit(gas134, cost134));

		ArrayLC lc75 = new ArrayLC();
		int[] nums75 = { 0, 1, 0, 1, 2, 0, 1 };
		lc75.sortColors(nums75);

		ArrayLC lc76 = new ArrayLC();
		lc76.minWindow("ADOBECODEBANC", "ABC");

		ArrayLC lc55 = new ArrayLC();
		int[] nums55 = { 2, 3, 1, 1, 4 };
		System.out.println(lc55.canJump(nums55));

		ArrayLC lc45 = new ArrayLC();
		int[] nums45 = { 2, 3, 1, 1, 4 };
		System.out.println(lc45.jump(nums45));

		ArrayLC lc165 = new ArrayLC();
		int[] ratings65 = { 4, 2, 3, 4, 1 };
		System.out.println(lc165.candy(ratings65));

		ArrayLC lc215 = new ArrayLC();
		int[] arr215 = { 3, 2, 1, 6, 4, 5 };
		System.out.println(lc215.findKthLargest(arr215, 3));

		ArrayLC lc268 = new ArrayLC();
		int[] arr268 = { 0, 1, 3, 4, 5 };
		System.out.println(lc268.missingNumber(arr268));

		ArrayLC lc263 = new ArrayLC();
		System.out.println(lc263.isUgly(70));

		ArrayLC lc205 = new ArrayLC();
		System.out.println(lc205.isIsomorphic("ee", "ad"));

		ArrayLC lc240 = new ArrayLC();
		int[][] arr240 = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
				{ 18, 21, 23, 26, 30 } };
		System.out.println(lc240.searchMatrix(arr240, 5));
		System.out.println(lc240.searchMatrix(arr240, -1));

		ArrayLC lc78 = new ArrayLC();
		int[] arr78 = { 1, 3, 2 };
		lc78.subsets(arr78);

		ArrayLC lc31 = new ArrayLC();
		int[] arr31 = { 9, 8, 7, 9, 6, 2 };
		lc31.nextPermutation(arr31);

		ArrayLC lc80 = new ArrayLC();
		int[] arr80_1 = { 1, 2, 2, 2, 3, 3, 4, 4 };
		int[] arr80_2 = { 1, 2, 2, 2, 3, 3, 4 };
		System.out.println(lc80.removeDuplicates2(arr80_2));

		ArrayLC lc229 = new ArrayLC();
		int[] arr229 = { 1, 1, 2, 3, 3, 1, 3, 2, 1 };
		lc229.majorityElement2(arr229);

		ArrayLC lc299 = new ArrayLC();
		System.out.println(lc299.getHint("1123", "0111"));

		ArrayLC lc131 = new ArrayLC();
		lc131.partition("abcbe");

		ArrayLC lc47 = new ArrayLC();
		int[] arr47 = { 1, 1, 2 };
		lc47.permuteUnique(arr47);

		ArrayLC lc40 = new ArrayLC();
		int[] arr40 = { 10, 1, 2, 7, 6, 1, 5 };
		lc40.combinationSum2(arr40, 8);

		ArrayLC lc254 = new ArrayLC();
		lc254.getFactors(8);

		ArrayLC lcno = new ArrayLC();
		int[] arr = { -2, -1, 2, 1 };
		lcno.maxSubArrayLenNoLeetCode(arr, 1);

		ArrayLC lc325 = new ArrayLC();
		int[] arr325 = { 1, 0, -1 };
		System.out.println(lc325.maxSubArrayLen(arr325, -1));

		ArrayLC lc266 = new ArrayLC();
		System.out.println(lc266.canPermutePalindrome("acva"));

		ArrayLC lc209 = new ArrayLC();
		int[] arr209 = { 2, 3, 1, 2, 4, 3 };
		System.out.println(lc209.minSubArrayLen(7, arr209));

		ArrayLC lc293 = new ArrayLC();
		lc293.generatePossibleNextMoves("++++");

		ArrayLC lc294 = new ArrayLC();
		System.out.println(lc294.canWin("++++"));

		ArrayLC lc249 = new ArrayLC();
		String[] arr249 = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
		lc249.groupStrings(arr249);

		ArrayLC lc167 = new ArrayLC();
		int[] arr167 = { -2, -1, 1, 2 };
		lc167.twoSum2(arr167, 0);

		ArrayLC lc277 = new ArrayLC();
		System.out.println(lc277.findCelebrity(8));

		ArrayLC lc3 = new ArrayLC();
		System.out.println(lc3.lengthOfLongestSubstring("bb"));

		ArrayLC lc336 = new ArrayLC();
		String[] arr336 = { "abcd", "dcba", "lls", "s", "sssll" };
		lc336.palindromePairs(arr336);

		ArrayLC lc73 = new ArrayLC();
		int[][] arr73 = { { 1, 3, 5 }, { 7, 9, 10 }, { 11, 16, 20 } };
		System.out.println(lc73.searchMatrixSimple(arr73, 3));

		ArrayLC lc48 = new ArrayLC();
		int[][] arr48 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		lc48.rotate(arr48);

		ArrayLC lc91 = new ArrayLC();
		lc91.numDecodings("1234");

		ArrayLC lc97 = new ArrayLC();
		System.out.println(lc97.isInterleave("aabcc", "bbbca", "aaabbcbcac"));

		ArrayLC lc18 = new ArrayLC();
		int[] arr18 = { -3, -2, -1, 0, 0, 1, 2, 3 };
		System.out.println(lc18.fourSum(arr18, 0));

		ArrayLC lc179 = new ArrayLC();
		int[] nums179 = { 3, 30, 34, 5, 9 };
		lc179.largestNumber(nums179);

		ArrayLC lc319 = new ArrayLC();
		System.out.println(lc319.bulbSwitch(4));

		ArrayLC lc123 = new ArrayLC();
		int[] array123 = { 1, 4, 5, 7, 6, 3, 2, 9 };
		lc123.maxProfit3(array123);

		ArrayLC lc365 = new ArrayLC();
		System.out.println(lc365.canMeasureWater(48, 37, 3));
		
		ArrayLC lc213 = new ArrayLC();
		int [] arr213 = {1,2,3,4,1};
		lc213.rob2(arr213);
	}
}
