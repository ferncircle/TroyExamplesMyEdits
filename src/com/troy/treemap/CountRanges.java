package com.troy.treemap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Date 03/03/2016
 * @author Tushar Roy
 *
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i <= j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

[1 2 3 4 -2]

[1 3 6 10 8]

 *
 * Time complexity O(nlogn)
 * Space complexity O(n)
 *
 * Performance can be improved by using self balancing BST 
 * 
 * Solution:
 * Note that problem becomes easier if we had to only find given sum (say 0). We just need to maintain Hashmap 
 * of all sumsSoFar.
 * Since there is a range, we'll have to look for specific range after considering current element. For that,
 * TreeMap will be better for range search
 * 
 * 1) create a TreeMap<sum, countOfSum> and sumSoFar
 * 2) At each value, update sumSoFar and check in treeMap for rangeSum
 * 3) Add value in TreeMap
 * 4) Maintain extra map of counts of sums, countOfSumsMap for improving performance
 *
 * https://leetcode.com/problems/count-of-range-sum/
 */
public class CountRanges {

    public int countRangeSum(int[] nums, int lower, int upper) {
        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        Map<Long, Integer> countOfSumsMap = new HashMap<>();
        treeMap.put(0l, 1);
        countOfSumsMap.put(0l, 1);
        long sumSoFar=0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sumSoFar = sumSoFar + nums[i];
            NavigableMap<Long, Integer> rangeSum =  treeMap.subMap(sumSoFar - upper, true, sumSoFar - lower, true);
            if (rangeSum.size() > 0) {
                for (int c : rangeSum.values()) {
                    count += c;
                }
            }
            countOfSumsMap.compute(sumSoFar, (k,v)->v==null?1:v+1);
            
            treeMap.put(sumSoFar, countOfSumsMap.get(sumSoFar));
        }
        return count;
    }

    public static void main(String args[]) {
        
        assertThat(new CountRanges().countRangeSum(new int[]{-2, 5, -1}, -2, 2), is(3));
        assertThat(new CountRanges().countRangeSum(new int[]{-2, 5, -1, 1, 2, 5, -3, 2}, -2, 2), is(10));

        
        System.out.println("All test cases passed");
    }

}

