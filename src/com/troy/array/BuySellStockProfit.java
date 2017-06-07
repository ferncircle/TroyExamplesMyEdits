package com.troy.array;

/**
 * Date 03/04/2016
 * @author Tushar Roy
 *
 * Best time to buy and sell stocks.
 * 1) Only 1 transaction is allowed
 * 2) Infinite number transactions are allowed
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BuySellStockProfit {

    public int oneProfit(int arr[]){
        int minPrice = arr[0];
        int maxProfit = 0;
        for(int i=1; i < arr.length; i++){
            if(arr[i] - minPrice > maxProfit){
                maxProfit = arr[i] - minPrice;
            }
            if(arr[i] < minPrice){
                minPrice = arr[i];
            }
        }
        return maxProfit;
    }
        
    public int allTimeProfit(int arr[]){
        int profit = 0;
        for(int i=1; i < arr.length;i++){
            if(arr[i-1] < arr[i]){
                profit += arr[i] - arr[i-1];
            }
        }
        return profit;
    }
    
    public int allTimeProfit1(int arr[]){
        int profit = 0;
        int[] buy=new int[arr.length+1];
        int[] sell=new int[arr.length+1];
        buy[0]=Integer.MIN_VALUE;
        for (int i = 1; i < buy.length; i++) {
        	int cur=arr[i-1];
			buy[i]=Math.max(buy[i-1], sell[i-1]-cur);
			sell[i]=Math.max(sell[i], cur+buy[i]);
		}
        
        profit=Math.max(buy[arr.length], sell[arr.length]);
        return profit;
    }
    
    public int allTimeProfitSpace(int arr[]){
        int profit = 0;
        int buyProfit=Integer.MIN_VALUE;
        int sellProfit=0;
        for (int i = 0; i < arr.length; i++) {
        	int cur=arr[i];
        	buyProfit=Math.max(buyProfit, sellProfit-cur);
        	sellProfit=Math.max(sellProfit, cur+buyProfit);
		}
        
        profit=Math.max(buyProfit, sellProfit);
        return profit;
    }
    
    public static void main(String args[]){
        int arr[] = {7,10,15,5,11,2,7,9,3};
        int arr1[] = {1,2};
        BuySellStockProfit bss = new BuySellStockProfit();
        System.out.println(bss.oneProfit(arr));
        System.out.println(bss.allTimeProfit(arr1));
        System.out.println(bss.allTimeProfit1(arr1));

        System.out.println(bss.allTimeProfitSpace(arr1));
        
    }
}
