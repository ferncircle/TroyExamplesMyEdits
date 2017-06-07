package com.troy.dynamic.gotit;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * 
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. 
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if length 
 * of the rod 
 * is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22 
 * (by cutting in two pieces of lengths 2 and 6)


length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20
And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)

Note that after cutting at specific marking, you can slide the rod backwards(since the length reduces)

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20
 */
public class CuttingRod {

    public int maxValue(int price[]){
        int max[] = new int[price.length+1];
        for(int i=1; i <= price.length; i++){
            for(int j=i; j <= price.length; j++){
                max[j] = Math.max(max[j], max[j-i] + price[i-1]);
            }
        }
        return max[price.length];
    }
    
    public int maxValue1(int price[]){
        int max[] = new int[price.length+1];
        for(int i=1; i <= price.length; i++){
            max[i] = price[i-1];
        }
        for(int i=1 ; i <= price.length; i++){
            for(int j=1; j < i ; j++){
                max[i] = Math.max(max[i], max[i-j] + max[j]);
            }
        }
        return max[price.length];
    }
    
    public int recursiveMaxValue(int price[],int len){
        if(len <= 0){
            return 0;
        }
        int maxValue = 0;
        for(int i=0; i < len;i++){
            int val = price[i] + recursiveMaxValue(price, len-i-1);
            if(maxValue < val){
                maxValue = val;
            }
        }
        return maxValue;
    }
    public static void main(String args[]){
        CuttingRod cr =new CuttingRod();
        int[] price = {1,5,8,9,10,17,17,20};
        System.out.println(cr.maxValue1(price));
        long t1 = System.currentTimeMillis();
        int r = cr.recursiveMaxValue(price,8);
        long t2 = System.currentTimeMillis();
        System.out.println(r);
        System.out.println(t2 - t1);
    }
}
