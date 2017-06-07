package com.troy.array.gotit;
/**
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much 
 * water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Solution: 1) note the width as 1(not 0) and we have to find trapped ALL water
2) calculate how much water is there above each bar. For that calculate tallest bars on both sides and total
water contained in that contaier formed and then subtract the height of current bar.
 * References
 * https://oj.leetcode.com/problems/trapping-rain-water/
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingWater {

    public int trap(int[] height) {
       if(height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int left[] = new int[len];
        int right[] = new int[len];
        left[0] = height[0];
        right[len-1] = height[len -1];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(height[i], left[i-1]);
            right[len - i - 1] = Math.max(height[len- i - 1], right[len-i]);
        }

        int maxWaterTrapped = 0;
        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(left[i], right[i]);
            if (height[i] < min) {
                maxWaterTrapped += min - height[i];
            }
        }
        return maxWaterTrapped;
    }

    public static void main(String args[]){
        int input[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingWater tw = new TrappingWater();
        System.out.println(tw.trap(input));
    }
}
