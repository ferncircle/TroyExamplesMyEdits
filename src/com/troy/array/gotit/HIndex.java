package com.troy.array.gotit;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to 
 * compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have 
at least h citations each, and the other N - h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of 
them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 
citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Solution:
Note that answer for {0, 1, 1, 1, 1, 7, 6 ,8} is 3

1) The answer will only be between 0 to size of array. so, create additional array of same size
2) Count occurance of each value in new array e.g. arr[1]=4. If value is more than array size then just increment 
last element
3) Go through each element and check  if current index i, is the answer by subtracting total items before

Difficulty: Medium
 * https://leetcode.com/problems/h-index/
 */
public class HIndex {
    public int hIndex(int[] citations) {
        int[] count = new int[citations.length + 1];
        for (int c : citations) {
            if (c <= citations.length) {
                count[c]++;
            } else {
                count[citations.length]++;
            }
        }

        int sum = 0;
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            sum += count[i];
            //we are trying to see if i is answer.
            //already everything before i has less than i citations.
            //so only thing to check is that p >= i where p is
            //total number of papers with i or more citations.
            int p = citations.length - sum + count[i];
            if (i <= p) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String args[]) {
        HIndex hi = new HIndex();
        int[] input = {0, 1, 1, 1, 1, 7, 6 ,8};
        System.out.print(hi.hIndex(input));
    }
}

//0 1 2 6 6 6 6 7
//0 1 2 3 4 5 6 7
//0 1 1 1 3 6 7 8
//0 1 2 3 4 5 6 7

//0 1 2 5 6
