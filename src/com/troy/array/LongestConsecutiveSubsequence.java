package com.troy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 12/03/2016
 * @author Tushar Roy
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Difficulty: Hard

Solution:
1) Keep a map where key=current element and value is the length of interval starting or ending at key
2) At each element, check if the previous and next element exists in map, if it does then merge these two intervals
	and update the values of relevant keys
 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * Reference
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSubsequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 1;
        for (int i : nums) {
            if (map.containsKey(i)) {
                continue;
            }
            int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
            int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;

            int sum = left + right + 1;
            map.put(i, sum);
            result = Math.max(sum, result);
            map.put(i - left, sum);
            map.put(i + right, sum);
        }
        return result;
    }

    public static void main(String args[]) {
        LongestConsecutiveSubsequence lcs = new LongestConsecutiveSubsequence();
        int[] input = {100, 4, 200, 1, 3, 2};
        System.out.println(lcs.longestConsecutive(input));
    }
}