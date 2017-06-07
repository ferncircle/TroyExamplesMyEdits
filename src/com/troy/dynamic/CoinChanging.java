package com.troy.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 08/01/2014
 * @author Tushar Roy
 *
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} 
 * valued coins, how many ways can we make the change? The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
So output should be 4. For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} 
and {5,5}. 
So the output should be 5.*
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 * NOTE OBVIOUS LOGIC DOESN'T WORK (but subset logic works)
 * Solution:
 * 1) create a recursive solution with logic, any coin can be there in final set or not (creating two forks)
 * 2) Sol(m, n)=Sol(m, n-a[i]) (coin m is there in this set, NOTE that we don't decrement m here since repetition allowed) 
 * 				+ Sol(m-1, n) (coin m is not there in this set)
 */
public class CoinChanging {

	public int totalCoinsRecursive(int[] coins, int sum){

		return totalCoinsRecursiveUtil(coins, coins.length-1, sum);
	}

	public int totalCoinsRecursiveUtil(int coins[], int m, int sum){
		if(sum==0)
			return 1;

		if((m<0  && sum>=1) || sum<0)
			return 0;

		return 
				totalCoinsRecursiveUtil(coins, m, sum-coins[m]) //include coin m in subset
				+ totalCoinsRecursiveUtil(coins, m-1, sum);	//don't include coin m in subset 
	}

	public int numberOfSolutions(int total, int coins[]){
		int temp[][] = new int[coins.length+1][total+1];
		for(int i=0; i <= coins.length; i++){
			temp[i][0] = 1;
		}
		for(int i=1; i <= coins.length; i++){
			for(int j=1; j <= total ; j++){
				if(coins[i-1] > j){
					temp[i][j] = temp[i-1][j];
				}
				else{
					temp[i][j] = temp[i][j-coins[i-1]] + temp[i-1][j];
				}
			}
		}
		return temp[coins.length][total];
	}

	/**
	 * Space efficient DP solution(Harder to understand)
	 */
	public int numberOfSolutionsOnSpace(int total, int arr[]){

		int temp[] = new int[total+1];

		temp[0] = 1;
		for(int i=0; i < arr.length; i++){
			for(int j=1; j <= total ; j++){        	
				if(j >= arr[i]){
					temp[j] += temp[j-arr[i]];
				}
			}
			System.out.println(Arrays.toString(temp));
		}
		return temp[total];
	}


	/**
	 * This method actually prints all the combination. It takes exponential time.
	 */
	public void printCoinChangingSolution(int total,int coins[]){
		List<Integer> result = new ArrayList<>();
		printActualSolution(result, total, coins, 0);
	}

	private void printActualSolution(List<Integer> result,int total,int coins[],int pos){
		if(total == 0){
			for(int r : result){
				System.out.print(r + " ");
			}
			System.out.print("\n");
		}
		for(int i=pos; i < coins.length; i++){
			if(total >= coins[i]){
				result.add(coins[i]);
				printActualSolution(result,total-coins[i],coins,i);
				result.remove(result.size()-1);
			}
		}
	}

	public static void main(String args[]){
		CoinChanging cc = new CoinChanging();
		int total = 20;
		int coins[] = {2,3};
		System.out.println(cc.numberOfSolutions(total, coins));
		System.out.println(cc.numberOfSolutionsOnSpace(total, coins));
		System.out.println(cc.totalCoinsRecursive(coins, total));
		//cc.printCoinChangingSolution(total, coins);
	}
}
