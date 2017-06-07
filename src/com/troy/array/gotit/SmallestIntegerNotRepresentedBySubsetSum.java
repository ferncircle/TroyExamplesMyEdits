package com.troy.array.gotit;

/**
 * Date 12/31/2015
 * @author Tushar Roy
 *
 * Given a sorted array (sorted in non-decreasing order) of positive numbers, find the smallest positive integer 
 * value that cannot be represented as sum of elements of any subset of given set. 
Expected time complexity is O(n).

Examples:

Input:  arr[] = {1, 3, 6, 10, 11, 15};
Output: 2

Input:  arr[] = {1, 1, 1, 1};
Output: 5

Input:  arr[] = {1, 1, 3, 4};
Output: 10

Input:  arr[] = {1, 2, 5, 10, 20, 40};
Output: 4

Input:  arr[] = {1, 2, 3, 4, 5, 6};
Output: 22

 *
 * Time complexity is O(n)
 *
 * http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
 */
public class SmallestIntegerNotRepresentedBySubsetSum {

    public int findSmallestInteger(int input[]) {
        int result = 1;
        for (int i = 0; i < input.length; i++) {
            if (input[i] <= result) {
                result += input[i];
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Leetcode variation https://leetcode.com/problems/patching-array/
     */
    public int minPatches(int[] nums, int n) {
        int patch = 0;
        long t = 1;
        int i = 0;
        while(t <= n) {
            if (i == nums.length || t < nums[i]) {
                patch++;
                t += t;
            } else {
                t = nums[i] + t;
                i++;
            }
        }
        return patch;
    }


    public static void main(String args[]) {
        int input[] = {1, 2, 3, 8};
        SmallestIntegerNotRepresentedBySubsetSum ss = new SmallestIntegerNotRepresentedBySubsetSum();
        System.out.println(ss.findSmallestInteger(input));

        int input1[] = {1,2,31,33};
        System.out.println(ss.minPatches(input1, 2147483647));
    }
}
