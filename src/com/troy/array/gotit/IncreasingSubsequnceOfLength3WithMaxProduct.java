package com.troy.array.gotit;

import java.util.TreeSet;

/**
 * http://www.geeksforgeeks.org/increasing-subsequence-of-length-three-with-maximum-product/
 * 
 * Given a sequence of non-negative integers, find the subsequence of length 3 having maximum product with the 
 * numbers of the subsequence being in ascending order. 

Examples:

 
Input: 
arr[] = {6, 7, 8, 1, 2, 3, 9, 10} 
Output: 
8 9 10

Input: 
arr[] = {1, 5, 10, 8, 9}
Output: 5 8 9


 *Solution:
 *
 *1) Note that at each element, we want 
 *	 a) greatest element on right side of it. 
 *   b) greatest element on left side of it smaller than current element
 *2) For case a, maintain an array that keeps track of largest element on right O(n)
 *3) For case b, create a TreeSet of elements, and for each element find largest element smaller than current element
 * 		using tree.lower(curlement) method
 * 
 * Note that related methods: 
 * tree.lower(curelement) 	returns greatest element lower than current element
 * tree.floor(curElement) 	returns greatest element lower or equal to current element
 * tree.ceiling(curElement) returns lowest elemenet greater or equal to curElement
 * tree.higher(curElement	returns lowest element greater than curElement
 *
 *
 * Test cases
 * Negative numbers
 * 0 in the input
 */
public class IncreasingSubsequnceOfLength3WithMaxProduct {

    public int maxProduct(int arr[]){
        int RGN[] = new int[arr.length];
        int LGN[] = new int[arr.length];
        RGN[arr.length-1] = arr[arr.length-1];
        int max = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--){
            if(max < arr[i]){
                max = arr[i];
            }
            if(max > arr[i]){
                RGN[i] = max;
            }
            else{
                RGN[i] = 0;
            }
        }
        LGN[0] = 0;
        TreeSet<Integer> tree=new TreeSet<Integer>();
        tree.add(arr[0]);
        for(int i=1; i < arr.length; i++){
        	getLGNTree(arr, i, LGN, tree);
        }
        int maxProduct = 0;
        for(int i=1; i < arr.length-1; i++){
            int product = arr[i]*LGN[i]*RGN[i];
            if(maxProduct < product){
                maxProduct = product;
            }
        }
        return maxProduct;
    }
    
    private void getLGNTree(int[] arr, int pos, int[] LGN, TreeSet<Integer> tree){
    	Integer lower=tree.lower(arr[pos]);
    	if(lower!=null){
    		LGN[pos]=lower;
    	}
    	tree.add(arr[pos]);
    }
    
    public static void main(String args[]){
        int arr[] = {6, 7, 8, 1, 2, 3, 9, 10};
        IncreasingSubsequnceOfLength3WithMaxProduct iss = new IncreasingSubsequnceOfLength3WithMaxProduct();
        System.out.println(iss.maxProduct(arr));
        int arr1[] = {1, 5, 10, 8, 9};
        System.out.println(iss.maxProduct(arr1));
    }
}
