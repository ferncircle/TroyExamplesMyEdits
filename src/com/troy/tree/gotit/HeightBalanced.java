package com.troy.tree.gotit;

import com.troy.tree.Node;

/**
 * Date 10/06/2016
 * @author Tushar Roy
 *
 * Given a binary tree, determine if it is height-balanced.
 * 
 * Solution:
 * 1) check if diff of left and right subtree is <=1 and also left subtree and right subtree height balanced
 * 2) This can be done in O(n) by returning -1 for height from subtree indicating that subtree is not
 * 		balanced
 * 
 * Time complexity O(logn)
 *
 * Reference
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class HeightBalanced {
    public boolean isBalanced(Node root) {
        return isBalancedUtil(root) >= 0;
    }

    private int isBalancedUtil(Node root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedUtil(root.left);
        if (left < 0) {
            return -1;
        }
        int right = isBalancedUtil(root.right);
        if (right < 0) {
            return -1;
        }
        int diff = Math.abs(left - right);
        return diff <= 1 ? (Math.max(left, right) + 1) : -1;
    }
}
