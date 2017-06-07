package com.troy.array.gotit;

/**
 * http://www.geeksforgeeks.org/rearrange-given-array-place/
 * Given an array arr[] of size n where every element is in range from 0 to n-1. Rearrange the given array so 
 * that arr[i] 
 * becomes arr[arr[i]]. This should be done with O(1) extra space.

Examples:

Input: arr[]  = {3, 2, 0, 1}
Output: arr[] = {1, 0, 3, 2}

Input: arr[] = {4, 0, 2, 1, 3}
Output: arr[] = {3, 4, 2, 0, 1}

Input: arr[] = {0, 1, 2, 3}
Output: arr[] = {0, 1, 2, 3}
 */
public class RearrangeSuchThatArriBecomesArrArri {

    public void rearrange(int arr[]){
        for(int i=0; i < arr.length; i++){
            int temp;
            if(arr[arr[i]] > arr.length-1){
                temp = arr[arr[i]]/arr.length-1;
            }else{
                temp = arr[arr[i]];
            }
            arr[i] = temp + arr.length*(arr[i]+1);
        }
        
        for(int i=0; i < arr.length;i++){
            arr[i] = arr[i] % arr.length;
        }
    }
    
    public static void main(String args[]){
        int arr[] = {4,2,0,1,3};
        RearrangeSuchThatArriBecomesArrArri rss = new RearrangeSuchThatArriBecomesArrArri();
        rss.rearrange(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}

