package com.troy.array.gotit;

import java.util.Arrays;

/**
 * Date 12/30/2015
 *
 * Given an array of size n where all elements are in range from 0 to n-1, change contents of arr[] so that 
 * arr[i] = j is changed to arr[j] = i.

Examples:

Example 1:
Input: arr[]  = {1, 3, 0, 2};
Output: arr[] = {2, 0, 3, 1};
Explanation for the above output.
Since arr[0] is 1, arr[1] is changed to 0
Since arr[1] is 3, arr[3] is changed to 1
Since arr[2] is 0, arr[0] is changed to 2
Since arr[3] is 2, arr[2] is changed to 3

Example 2:
Input: arr[]  = {2, 0, 1, 4, 5, 3};
Output: arr[] = {1, 2, 0, 5, 3, 4};

Example 3:
Input: arr[]  = {0, 1, 2, 3};
Output: arr[] = {0, 1, 2, 3};

Example 4:
Input: arr[]  = {3, 2, 1, 0};
Output: arr[] = {3, 2, 1, 0};
 *
 * Time complexity O(n)
 * Space complexity O(1)
 * 
 * Solution:
 * 1) From every element follow the chain per requirement
 * 		similar to isolated connected components
 * 2) There might be a loop so we need to keep track of visited nodes: either we can use extra memory (hashset) or 
 * 		we can turn values to negative to keep track(we will have to handle 0 value, for that we'll just increment all 
 * values by 1)
 * 
 * Other Solution:
 * 1) Replace element by arrLength*curValue+newValue
 * 
 * 
 *
 * http://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/
 */
public class RearrangeArrayPerIndex {

    public void rearrange(int input[]) {

        for (int i = 0; i < input.length; i++) {
            input[i]++;
        }

        for (int i = 0; i < input.length; i++) {
            if (input[i] > 0) {
                rearrangeUtil(input, i);
            }
        }

        for (int i = 0; i < input.length; i++) {
            input[i] = -input[i] - 1;
        }
    }

    private void rearrangeUtil(int input[], int start) {
        int i = start + 1;
        int v = input[start];
        while (v > 0) {
            int t = input[v - 1];
            input[v - 1] = -i;
            i = v;
            v = t;
        }
    }

    public static void main(String args[]) {
        RearrangeArrayPerIndex rai = new RearrangeArrayPerIndex();
        int input[] = {1, 2, 0, 5, 3, 4};
        rai.rearrange(input);
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
    }

}
