package com.troy.array.gotit;

/**
 * http://stackoverflow.com/questions/4686823/given-2-sorted-arrays-of-integers-find-the-nth-largest-number-in-sublinear-time
 * http://articles.leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
 * 
 * Solution:
 * 1) Take the middle of each array, now there are 4 sections of these two array
 * 2) We will be able to get rid of one section based on where k lies(left1Total+left2Total <=k)
 * 	 and comparing two middle values (a[mid1] <b[mid2])
 * 
 */
public class KthLargestInTwoSortedArray {
    
    public int kthLargest(int input1[],int input2[],int k){
        return kthLargest(input1, input2, 0, input1.length-1, 0, input2.length-1, k);
    }
    
    private int kthLargest(int input1[],int input2[],int l1, int h1, int l2,int h2,int k){
        if(l1 > h1){
            return input2[k-1];
        }
        if(l2 > h2){
            return input1[k-1];
        }
        
        if((h1 - l1 + 1) + (h2 - l2 + 1) == k){
            return Math.max(input1[h1], input2[h2]);
        }

        if(k == 1){
            return Math.min(input1[l1],input2[l2]);
        }

        //handle the situation where only one element is left
        //in either of array.
        //e.g k =2 and arr1 = 8 arr2 = 1,9,11
        //we try to find if 8 is before 9 betweenn 1 and 9
        //or before 1. In all these cases k will be either
        //1 8 or 9 
        if(l2 == h2 || l1 == h1){
            if(l2 == h2){
                if(input1[l1+k-1] < input2[l2]){
                    return input1[l1+k-1];
                }else if(input1[l1+k-2] > input2[l2]){
                    return input1[l1+k-2];
                }else{
                    return input2[l2];
                }
            }
            if(l1 == h1){
                if(input2[l2+k-1] < input1[l1]){
                    return input2[l2+k-1];
                }else if(input2[l2+k-2] > input1[l1]){
                    return input2[l2+k-2];
                }else{
                    return input1[l1];
                }
            }
        }
        
    
        int m1 = (h1 + l1)/2;
        int m2 = (h2 + l2)/2;
        
        int diff1 = m1 - l1+1;
        int diff2 = m2 - l2+1;
        if(diff1 + diff2 >= k){
            if(input1[m1] < input2[m2]){
                return kthLargest(input1, input2, l1, h1, l2, m2, k);
            }else{
                return kthLargest(input1, input2, l1, m1, l2, h2, k);
            }
        }else{
            if(input1[m1] < input2[m2]){
                return kthLargest(input1, input2,m1+1, h1, l2, h2, k - diff1);
            }else{
                return kthLargest(input1, input2, l1, h1, m2+1, h2, k -diff2);
            }
        }
    }
    
    public static void main(String args[]){
        KthLargestInTwoSortedArray kis = new KthLargestInTwoSortedArray();
        int input1[] = {1,4,7,11,17,21};
        int input2[] = {-4,-1,3,4,6,28,35,41,56,70};
        for(int i = 0; i < input1.length + input2.length; i++){
            System.out.println(kis.kthLargest(input1, input2, i+1));
        }
//      System.out.println(kis.kthLargest(input1, input2, 6));

    }
}
