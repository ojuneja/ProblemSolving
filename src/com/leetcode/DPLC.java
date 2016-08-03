/**
 * 
 */
package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;;

/*
leetcode problem number: lc
geeksforgeeks problem number: gk
other: 
*/
/**
 * @author Ojas Juneja
 *
 */
public class DPLC {

	/**
	 * Leetcode problem number: lc123 Problem Name: Best Time to Buy and Sell
	 * Stock III status: yellow
	 */
	public int maxProfit3(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int maximum = 0;
		int[] leftMax = new int[prices.length];
		int[] rightMax = new int[prices.length];
		leftMax[0] = 0;
		rightMax[prices.length - 1] = 0;
		int min = prices[0];
		int max = prices[prices.length - 1];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			leftMax[i] = Math.max(leftMax[i - 1], prices[i] - min);
		}
		for (int i = prices.length - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			rightMax[i] = Math.max(rightMax[i], max - prices[i]);
		}
		for (int i = 0; i < prices.length; i++) {
			maximum = Math.max(leftMax[i] + rightMax[i], maximum);
		}
		return maximum;
	}

	/**
	 * Leetcode problem number: lc70 Problem Name: Climbing Stairs status:
	 * yellow
	 */
	public int climbStairs(int n) {
		int oneStepBefore = 1;
		int twoStepBefore = 2;
		int totalSteps = 0;
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		for (int i = 2; i < n; i++) {
			totalSteps = oneStepBefore + twoStepBefore;
			oneStepBefore = twoStepBefore;
			twoStepBefore = totalSteps;
		}
		return totalSteps;
	}

	/**
	 * Leetcode problem number: lc64 Problem name: Minimum Path Sum status:
	 * green
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int rows = grid.length;
		int coloumns = grid[0].length;
		int[][] dp = new int[rows][coloumns];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < coloumns; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < rows; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < coloumns; j++) {
				if (dp[i - 1][j] > dp[i][j - 1]) {
					dp[i][j] = dp[i][j - 1] + grid[i][j];
				} else {
					dp[i][j] = dp[i - 1][j] + grid[i][j];
				}
			}
		}
		return dp[rows - 1][coloumns - 1];
	}

	/**
	 * Leetcode problem number: lc300 ProblemName: Longest Increasing
	 * Subsequence status: red
	 */
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}
		int size = nums.length, length = 0;
		int[] temp = new int[size];
		int[] track = new int[size];
		temp[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[temp[length]]) {
				length++;
				temp[length] = i;
				track[i] = temp[i - 1];
			} else if (nums[i] < nums[temp[0]]) {
				temp[0] = i;
			} else {
				int index = binarySearch(nums, temp, 0, i - 1, nums[i]);
				temp[index] = i;
				track[i] = temp[index - 1];
			}
		}
		return length;
	}

	/**
	 * Leetcode problem number: lc300 ProblemName: Longest Increasing
	 * Subsequence status: red
	 */
	private int binarySearch(int[] nums, int[] temp, int left, int right, int target) {
		while (left <= right) {
			int middle = (left + right) / 2;
			if (nums[temp[middle]] < target && nums[temp[middle + 1]] >= target) {
				return middle + 1;
			} else if (nums[temp[middle]] < target) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		DPLC lc123 = new DPLC();
		int[] array123 = { 1, 4, 5, 7, 6, 3, 2, 9 };
		System.out.println(lc123.maxProfit3(array123));

		DPLC lc70 = new DPLC();
		System.out.println(lc70.climbStairs(4));

		DPLC lc64 = new DPLC();
		int[][] nums64 = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(lc64.minPathSum(nums64));

		DPLC lc300 = new DPLC();
		int[] array300 = { 3, 4 ,- 1, 5, 8, 2, 3, 12, 7, 9, 10 };
		System.out.println(lc300.lengthOfLIS(array300));

	}
}
