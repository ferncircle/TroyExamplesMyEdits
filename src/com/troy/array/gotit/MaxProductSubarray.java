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
		int product=nums[0],max=nums[0],min=nums[0];

		for(int i=1;i<nums.length;i++){
			int cur=nums[i];
			int temp=max;
			max=Math.max(Math.max(max*cur,min*cur),cur);
			min=Math.min(Math.min(temp*cur,min*cur),cur);

			product=Math.max(product,max);
		}

		return product;
	}

	public static void main(String args[]){
		MaxProductSubarray mps = new MaxProductSubarray();
		int input[] = {-6, -3, 8, -9, -1, -1, 3, 6, 9, 0, 3, -1};
		System.out.println(mps.maxProduct(input));
	}
}
