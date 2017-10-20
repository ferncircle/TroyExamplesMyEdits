package com.troy.array.gotit;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 03/01/2016
 * @author Tushar Roy
 *
 *You are given an integer array nums and you have to return a new counts array. 
 *The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

Difficulty: Hard
 *
 * Time complexity is O(nlogn)
 * Space complexity is O(n)
 *
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountSmallerOnRight {
    static class NumberIndex {
        int val;
        int index;
        NumberIndex(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        NumberIndex[] input = new NumberIndex[nums.length];
        for (int i = 0; i < nums.length; i++) {
            input[i] = new NumberIndex(nums[i], i);
        }

        int result[] = new int[nums.length];

        mergeUtil(input, result, 0, input.length - 1);

        List<Integer> r = new ArrayList<>();
        for (int s : result) {
            r.add(s);
        }
        return r;
    }

    private void mergeUtil(NumberIndex[] nums, int[] result, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = (low + high)/2;
        mergeUtil(nums, result, low, mid);
        mergeUtil(nums, result, mid + 1, high);

        int i = low;
        int j = mid + 1;
        NumberIndex[] t = new NumberIndex[high - low + 1];
        int k = 0;
        int tempResult[] = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (nums[i].val <= nums[j].val) {
                tempResult[nums[i].index - low] = j - mid - 1;
                t[k++] = nums[i++];
            } else {
                tempResult[nums[i].index - low] = j - mid;
                t[k++] = nums[j++];
            }
        }
        int i1= i;
        while (i1 <= mid) {
            tempResult[nums[i1].index - low] = j - mid - 1;
            t[k++] = nums[i1++];
        }

        while (j <= high) {
            t[k++] = nums[j++];
        }

        k = 0;
        for (i = low; i <= high; i++) {
            nums[i] = t[k];
            result[i] += tempResult[k++];
        }
    }

    public static void main(String args[]) {
        CountSmallerOnRight csr = new CountSmallerOnRight();
        int nums[] = {5, 2, 6, 1, 0, 3};
        List<Integer> result = csr.countSmaller(nums);
        result.forEach(r -> System.out.print(r + " "));
    }
}
