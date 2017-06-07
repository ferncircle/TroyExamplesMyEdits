package com.troy.array.gotit;

/**
 * Date 04/`7/2016
 * @author Tushar Roy
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 *
 * Time complexity is O(n)
 * Space complexity is O(1)
 * 
 * Solution:
 * 1) Similar to Kadane algo
 * 2) Create two variables, min and max  and update them based on negative or positive current number
 *
 * http://www.geeksforgeeks.org/maximum-product-subarray/
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaxProductSubarray {

    public int maxProduct(int[] nums) {
        int min = 1;
        int max = 1;
        int maxSoFar = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = max * nums[i];
                min = Math.min(min * nums[i], 1);
                maxSoFar = Math.max(maxSoFar, max);
            } else if (nums[i] == 0) {
                min = 1;
                max = 1;
                maxSoFar = Math.max(maxSoFar, 0);
            } else {
                int t = max * nums[i];
                maxSoFar = Math.max(maxSoFar, min * nums[i]);
                max = Math.max(1, min*nums[i]);
                min = t;
            }
        }
        return maxSoFar;
    }
    
    public static void main(String args[]){
        MaxProductSubarray mps = new MaxProductSubarray();
        int input[] = {-6, -3, 8, -9, -1, -1, 3, 6, 9, 0, 3, -1};
        System.out.println(mps.maxProduct(input));
    }
}
