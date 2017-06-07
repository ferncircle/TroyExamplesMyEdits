package com.troy.bits.gotit;

class Pair{
    int x;
    int y;
}

/**
 * * http://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
 * Given an unsorted array of size n. Array elements are in range from 1 to n. One number from set {1, 2, …n} is missing and
 *  one number occurs twice in array. 
 * Find these two numbers.

Examples:

  arr[] = {3, 1, 3}
  Output: 2, 3   // 2 is missing and 3 occurs twice 

  arr[] = {4, 3, 6, 2, 1, 1}
  Output: 1, 5  // 5 is missing and 1 occurs twice 
  
  Solution: 
  1) Do xor of elements in array and then use that result to xor all elements from 1 to n
  2) The result will have xor of just two elements (one missing and one repeated). We now want to reduce the 
  		problem from two variables to one variable
  3) Divide the given elements in two sets, one that has missing and one that has repeated
  4) Use any set bet in result two elements xor to divide (we will use rightmost bit)
  5) Take xor of all elements in each set separately
  6) Now do repeat steps from 3-5 for elements from 1 to n
  
  
 * http://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
 * 
 * You are given an array of n+2 elements. All elements of the array are in range 1 to n. And all elements 
 * occur once except two numbers which occur twice. Find the two repeating numbers.

For example, array = {4, 2, 4, 5, 2, 3, 1} and n = 5

The above array has n + 2 = 7 elements with all elements occurring once except 2 and 4 which occur twice. So the output 
should be 4 2.

 
 */
public class MissingNumbers {

    public Pair findMissingAndRepeated(int arr[], int n){
        int xor = 0;
        for(int i=0; i < arr.length; i++){
            xor = xor ^ arr[i];
        }
        
        for(int i=1; i <= n; i++){
            xor = xor ^ i;
        }
        
        xor = xor & ~(xor-1); //get the rightmost set bit e.g. 1100 & ~(1011)= 1100 & 0100 = 100 i.e. 4
        int set1 = 0;
        int set2 = 0;
        for(int i=0; i < arr.length; i++){
            if((arr[i] & xor) > 0){
                set1 ^= arr[i];
            }else{
                set2 ^= arr[i];
            }
        }
        Pair p = new Pair();
        for(int i=1; i <= n; i++){
            if((i & xor) > 0){
                set1 ^= i;
            }else{
                set2 ^= i;
            }
        }
        p.x = set1;
        p.y = set2;
        return p;
    }
    
    public Pair findTwoMissingNumber(int arr[], int n){
        int xor = 0;
        for(int i=0; i < arr.length; i++){
            xor = xor ^ arr[i];
        }
        
        for(int i=1; i <= n; i++){
            xor = xor ^ i;
        }
        
        xor = xor & ~(xor-1);
        int set1 = 0;
        int set2 = 0;
        for(int i=0; i < arr.length; i++){
            if((arr[i] & xor) > 0){
                set1 ^= arr[i];
            }else{
                set2 ^= arr[i];
            }
        }
        Pair p = new Pair();
        for(int i=1; i <= n; i++){
            if((i & xor) > 0){
                set1 ^= i;
            }else{
                set2 ^= i;
            }
        }
        p.x = set1;
        p.y = set2;
        return p;
    }
    
    public static void main(String args[]){
        MissingNumbers mn = new MissingNumbers();
        int arr[] = {1,2,3,5,5};
        Pair p = mn.findMissingAndRepeated(arr, 5);
        System.out.println(p.x + " " + p.y);
        int arr1[] = {1,5,3,6};
        p = mn.findMissingAndRepeated(arr1, 6);
        System.out.println(p.x + " " + p.y);
    }
}
