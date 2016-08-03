/**
 * 
 */
package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author Ojas Juneja
 *
 */
public class WordLC {

	private boolean[][] visited;
	private final String[] belowTen = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine" };
	private final String[] belowTwenty = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
			"Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	private final String[] belowHundred = new String[] { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
			"Seventy", "Eighty", "Ninety" };

	/**
	 * Leetcode problem number: lc151 Problem Name: Reverse Words in a String
	 * status: yellow
	 */
	public String reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		// split to words by space
		String[] arr = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; --i) {
			if (!arr[i].equals("")) {
				sb.append(arr[i]).append(" ");
			}
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}

	/**
	 * Leetcode problem number: lc139 Method Name: Word Break status: red
	 */
	public boolean wordBreak(String s, Set<String> wordDict) {
		if (s == null || s.length() == 0)
			return false;
		boolean[] check = new boolean[s.length() + 1];
		check[0] = true;
		for (int i = 0; i < s.length(); i++) {
			if (!check[i])
				continue;
			for (String str : wordDict) {
				int length = str.length();
				int end = i + length;
				if (end > s.length()) {
					continue;
				}
				if (check[end]) {
					continue;
				}
				if (s.substring(i, end).equals(str)) {
					check[end] = true;
				}
			}
		}
		return check[s.length()];
	}

	/**
	 * Leetcode problem number: lc79 ProblemName : Word Search status : red
	 * Method: Recursion
	 */
	public boolean exist(char[][] board, String word) {
		visited = new boolean[board.length][board[0].length];
		if (board == null || board.length == 0 || board[0].length == 0) {
			return false;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == word.charAt(0) && search(board, word, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Leetcode problem number: lc79 ProblemName : Word Search status : red
	 * Method: Recursion
	 */
	boolean search(char[][] board, String word, int rowNumber, int coloumnNumber, int index) {
		if (index == word.length())
			return true;
		if (rowNumber < 0 || rowNumber >= board.length || coloumnNumber < 0 || coloumnNumber >= board[0].length
				|| visited[rowNumber][coloumnNumber] || board[rowNumber][coloumnNumber] != word.charAt(index)) {
			return false;
		}
		visited[rowNumber][coloumnNumber] = true;
		if (search(board, word, rowNumber - 1, coloumnNumber, index + 1)
				|| search(board, word, rowNumber + 1, coloumnNumber, index + 1)
				|| search(board, word, rowNumber, coloumnNumber - 1, index + 1)
				|| search(board, word, rowNumber, coloumnNumber + 1, index + 1)) {
			return true;
		}
		visited[rowNumber][coloumnNumber] = false;
		return false;
	}

	/**
	 * Leetcode problem number: lc127 Problem Name:Word Ladder status: red
	 * method: iteration
	 */
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		if (beginWord == null || endWord == null || beginWord.equals(endWord)) {
			return 1;
		}
		int ladderLength = 1;
		Queue<String> q = new LinkedList<String>();
		Set<String> visitedWord = new HashSet<String>();
		q.add(beginWord);
		q.add(null);
		while (!q.isEmpty()) {
			String word = q.poll();
			if (word != null) {
				char[] wordArr = word.toCharArray();
				for (int i = 0; i < wordArr.length; i++) {
					for (char ch = 'a'; ch <= 'z'; ch++) {
						wordArr[i] = ch;
						String newWord = new String(wordArr);
						if (newWord.endsWith(endWord)) {
							return ladderLength + 1;
						}
						if (wordList.contains(newWord) && !visitedWord.contains(newWord)) {
							visitedWord.add(newWord);
							q.add(newWord);
						}
					}
				}
			} else {
				ladderLength++;
				if (!q.isEmpty()) {
					q.add(null);
				}
			}
		}
		return ladderLength;
	}

	/**
	 * Leetcode problem number: lc58 problem name: Length of Last Word status:
	 * yellow
	 */
	public int lengthOfLastWord(String s) {
		int length = 0;
		boolean flag = true;
		for (int i = s.length() - 1; i >= 0; i--) {
			int val = s.charAt(i);
			if (val != 32) {
				length++;
				flag = false;
			} else if (flag == false) {
				break;
			}
		}
		return length;
	}

	/**
	 * Leetcode problem number: lc290 Problem name: Word Pattern method:
	 * iteration status: green
	 */
	public boolean wordPattern(String pattern, String str) {
		if ((str == null && pattern == null) || (str.length() == 0 && pattern.length() == 0)) {
			return true;
		}
		if (str == null || pattern == null) {
			return false;
		}
		String[] array = str.split(" ");
		HashMap<Character, String> hashMap = new HashMap<Character, String>();
		if (pattern.length() != array.length) {
			return false;
		}
		for (int i = 0; i < pattern.length(); i++) {
			char ch = pattern.charAt(i);
			if (hashMap.containsKey(ch)) {
				if (!hashMap.get(ch).equals(array[i])) {
					return false;
				}
			} else {
				hashMap.put(ch, array[i]);
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc273 Problem Name: Integer to English Words
	 * status: red Method: Recursion
	 */
	public String numberToWords(int num) {
		if (num == 0)
			return "Zero";
		else {
			return helper(num);
		}
	}

	/**
	 * Leetcode problem number: lc273 Problem Name: Integer to English Words
	 * status: red Method: Recursion
	 */
	String helper(int number) {
		String result = "";
		if (number < 10) {
			result = belowTen[number];
		} else if (number < 20) {
			result = belowTwenty[number];
		} else if (number < 100) {
			result = belowHundred[number / 10] + " " + helper(number % 10);
		} else if (number < 1000) {
			result = helper(number / 100) + " Hundred " + helper(number % 100);
		} else if (number < 1000000) {
			result = helper(number / 1000) + " Thousand " + helper(number % 1000);
		} else if (number < 1000000000) {
			result = helper(number / 1000000) + " Million " + helper(number % 1000000);
		} else {
			result = helper(number / 1000000000) + " Billion " + helper(number % 1000000000);
		}
		return result.trim();
	}

	/**
	 * Leetcode problem number: lc318 Problem name: Maximum Product of Word
	 * Lengths status: yellow
	 */
	public int maxProduct(String[] words) {
		int result = 0;
		int size = words.length;
		if (words.length == 0 || size == 1) {
			return result;
		}
		int[] length = new int[size];
		int[] match = new int[size];
		for (int i = 0; i < size; i++) {
			String word = words[i];
			length[i] = word.length();
			for (int j = 0; j < word.length(); j++) {
				int value = word.charAt(j) - 'a';
				value = 1 << value;
				match[i] = match[i] | value;
			}
		}
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if ((match[i] & match[j]) == 0) {
					if (result < length[i] * length[j]) {
						result = length[i] * length[j];
					}
				}
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc186 ProblemName: Reverse Words in a String II
	 * status: green
	 */
	public void reverseWords(char[] s) {
		int begin = 0;
		int i = 0;
		if (s == null || s.length == 0 || s.length == 1) {
			return;
		}
		for (i = 0; i < s.length; i++) {
			if (s[i] == ' ') {
				reverseSubWords(s, begin, i - 1);
				begin = i + 1;
			}
		}
		reverseSubWords(s, begin, i - 1);
		reverseSubWords(s, 0, s.length - 1);
	}

	/**
	 * Leetcode problem number: lc186 ProblemName: Reverse Words in a String II
	 * status: green
	 */
	public void reverseSubWords(char[] s, int begin, int end) {
		while (begin < end) {
			char temp = s[begin];
			s[begin] = s[end];
			s[end] = temp;
			begin++;
			end--;
		}
	}

	/**
	 * Leetcode problem number: lc243 Problem Name: Shortest Word Distance
	 * status: red
	 */
	public int shortestDistance(String[] words, String word1, String word2) {
		int index = -1;
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1) || words[i].equals(word2)) {
				if (index != -1 && !words[i].equals(words[index])) {
					minDistance = Math.min(minDistance, i - index);
				}
				index = i;
			}
		}
		return minDistance;
	}

	/**
	 * Leetcode problem number: lc Problem Name: Shortest Word Distance with
	 * duplicates status: red
	 */
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int minDistance = Integer.MAX_VALUE;
		int i1 = minDistance, i2 = -minDistance;
		int index = 0;
		boolean same = word1.equals(word2);
		for (String word : words) {
			if (word.equals(word1)) {
				if (same) {
					i1 = i2;
					i2 = index;
				} else {
					i1 = index;
				}
			} else {
				i2 = index;
			}
			index++;
			minDistance = Math.min(minDistance, Math.abs(i1 - i2));
		}
		return minDistance;
	}
	
	/**
	 * Leetcode problem number: lc140
	 * Problem Name:  Word Break II
	 * status: red
	 * method: DP + backtracking
	 */
	 public List<String> wordBreakDP(String s, Set<String> wordDict){
		 List<String> result = new ArrayList<String>();
		 if(s == null)
		 {
			 return result;
		 }
		 int size = s.length();
		 List<String> [] dp = new ArrayList[size + 1];
		 dp[0] = new ArrayList<String>();
		 for(int i=0 ; i < size; i++)
		 {
			 for(String word: wordDict)
			 {
				 int end = i + word.length();
				 if(end > size)
				 {
					 continue;
				 }
				 if(s.substring(i, end).equals(word))
				 {
					 if(dp[end] == null)
					 {
						 dp[end] = new ArrayList<String>();
					 }
					 dp[end].add(word);
				 }
			 }
		 }
		 if(dp[size] == null)
		 {
			 return result;
		 }
		 backtracking( dp,result,size,new ArrayList<String>());
		 return result;
	 }
	 
	 void backtracking( List<String> [] dp,List<String> result,int end,List<String> temp)
	 {
		 if(end <= 0)
		 {
			 String strResult = temp.get(temp.size()-1);
			 for(int i = temp.size() - 2; i >= 0; i--)
			 {
				 strResult = strResult + " " + temp.get(i);
			 }
			 result.add(strResult);
		 }
		 for(String word: dp[end])
		 {
			 temp.add(word);
			 backtracking( dp,result,end - word.length(),temp);
			 temp.remove(temp.size()-1);
		 }
	 }

	public static void main(String[] args) {
		WordLC lc151 = new WordLC();
		System.out.println(lc151.reverseWords("Sky is Blue"));
		WordLC lc139 = new WordLC();
		Set<String> str139 = new HashSet<String>();
		str139.add("leet");
		str139.add("code");
		System.out.println(lc139.wordBreak("leet", str139));
		WordLC lc79 = new WordLC();
		char[][] char79 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(lc79.exist(char79, "SEE"));
		WordLC lc127 = new WordLC();
		Set<String> set127 = new HashSet<String>();
		set127.add("aat");
		set127.add("cot");
		set127.add("cog");
		System.out.println(lc127.ladderLength("cat", "cog", set127));
		WordLC lc58 = new WordLC();
		System.out.println(lc58.lengthOfLastWord("Hello World   "));
		WordLC lc290 = new WordLC();
		System.out.println(lc290.wordPattern("abccb", "yo mai hu hu mai"));
		WordLC lc273 = new WordLC();
		System.out.println(lc273.numberToWords(50868));
		WordLC lc318 = new WordLC();
		String[] str318 = { "abcw", "foo", "bar", "xtfn" };
		System.out.println(lc318.maxProduct(str318));
		WordLC lc186 = new WordLC();
		char[] char186 = { 't', 'h', 'e', ' ', 's', 'h', 'i', 'p', ' ', 's', 'i', 'n', 'k', 's' };
		lc186.reverseWords(char186);
		WordLC lc243 = new WordLC();
		String[] str243_1 = { "practice", "makes", "coding", "perfect", "makes" };
		String[] str243_2 = { "a", "b" };
		System.out.println(lc243.shortestDistance(str243_1, "makes", "makes"));
		
		Set<String> set140 = new HashSet<>();
		set140.add("cat");
		set140.add("cats");
		set140.add("and");
		set140.add("dog");
		set140.add("sand");
 		WordLC lc140 = new WordLC();
		lc140.wordBreakDP("catsanddog", set140);
	}
}
