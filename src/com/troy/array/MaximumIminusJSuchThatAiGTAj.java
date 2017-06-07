package com.troy.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].

Examples:

  Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
  Output: 6  (j = 7, i = 1)

  Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
  Output: 8 ( j = 8, i = 0)

  Input:  {1, 2, 3, 4, 5, 6}
  Output: 5  (j = 5, i = 0)

  Input:  {6, 5, 4, 3, 2, 1}
  Output: -1 
  
  Solution: 
  This is a window problem BUT we need more info like smallest on left and greatest on right
  1) Create two additional arrays lhs and rhs
  2) lhs contains index of smallest element to the left of current element including current element
  3) rhs contains index of highest element to the right of current element including current element
  4) Consider window with i at begin and j at end
  5) expand window by incrementing j as long as value at rhs[j] is greater than lhs[i] otherwise shrink window by 
  incrementing i
  
  O(n) space, O(n) time
  
 */
public class MaximumIminusJSuchThatAiGTAj {
    
    public int maximumGeeks(int input[]){
        int lhs[] = new int[input.length];
        int rhs[] = new int[input.length];
        lhs[0] = 0;
        for(int i=1; i < lhs.length; i++){
            if(input[lhs[i-1]] < input[i]){
                lhs[i] = lhs[i-1];
            }else{
                lhs[i] = i;
            }
        }
        rhs[input.length-1] = input.length-1;
        for(int i=input.length-2; i >= 0; i--){
            if(input[rhs[i+1]] > input[i]){
                rhs[i] = rhs[i+1];
            }else{
                rhs[i] = i;
            }
        }
        
        int i=0;
        int j=0;
        int max = 0;
        for(;i<input.length && j < input.length;){
            if(input[lhs[i]] < input[rhs[j]]){
                max = Math.max(max, j-i);
                j++;
            }else{
                i++;
            }
        }
        return max;
    }
    
    public static void main(String args[]){
    	
    	assertThat(new MaximumIminusJSuchThatAiGTAj().maximumGeeks(new int[]{34, 8, 10, 35, 2, 80, 30, 33, 1}), is(6));
    	assertThat(new MaximumIminusJSuchThatAiGTAj().maximumGeeks(new int[]{9, 2, 3, 4, 5, 6, 7, 8, 18, 0}), is(8));

    	System.out.println("All test cases passed");
    }

}
