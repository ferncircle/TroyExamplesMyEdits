package com.troy.array;

import java.util.Arrays;

/**
 * Date 12/29/2015
 * @author Tushar Roy
 *
 * Given an array of distinct integers and a sum value. Find count of triplets with sum smaller than 
 * given sum value. Expected Time Complexity is O(n2).

Examples:

Input : arr[] = {-2, 0, 1, 3}
        sum = 2.
Output : 2
Explanation :  Below are triplets with sum less than 2
               (-2, 0, 1) and (-2, 0, 3) 

Input : arr[] = {5, 1, 3, 4, 7}
        sum = 12.
Output : 4
sorted: {1, 3, 4, 5, 7}
Explanation :  Below are triplets with sum less than 4
               (1, 3, 4), (1, 3, 5), (1, 3, 7) and 
               (1, 4, 5)
               
Solution:
Note this is an extension of finding two elements in array that sum to given value when array is sorted
1) Sort the given array
2) Start from the beginning till end, i=0 to n-1
3) Treat current element, i,  as first element in triplet and for i+1 to n-1 find the rest of two elements using above 
approach

 *
 * http://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/
 */
public class TripletSumLessThanTotal {

    public int findAllTriplets(int input[], int total) {
        Arrays.sort(input);
        int result = 0;
        for (int i = 0; i < input.length - 2; i++) {
            int j = i + 1;
            int k = input.length - 1;

            while (j < k) {
                if (input[i] + input[j] + input[k] >= total) {
                    k--;
                } else {
                    result += k - j;
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int input[] = {5, 1, 3, 4, 7};
        TripletSumLessThanTotal tt = new TripletSumLessThanTotal();
        System.out.print(tt.findAllTriplets(input, 12));
    }
}
