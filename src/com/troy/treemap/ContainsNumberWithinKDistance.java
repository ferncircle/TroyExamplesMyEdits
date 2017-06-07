package com.troy.treemap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Date 10/26/2016
 * @author Tushar Roy
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the 
 * absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j 
 * is at most k.
 * 
 *
 * Solution
 * Keep a tree map of num and its count. For every new number check if any number exists in map between
 * num - t and num + t. If yes than return true. If no then add this number to the tree map. Also if tree map
 * becomes of size k then drop the num[i - k] number from the tree map.
 *
 * Time complexity O(nlogk)
 *
 * https://leetcode.com/problems/contains-duplicate-iii/
 */
public class ContainsNumberWithinKDistance {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length == 0 || k == 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int lowerEntry = nums[i] - t - 1;
            int higherEntry = nums[i] + t + 1;
            Map.Entry<Integer, Integer> higher = map.lowerEntry(higherEntry);
            if (higher != null && higher.getKey() >= nums[i])
                return true;
            
            Map.Entry<Integer, Integer> lower = map.higherEntry(lowerEntry);
            if (lower != null && lower.getKey() <= nums[i])
                return true;
            
            if (map.size() == k) 
                map.compute(nums[i - k], (key, val) -> (val == 1)? null:  val - 1); //delete or reduce count of entry just before window on left
            
            map.compute(nums[i], (key, val) -> (val == null)? 1:val + 1); //add or increment an entry on right side of window
            
        }
        return false;
    }
}
