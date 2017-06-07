package com.troy.array.gotit;

/**
 * Date 12/31/2015
 * @author Tushar Roy
 *
 *Given two sorted arrays such the arrays may have some common elements. Find the sum of the maximum sum 
 *path to reach from beginning of any array to end of any of the two arrays. We can switch from one array 
 *to another array only at common elements.

Expected time complexity is O(m+n) where m is the number of elements in ar1[] and n is the number of elements 
in ar2[].

Examples:

Input:  ar1[] = {2, 3, 7, 10, 12}, 
		ar2[] = {1, 5, 7, 8}
Output: 35
35 is sum of 1 + 5 + 7 + 10 + 12.
We start from first element of arr2 which is 1, then we
move to 5, then 7.  From 7, we switch to ar1 (7 is common)
and traverse 10 and 12.

Input:  ar1[] = {10, 12}, 
		ar2 = {5, 7, 9}
Output: 22
22 is sum of 10 and 12.
Since there is no common element, we need to take all 
elements from the array with more sum.

Input:  ar1[] = {2, 3, 7, 10, 12, 15, 30, 34}
        ar2[] = {1, 5, 7, 8, 10, 15, 16, 19}
Output: 122
122 is sum of 1, 5, 7, 8, 10, 12, 15, 30, 34

Solution:

Other solution with O(n) extra space but easier to understand:
1) Find out common elements indices
{0,2},{3,4}
{0,2},{3,5}
2) Start from 0 and take max between each range sum

Better solution with O(1) space but same idea
1) Create two variables, sum1 and sum2 and two pointers i and j for each
2) Keep adding sum at i and j until you find common element. At that time, choose either sum1 or sum2 for maxSum
3) Note that we can use PriorityQueue for this
 *
 * Time complexity is O(n + m)
 * Space complexity is O(1)
 *
 * http://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
 */
public class MaximumSumPathTwoArrays {

    public int maxSum(int input1[], int input2[]) {
        int maxSum = 0;
        int i = 0, j = 0;
        int sum1 = 0;
        int sum2 = 0;
        while (i < input1.length && j < input2.length) {
            if (input1[i] == input2[j]) {
                if (sum1 > sum2) {
                    maxSum += sum1 + input1[i];
                } else {
                    maxSum += sum2 + input2[j];
                }
                i++;
                j++;
                sum1 = 0;
                sum2 = 0;
            } else if (input1[i] < input2[j]) {
                sum1 += input1[i++];
            } else {
                sum2 += input2[j++];
            }
        }
        while(i < input1.length) {
            sum1 += input1[i++];
        }
        while(j < input2.length) {
            sum2 += input2[j++];
        }

        if (sum1 > sum2) {
            maxSum += sum1;
        } else {
            maxSum += sum2;
        }
        return maxSum;
    }

    public static void main(String args[]) {
        int input1[] = {2, 3, 7, 10, 12, 15, 30, 34};
        int input2[] = {1, 5, 7, 8, 10, 15, 16, 19};

        MaximumSumPathTwoArrays msp = new MaximumSumPathTwoArrays();
        System.out.println(msp.maxSum(input1, input2));
    }
}
