package com.troy.recursion.gotit;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 12/30/2015
 * @author Tushar Roy
 *
 * There are n-pairs and therefore 2n people. everyone has one unique number ranging from 1 to 2n. All these 2n 
 * persons are arranged in random fashion in an Array of size 2n. We are also given who is partner of whom. 
 * Find the minimum number of swaps required to arrange these pairs such that all pairs become adjacent to each other.

Example:

Input:
n = 3  
pairs[] = {1->3, 2->6, 4->5}  // 1 is partner of 3 and so on
arr[] = {3, 5, 6, 4, 1, 2}

Output: 2
We can get {3, 1, 5, 4, 6, 2} by swapping 5 & 1, and 6 & 5 

 *
 * Time complexity is O(2^n)
 *
 * http://www.geeksforgeeks.org/minimum-number-of-swaps-required-for-arranging-pairs-adjacent-to-each-other/
 * 
 * Solution:
 * We need to think about pairs at positions 0, 2 ,4...
 * 
 * 1) At each pair, we can either 
 * 		swap left partner and repeat for rest (call it state 0)
 * 		or right partner and repeat for rest (call it state 1)
 * 		and take the minimum of two calculations
 * 2) Make sure to revert(backtrack) the changes before moving to next state
 * 3) We will get total 8 states for 3 pairs (000, 001, 010 ...111) hence complexity 2^n
 * 4) Note that we don't need to permute all orders
 * 
 */
public class SetPairTogether {
	
    public int findMinimumSwaps(int input[], Map<Integer, Integer> pair) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            index.put(input[i], i);
        }
        int total= findMinimumSwapsUtil(input, pair, index, 0);
        return total;
    }

    public int findMinimumSwapsUtil(int input[], Map<Integer, Integer> pair, Map<Integer, Integer> index, int current) {
        if (current == input.length) {
            return 0;
        }

        int v1 = input[current];
        int v2 = input[current + 1];
        int pv2 = pair.get(v1);

        if(pv2 == v2) {
            return findMinimumSwapsUtil(input, pair, index, current + 2);
        } else {
            int idx1 = index.get(v1);
            int idx2 = index.get(v2);

            int idx3 = index.get(pair.get(v1));
            int idx4 = index.get(pair.get(v2));

            swap(index, input, idx2, idx3);
            int val1 = findMinimumSwapsUtil(input, pair, index, current + 2);
            swap(index, input, idx2, idx3);

            swap(index, input, idx1, idx4);
            int val2 = findMinimumSwapsUtil(input, pair, index, current + 2);
            /*System.out.println(Arrays.toString(input));*/
            swap(index, input, idx1, idx4);

            return 1 + Math.min(val1, val2);
        }
    }

    private void swap(Map<Integer, Integer> index, int input[], int i, int j) {
        index.compute(input[i], (k, v) -> j);
        index.compute(input[j], (k, v) -> i);

        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }

    public static void main(String args[]) {
        SetPairTogether spt = new SetPairTogether();
        int input[] = {3, 5, 6, 4, 1, 2};
        Map<Integer, Integer> pair = new HashMap<>();
        pair.put(1, 3);
        pair.put(3, 1);
        pair.put(2, 6);
        pair.put(6, 2);
        pair.put(4, 5);
        pair.put(5 ,4);
        System.out.println(spt.findMinimumSwaps(input, pair));
    }
    
    /*
     * The idea is to start from first and second elements and recur for remaining elements. Below are detailed steps/

1) If first and second elements are pair, then simply recur 
   for remaining n-1 pairs and return the value returned by 
   recursive call.

2) If first and second are NOT pair, then there are two ways to 
   arrange. So try both of them return the minimum of two.
   a) Swap second with pair of first and recur for n-1 elements. 
      Let the value returned by recursive call be 'a'.
   b) Revert the changes made by previous step.
   c) Swap first with pair of second and recur for n-1 elements. 
      Let the value returned by recursive call be 'b'.
   d) Revert the changes made by previous step before returning
      control to parent call.
   e) Return 1 + min(a, b)
     */
}
