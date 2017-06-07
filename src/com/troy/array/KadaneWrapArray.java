package com.troy.array;

/**
 * http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
 * Given n numbers (both +ve and -ve), arranged in a circle, fnd the maximum sum of consecutive number. 

Examples:

Input: a[] = {8, -8, 9, -9, 10, -11, 12}
Output: 22 (12 + 8 - 8 + 9 - 9 + 10)

Input: a[] = {10, -3, -4, 7, 6, 5, -4, -1} 
Output:  23 (7 + 6 + 5 - 4 -1 + 10) 

Input: a[] = {-1, 40, -14, 7, 6, 5, -4, -1}
Output: 52 (7 + 6 + 5 - 4 - 1 - 1 + 40)
There can be two cases for the maximum sum:

 * Test cases
 * All negative
 * All positives
 * all 0s
 */

class Triplet{
    int start;
    int end;
    int sum;
    @Override
    public String toString() {
        return "Triplet [start=" + start + ", end=" + end + ", sum=" + sum
                + "]";
    }
    
}
public class KadaneWrapArray {

    public Triplet kadaneWrap(int arr[]){
        Triplet straightKadane = kadane(arr);
        int sum =0;
        for(int i=0; i < arr.length; i++){
            sum += arr[i];
            arr[i] = -arr[i];
        }
        Triplet wrappedNegKadane = kadane(arr);
        for(int i=0; i < arr.length; i++){
            arr[i] = -arr[i];
        }
        if(straightKadane.sum < sum + wrappedNegKadane.sum){
            straightKadane.sum = wrappedNegKadane.sum + sum;
            straightKadane.start = wrappedNegKadane.end+1;
            straightKadane.end = wrappedNegKadane.start-1;
        }
        return straightKadane;
    }
    
    /**
     * This method assumes there is at least one positive number in the array.
     * Otherwise it will break
     * @param arr
     * @return
     */
    public Triplet kadane(int arr[]){
        int sum =0;
        int cStart = 0;
        int mStart = 0;
        int end = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i < arr.length; i++){
            sum += arr[i];
            if(sum <= 0){
                sum = 0;
                cStart = i+1;
            }else{
                if(sum > maxSum){
                    maxSum = sum;
                    mStart = cStart;
                    end = i;
                }
            }
        }
        Triplet p = new Triplet();
        p.sum = maxSum;
        p.start = mStart;
        p.end = end;
        return p;
    }
    
    public static void main(String args[]){
        KadaneWrapArray kwa = new KadaneWrapArray();
        int input[] = {12, -2, -6, 5, 9, -7, 3};
        int input1[] = {8, -8, 9, -9, 10, -11, 12};
        System.out.println(kwa.kadaneWrap(input));
        System.out.println(kwa.kadaneWrap(input1));
    }
}
