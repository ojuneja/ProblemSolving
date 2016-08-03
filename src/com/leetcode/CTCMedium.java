/**
 * 
 */
package com.leetcode;

/*
leetcode problem number: lc
geeksforgeeks problem number: gk
other: 
*/
/**
 * @author Ojas Juneja
 *
 */
public class CTCMedium {

	/**
	 * Leetcode problem number: lc ProblemName: swapwithouttemp
	 */
	void swapTwo1(int a, int b) {
		b = a + b;
		a = b - a;
		b = b - a;
		System.out.println("a= " + a + " b=" + b);
	}

	/**
	 * Leetcode problem number: lc ProblemName: swapwithouttemp
	 */
	void swapTwo2(int a, int b) {
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("a= " + a + " b=" + b);
	}

	/**
	 * Leetcode problem number: lc ProblemName: tictactoe
	 */
	int hasWon(int[][] board) {
		int j = 0;
		int i = 0;
		if (board == null || board.length == 0) {
			return -1;
		}
		int rowSize = board.length;
		int colSize = board[0].length;
		for (i = 0; i < rowSize; i++) {
			if (board[i][0] != -1) {
				for (j = 1; j < colSize; j++) {
					if (board[i][j] != board[i][j - 1]) {
						break;
					}
				}
				if (j == colSize) {
					return board[i][0];
				}
			}
		}
		for (i = 0; i < rowSize; i++) {
			if (board[0][i] != -1) {
				for (j = 1; j < colSize; j++) {
					if (board[j][i] != board[j - 1][i]) {
						break;
					}
				}
				if (j == rowSize) {
					return board[0][i];
				}
			}
		}
		if (board[0][0] != -1) {
			for (i = 1; i < rowSize; i++) {
				if (board[i][i] != board[i - 1][i - 1]) {
					break;
				}
			}
			if (i == rowSize) {
				return board[0][0];
			}
		}
		if (board[rowSize - 1][0] != -1) {
			for (i = rowSize - 1; i > 0; i--) {
				if (board[rowSize - i - 1][i] != board[rowSize - i][i - 1]) {
					break;
				}
			}
			if (i == rowSize) {
				return board[rowSize - 1][0];
			}
		}
		return -1;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Trailing Zeroes
	 */
	public int trailingZeroes(int n) {
		int count = 0;
		for (int i = 2; i <= n; i++) {
			count = count + factorOf5(i);
		}
		return count;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Trailing Zeroes
	 */
	private int factorOf5(int n) {
		int factors = 0;
		while (n > 0) {
			if (n % 5 == 0) {
				factors++;
			}
			n = n / 5;
		}
		return factors;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Trailing Zeroes
	 */
	public int trailingZeroesBetter(int n) {
		int count = 0;
		for (int i = 5; n / i > 0; i *= 5) {
			count += n / i;
		}
		return count;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Sort Array
	 */
	void findUnsortedSequence(int[] array) {
		if (array == null || array.length == 1) {
			return;
		}
		int endLeft = findEndofLeftSubsequence(array);
		if (endLeft == array.length - 1) {
			return;
		}
		int startRight = findEndofRightSubsequence(array);
		int max = endLeft;
		int min = startRight;
		for (int i = endLeft + 1; i < startRight; i++) {
			if (array[i] < array[min]) {
				min = i;
			}
			if (array[i] > array[max]) {
				max = i;
			}
		}
		int leftIndex = shrinkLeft(array, min, endLeft);
		int rightIndex = shrinkRight(array, max, startRight);
		System.out.println(rightIndex - leftIndex);
	}

	/**
	 * Leetcode problem number: lc Problem Name: Sort Array
	 */
	private int findEndofLeftSubsequence(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				return i - 1;
			}
		}
		return array.length - 1;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Sort Array
	 */
	private int findEndofRightSubsequence(int[] array) {
		for (int i = array.length - 2; i >= 0; i--) {
			if (array[i + 1] < array[i]) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Sort Array
	 */
	private int shrinkLeft(int[] array, int min, int start) {
		int comp = array[min];
		for (int i = start - 1; i >= 0; i--) {
			if (array[i] <= comp) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Sort Array
	 */
	private int shrinkRight(int[] array, int max, int start) {
		int comp = array[max];
		for (int i = start; i < array.length; i++) {
			if (array[i] >= comp) {
				return i - 1;
			}
		}
		return array.length - 1;
	}

	public static void main(String[] args) {
		CTCMedium ctc1 = new CTCMedium();
		ctc1.swapTwo2(2, 3);

		CTCMedium ctc2 = new CTCMedium();
		System.out.println(ctc2.trailingZeroesBetter(10));

		CTCMedium ctc3 = new CTCMedium();
		int[] arr3 = { 1, 2, 4, 7, 11, 8, 12, 5, 6, 16, 18, 19 };
		ctc3.findUnsortedSequence(arr3);
	}
}
