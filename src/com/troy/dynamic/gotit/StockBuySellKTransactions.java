package com.troy.dynamic.gotit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 12/22/2015
 * @author Tushar Roy
 *
 * Given stocks prices for certain days and at most k transactions how to buy and sell
 * to maximize profit.
 *
 * Time complexity - O(number of transactions * number of days)
 * Space complexity - O(number of transcations * number of days)
 * 
 * Solution:
 * Easy one:
 * 1) Similar to number partition problem, Create matrix T[k][n]=max profit possible using k transactions and for n days
 * 2) At each cell we have two choices
 * 	  a) Current price is used as sell price for kth transaction. Find out best buy price for it from 0 to n-1th day
 * 		  currentprice(sell price) - price[m](buy price) + T[k-1][m] for m=0 to n-1
 * 		  Find max m;
 *    b) Current price is not used as sell price (no transaction done), T[k][n-1]
 * 3) T[k][n]=max of above two choices(a and b)
 * 
 * O(k*n^2)
 *
 * https://leetcode.com/discuss/15153/a-clean-dp-solution-which-generalizes-to-k-transactions
 */
public class StockBuySellKTransactions {
    /**
     * This is slow method but easier to understand.
     * Time complexity is O(k * number of days ^ 2)
     * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     */
    public int maxProfitSlowSolution(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices.length];

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j < T[0].length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, prices[j] - prices[m] + T[i-1][m]);
                }
                T[i][j] = Math.max(T[i][j-1], maxVal);
            }
        }
        printActualSolution(T, prices);
        return T[K][prices.length - 1];
    }
    

    /**
     * This is faster method which does optimization on slower method
     * Time complexity here is O(K * number of days)
     *
     * Formula is
     * T[i][j] = max(T[i][j-1], prices[j] + maxDiff)
     * maxDiff = max(maxDiff, T[i-1][j] - prices[j])
     */
    public int maxProfit(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices.length];

        for (int i = 1; i <= K; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = Math.max(T[i][j-1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, T[i-1][j] - prices[j]);
            }
        }
        printActualSolution(T, prices);
        return T[K][prices.length-1];
    }



    public void printActualSolution(int T[][], int prices[]) {
        int i = T.length - 1;
        int j = T[0].length - 1;

        Deque<Integer> stack = new LinkedList<>();
        while(true) {
            if(i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];
                for (int k = j-1; k >= 0; k--) {
                    if (T[i-1][k] - prices[k] == maxDiff) {
                        i = i - 1;
                        j = k;
                        stack.addFirst(j);
                        break;
                    }
                }
            }
        }

        while(!stack.isEmpty()) {
            System.out.println("Buy at price " + prices[stack.pollFirst()]);
            System.out.println("Sell at price " + prices[stack.pollFirst()]);
        }

    }

    public static void main(String args[]) {
        StockBuySellKTransactions sbt = new StockBuySellKTransactions();
        int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};

        //System.out.println("Max profit fast solution " + sbt.maxProfit(prices, 3));
        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 3));
    }
    
 // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions. 
    // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
    //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
    // f[0, ii] = 0; 0 times transation makes 0 profit
    // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
 
}
