package com.troy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 12/29/2015
 * @author Tushar Roy
 *
 * Give two arrays of same size consisting of 0s and 1s find span (i, j) such that
 * sum of input1[i..j] = sum of input2[i..j]
 * Given two binary arrays arr1[] and arr2[] of same size n. Find length of the longest common span (i, j) 
 * where j >= i such that arr1[i] + arr1[i+1] + …. + arr1[j] = arr2[i] + arr2[i+1] + …. + arr2[j].

Expected time complexity is O(n).

Examples:

Input: arr1[] = {0, 1, 0, 0, 0, 0};
       arr2[] = {1, 0, 1, 0, 0, 1};
Output: 4
The longest span with same sum is from index 1 to 4.

Input: arr1[] = {0, 1, 0, 1, 1, 1, 1};
       arr2[] = {1, 1, 1, 1, 1, 0, 1};
Output: 6
The longest span with same sum is from index 1 to 6.

Input: arr1[] = {0, 0, 0};
       arr2[] = {1, 1, 1};
Output: 0

Input: arr1[] = {0, 0, 1, 0};
       arr2[] = {1, 1, 1, 1};
Output: 1

Solution:
This is similar to Longestsubarray with equal 0s and 1s
1) Calculate prefix sums of each array (we can consider 0 as -1 if want)
2) subtract prefix sums at each i and see if that value was already seen before (use maps for it)
Consider this:
Input: arr1[] = {0, 1, 0, 0, 0, 0};
       arr2[] = {1, 0, 1, 0, 0, 1};
       
     prefixSum1={-1,0,-1,-2,-3,-4}
     prefixSum2={1, 0, 1, 0, -1,0}
     Add	   ={-2, 0,-2,-2,-2,-4}

   OR 
{1, 0, 1, 0, 1, 0}--> prefixSum1={1,1,2,2,3,3}
{1, 1, 1, 1, 0, 1}--> prefixSum2={1,2,3,4,4,5}
diffMap={0->-1, -1->1, -2->3}

 *
 * Time complexity O(n)
 * Space complexity O(n)
 *
 * http://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/
 */
public class LongestSameSumSpan {

    public int longestSpan(int input1[], int input2[]) {
        if (input1.length != input2.length) {
            throw new IllegalArgumentException("Not same length input");
        }
        Map<Integer, Integer> diff = new HashMap<>();
        int prefix1 = 0, prefix2 = 0;
        int maxSpan = 0;
        diff.put(0, -1);
        for (int i = 0; i < input1.length ; i++) {
            prefix1 += input1[i];
            prefix2 += input2[i];
            int currDiff = prefix1 - prefix2;
            if (diff.containsKey(currDiff)) {
                maxSpan = Math.max(maxSpan, i - diff.get(currDiff));
            } else {
                diff.put(currDiff, i);
            }
        }
        return maxSpan;
    }

    public static void main(String args[]) {
        int input1[] = {1, 0, 0, 1, 1, 0};
        int input2[] = {0, 1, 1, 0, 1, 1};
        LongestSameSumSpan lsss = new LongestSameSumSpan();
        System.out.print(lsss.longestSpan(input1, input2));
    }

}
