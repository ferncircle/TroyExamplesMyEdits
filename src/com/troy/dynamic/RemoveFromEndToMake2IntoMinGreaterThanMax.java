package com.troy.dynamic;

/**
 * http://www.geeksforgeeks.org/remove-minimum-elements-either-side-2min-max/
 * Given an unsorted array, trim the array such that twice of minimum is greater than maximum in the trimmed array. 
 * Elements should be removed either end of the array.

Number of removals should be minimum.

Examples:

arr[] = {4, 5, 100, 9, 10, 11, 12, 15, 200}
Output: 4
We need to remove 4 elements (4, 5, 100, 200)
so that 2*min becomes more than max.


arr[] = {4, 7, 5, 6}
Output: 0
We don't need to remove any element as 
4*2 > 7 (Note that min = 4, max = 8)

arr[] = {20, 7, 5, 6}
Output: 1
We need to remove 20 so that 2*min becomes
more than max

arr[] = {20, 4, 1, 3}
Output: 3
We need to remove any three elements from ends
like 20, 4, 1 or 4, 1, 3 or 20, 3, 1 or 20, 4, 1

Solution:
Recursive: min(minRemovals(arr, l+1, h), minRemovals(arr, l, h-1)) + 1;
DP: Use DP interval for bottom up approach
1) Start with the gap(interval) of 1 to n

 */
public class RemoveFromEndToMake2IntoMinGreaterThanMax {

    public int removeFromEnd(int input[]){
        return removeFromEnd(input,0,input.length-1);
    }
    
    private int removeFromEnd(int input[],int low,int high){
        if(low > high){
            return input.length;
        }
        int min = min(input,low,high);
        int max = max(input,low,high);
        if(2*min > max){
            return 0;
        }

        return Math.min(removeFromEnd(input,low,high-1), removeFromEnd(input,low+1,high)) +1;
    }
    
    private int min(int input[],int low,int high){
        int min = Integer.MAX_VALUE;
        for(int i=low; i <=high; i++){
            if(min > input[i]){
                min = input[i];
            }
        }
        return min;
    }
    private int max(int input[],int low,int high){
        int max = Integer.MIN_VALUE;
        for(int i=low; i <=high; i++){
            if(max < input[i]){
                max = input[i];
            }
        }
        return max;
    }
    
    public int removeFromEndDynamic(int input[]){
        int T[][] = new int[input.length][input.length];
        for(int gap=1; gap <= input.length; gap++){
            for(int i=0, j = i + gap-1; i < input.length-gap+1; i++,j++){
                int min = min(input,i,j);
                int max = max(input,i,j);
                if(2*min > max){
                    T[i][j] = 0;
                }else{
                    T[i][j] = Math.min(T[i+1][j] , T[i][j-1]) +1;
                }
            }
        }
        return T[0][input.length-1];
    }
    public static void main(String args[]){
        int input[] = {5,1,3,1,3,8,3};
        RemoveFromEndToMake2IntoMinGreaterThanMax rme = new RemoveFromEndToMake2IntoMinGreaterThanMax();
        System.out.println(rme.removeFromEnd(input));
        System.out.println(rme.removeFromEndDynamic(input));
    }
}
