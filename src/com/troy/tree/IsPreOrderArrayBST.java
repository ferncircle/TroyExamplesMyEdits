package com.troy.tree;

import java.util.Stack;

/**
 * Check if given pre array sequence will create a binary search tree or not.
 *
 * Input:  pre[] = {40, 30, 35, 80, 100}
Output: true
Given array can represent preorder traversal
of below tree
     40
   /   \
 30    80 
  \      \
  35     100 
 *
 * 
 * Solution:
 * 1) Note that if cur value in array is greater than root of its subtree then return false
 * 2) Use stack to maintain the roots but only maintain low level to high level
 * 3) Keep on popping roots from stack to maintain that property. 
 * 4) Last popped value is root of cur subtree.
 * 
 *
 * Time complexity - O(n)
 * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 * Reference - http://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
 */
public class IsPreOrderArrayBST {

    public boolean verifyPreorder(int pre[]) {
        Stack<Integer> st = new Stack<>();
        int rootOfCurSubtree = Integer.MIN_VALUE;
        st.push(pre[0]);
        for(int i = 1; i < pre.length; i++) {
        	int cur=pre[i];
            if(cur < rootOfCurSubtree) {
                return false;
            }
            while(!st.isEmpty() && st.peek() < cur) {
                rootOfCurSubtree = st.pop();
            }
            st.push(cur);
        }
        return true;
    }

    public boolean verifyPreorderConstantSpace(int[] preorder) {
        int start = 0;
        int min = Integer.MIN_VALUE;
        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] < min) {
                return false;
            }
            if (preorder[i] > preorder[i - 1]) {
                int index = binarySearch(preorder, start, i - 1, preorder[i]);
                min = preorder[index];
                start = i;
            }
        }
        return true;
    }

    int binarySearch(int[] preorder, int start, int end, int val) {
        int s = start;
        int e = end;
        while (s <= e) {
            int middle = (s + e)/2;
            if (preorder[middle] < val && (start == middle || preorder[middle - 1] > val)) {
                return middle;
            } else if (preorder[middle] < val) {
                e = middle - 1;
            } else {
                s = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        IsPreOrderArrayBST isb = new IsPreOrderArrayBST();
        int[] input = {40, 30, 35, 80, 100};
        System.out.println(isb.verifyPreorder(input));
        System.out.println(isb.verifyPreorderConstantSpace(input));
    }
}
