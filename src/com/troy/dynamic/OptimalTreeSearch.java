package com.troy.dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
 * Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is 
 * the number of searches to keys[i]. Construct a binary search tree of all keys such that the total cost of all the 
 * searches is as small as possible.

Let us first define the cost of a BST. The cost of a BST node is level of that node multiplied by its frequency. 
Level of root is 1.

Example 1
Input:  keys[] = {10, 12}, freq[] = {34, 50}
There can be following two possible BSTs 
        10                       12
          \                     / 
           12                 10
          I                     II
Frequency of searches of 10 and 12 are 34 and 50 respectively.
The cost of tree I is 34*1 + 50*2 = 134
The cost of tree II is 50*1 + 34*2 = 118 

Example 2
Input:  keys[] = {10, 12, 20}, freq[] = {34, 8, 50}
There can be following possible BSTs
    10                12                 20         10              20
      \             /    \              /             \            /
      12          10     20           12               20         10  
        \                            /                 /           \
         20                        10                12             12  
     I               II             III             IV             V
Among all possible BSTs, cost of the fifth BST is minimum.  
Cost of the fifth BST is 1*50 + 2*34 + 3*8 = 142

Solution: Use DP interval(gap) but checkout how to handle levels 

1 2 3
2,3 + 1,3+1,2

 */
public class OptimalTreeSearch {

    public int minCostRec(int input[],int freq[]){
        
        return minCostRec(input,freq,0,input.length-1,1);
    }
    
    private int minCostRec(int input[],int freq[],int low,int high,int level){
        if(low > high){
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=low; i <= high; i++){
            int val = minCostRec(input,freq,low,i-1,level+1) + 
                    minCostRec(input,freq,i+1,high,level+1)
                    + level*freq[i];
            if(val < min){
                min = val;
            }
        }
        return min;
    }
    
    public int minCost(int input[], int freq[]){
        int T[][] = new int[input.length][input.length];
        
        for(int i=0; i < T.length; i++){
            T[i][i] = freq[i];
        }
        
        for(int size = 2; size <= input.length; size++){
            for(int start=0; start <= input.length-size; start++){
                int end = start + size -1;
                T[start][end] = Integer.MAX_VALUE;
                int sum = getSum(freq, start, end);
                
                for(int k=start; k <= end; k++){
                     int val = sum + (k==start ? 0 : T[start][k-1]) +
                            (k==end ? 0 : T[k+1][end]) ;
                     if(val < T[start][end]){
                         T[start][end] = val;
                     }
                }
            }
        }
        return T[0][input.length-1];
    }
    
    private int getSum(int freq[], int i, int j){
        int sum = 0;
        for(int x = i; x <= j; x++){
            sum += freq[x];
        }
        return sum;
    }
 
    
    public static void main(String args[]){
        int input[] = {10,12,20,35,46,12,23,1,23,5};
        int freq[] =  {34, 8,50,21,16,23,5, 4,12,34};
        OptimalTreeSearch ots = new OptimalTreeSearch();
        System.out.println(ots.minCost(input, freq));
        System.out.println(ots.minCostRec(input, freq));
    }
}
