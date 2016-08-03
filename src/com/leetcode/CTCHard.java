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
public class CTCHard {

	/**
	 * CTC 1
	 */
	public int add(int a, int b) {
		if (b == 0) {
			return a;
		}
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return add(sum, carry);
	}

	/**
	 * 
	 * CTC 2
	 */
	public void shuffleArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			int k = rand(0, i);
			int temp = arr[k];
			arr[k] = arr[i];
			arr[i] = temp;
		}
		System.out.println("done");
	}
	
	private int rand(int lower,int higher)
	{
		//int temp = (int)Math.random();
		return lower + (int)(Math.random() * (higher - lower + 1));
	}

	public static void main(String[] args) {
		CTCHard ctc1 = new CTCHard();
		System.out.println(ctc1.add(3, 7));

		CTCHard ctc2 = new CTCHard();
		int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		ctc2.shuffleArray(arr2);
	}
}
