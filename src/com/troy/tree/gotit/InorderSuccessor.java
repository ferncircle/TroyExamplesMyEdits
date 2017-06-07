package com.troy.tree.gotit;

import com.troy.tree.Node;

/**
 * Date 03/27/2016
 * @author Tushar Roy
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 *Solution:
 *1) if the right subtree of given node p is not null then just find the min node in right subtree and return
 *2) else go down the left subtree
 * Time complexity O(h)
 * Space complexity O(h)
 *
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessor {
    public Node inorderSuccessor(Node root, Node p) {
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            return succ(root, p.data);
        }
    }

    private Node succ(Node root, int data) {
        if (root.data == data) {
            return null;
        }
        if (root.data < data) {
            return succ(root.right, data);
        } else {
            Node s = succ(root.left, data);
            return s == null ? root : s;
        }
    }
}
