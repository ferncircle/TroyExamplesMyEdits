package com.troy.dynamic.gotit;

/**
 * http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
 * 
 * Given a positive integer N, count all possible distinct binary strings of length N such that there are no 
 * consecutive 1’s.
 * 

Examples:

Input:  N = 2
Output: 3
// The 3 strings are 00, 01, 10

Input: N = 3
Output: 5
// The 5 strings are 000, 001, 010, 100, 101
 * 
 * 
 * Solution:
 * 1) if a string ends at 0, we can either append 0 or 1 (giving two forks in recursion)
 * 2) if a string ends at 1, we can only append 0 to it
 * 3) let functionA calculate number of strings ending at 0
 * 4) let functionB calculate number of strings ending at 1
 * 5) functionA(n)=functionA(n-1) + functionB(n-1)
 * 	  functionB(n)=functionA(n-1)
 *    base conditions: functionA(0)=1 and functionB(0)=1
 *    
 * It is really a straight up fibonacci series with values
 * 1,2,3,5,8,13....
 * Look how we assign a[i] value of a[i-1] + b[i-1] and then b[i] becomes a[i]
 */
public class CountNumberOfBinaryWithoutConsecutive1s {

    public int count(int n){
        int a[] = new int[n];
        int b[] = new int[n];
        
        a[0] = 1;
        b[0] = 1;
        
        for(int i=1; i < n; i++){
            a[i] = a[i-1] + b[i-1];
            b[i] = a[i-1];
        }
        
        return a[n-1] + b[n-1];
    }
    
    public int countSimple(int n){
        int a = 1;
        int b = 1;
        
        for(int i=1; i < n; i++){
            int tmp = a;
        	a = a + b;
            b = tmp;
        }
        
        return a + b;
    }
    
    public int countDP(int n){
    	int[][] dp=new int[n+1][2];
    	
    	for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if(i==0){
					dp[i][j]=1;
					continue;
				}
				if(j==0)
					dp[i][j]=dp[i-1][0]+dp[i-1][1];
				
				if(j==1)
					dp[i][j]=dp[i-1][0];
			}
			//System.out.println(Arrays.toString(dp[i]));
		}
    	
    	return dp[n][0];
    }
    
    public static void main(String args[]){
        CountNumberOfBinaryWithoutConsecutive1s cnb = new CountNumberOfBinaryWithoutConsecutive1s();
        System.out.println(cnb.count(10));
        System.out.println(cnb.countDP(10));
    }
}
