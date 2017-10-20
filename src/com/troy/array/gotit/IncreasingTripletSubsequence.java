package com.troy.array.gotit;

/**
 * Date 03/06/2016
 * @author Tushar Roy
 *
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 <= i < j < k <= n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.
 
 10 5 9 4 8 3 7 2 8
Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

 * Similar method to longest increasing subsequence in nlogn time.
 *
 * Time complexity is O(n)
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int T[] = new int[3];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            boolean found = false;
            int cur=nums[i];
            for (int j = 0; j < len; j++) {
                if (T[j] >= cur) {
                    T[j] = cur;
                    found = true;
                    break;
                }
            }
            if (!found) {
                T[len++] = cur;
            }
            if (len == 3) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
        int input[] = {9, 10, 1, 9, 6, 5, -1};
        System.out.print(tripletSubsequence.increasingTriplet(input));
    }
}
