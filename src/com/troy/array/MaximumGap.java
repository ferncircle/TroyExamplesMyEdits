package com.troy.array;

/**
 * Date 03/12/2016
 * @author Tushar Roy
 *
 *Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
e.g. 4, 3, 13, 2, 9, 7 --> 4
Difficulty: Hard
 * Time complexity O(n)
 * Space complexity O(n)
 *Difficulty: Hard
 *
 *Solution:
 *1) Find min and max and create buckets=(max-min)/arraySize
 *2) Each element put in respective bucket
 *3) Note that we only need to keep low and high for each bucket
 *
 * Reference
 * https://leetcode.com/problems/maximum-gap/
 */
public class MaximumGap {

    class Bucket {
        int low ;
        int high;
        boolean isSet = false;
        void update(int val) {
            if (!isSet) {
                low = val;
                high = val;
                isSet = true;
            } else {
                low = Math.min(low, val);
                high = Math.max(high, val);
            }
        }
		@Override
		public String toString() {
			return "Bucket [low=" + low + ", high=" + high + "]";
		}
    }

    public int maximumGap(int[] input) {
        if (input == null || input.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < input.length; i++) {
            min = Math.min(min, input[i]);
            max = Math.max(max, input[i]);
        }

        int gap = (int) Math.ceil((double) (max - min) / (input.length - 1));

        Bucket[] buckets = new Bucket[input.length];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        for (int i = 0; i < input.length; i++) {
        	int cur=input[i];
            /*if (cur == max || cur == min) {
                continue;
            }*/
            buckets[(cur - min) / gap].update(cur);
        }

        int prev = min;
        int maxGap = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isSet) {
                continue;
            }
            maxGap = Math.max(maxGap, buckets[i].low - prev);
            prev = buckets[i].high;
        }

        return Math.max(maxGap, max - prev);
    }

    public static void main(String args[]) {

    	System.out.println(new MaximumGap().maximumGap(new int[]{1,3,5,6,9}));
        MaximumGap mg = new MaximumGap();

        int[] input1 = {5, 2, 8, 20, 11, 15, 36, 100, 30, 3};
        System.out.println(mg.maximumGap(input1));
        
        int[] input = {4, 3, 13, 2, 9, 5, 6, 7, 20, 22};
        System.out.println(mg.maximumGap(input));
        
    }
}
