/**
 * 
 */
package com.leetcode;

/**
 * @author Ojas Juneja
 *
 */
public class TwoDArrayLC {
	
	/**
	 * Leetcode problem number: lc200
	 * Problem Name: Number of Islands
	 * Status: yellow
	 * Type: Recursion
	 */
	public int numIslands(char[][] grid) {
		int counter = 0;
		if(grid == null || grid.length == 0 || grid[0].length == 0)
		{
			return 0;
		}
		for(int i = 0; i < grid.length; i++)
		{
			for(int j=0; j < grid[i].length; j++)
			{
				if(grid[i][j] == '1')
				{
					counter++;
					startRemovingOnes(grid,i,j);
				}
			}
		}
		return counter;
	}
	
	/**
	 * Leetcode problem number: lc200
	 * Problem Name: Number of Islands
	 * Status: yellow
	 * Type: Recursion
	 */
	public void startRemovingOnes(char [][] grid, int i,int j)
	{
		if(i < 0 || j < 0 || i > grid.length - 1 || j > grid.length - 1)
		{
			return;
		}
		if(grid[i][j] != '1') return;
		grid[i][j] = '0';
		startRemovingOnes(grid, i-1, j);
		startRemovingOnes(grid, i, j-1);
		startRemovingOnes(grid, i+1, j);
		startRemovingOnes(grid, i, j+1);
		//return;
	}
	
	
	
	public static void main(String [] args)
	{
		TwoDArrayLC lc200 = new TwoDArrayLC();
		char [][] char200 = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
		lc200.numIslands(char200);
	}
}
