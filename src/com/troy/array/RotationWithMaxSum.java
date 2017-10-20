package com.troy.array;

/**
 * Date 12/30/2015
 * @author Tushar Roy
 *
 *Given an array, only rotation operation is allowed on array. We can rotate the array as many times as we want.
 * Return the maximum possbile of summation of i*arr[i].

Example:

Input: arr[] = {1, 20, 2, 10}
1*0+20*1+2*2
2*0+1*1+20*2
20*0+2*1+1*2

Output: 72
We can 72 by rotating array twice.
{2, 10, 1, 20}
20*3 + 1*2 + 10*1 + 2*0 = 72

Input: arr[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
Output: 330
We can 330 by rotating array 9 times.
{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
0*1 + 1*2 + 2*3 ... 9*10 = 330

 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/
 * 
 * 
 * Solution:
 * Calculate 1) R0=0*a[0]+1*a[1].....
 * 2) R1= 0*a[n-1]+1*a[0]....
 * After subtracting,
 * R1-R0=arrSum-n*a[n-j] where j=1 for this case
 * 
 * Use this formula to get value of next rotation from current rotation. O(n)
 * 
 */
public class RotationWithMaxSum {
    int maxSum(int input[]) {
        int arrSum = 0;
        int rotationSum = 0;
        for (int i =0; i < input.length; i++) {
            arrSum += input[i];
            rotationSum += i*input[i];
        }

        int maxRotationSum = rotationSum;

        for (int i = 1; i < input.length; i++) {
            rotationSum += input.length*input[i - 1] - arrSum;
            maxRotationSum = Math.max(maxRotationSum, rotationSum);
        }
        return maxRotationSum;
    }

    public static void main(String args[]) {
        int input[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        RotationWithMaxSum rms = new RotationWithMaxSum();
        System.out.print(rms.maxSum(input));
    }
}
