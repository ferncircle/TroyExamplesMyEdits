package com.troy.tree.gotit;

import java.util.LinkedList;
import java.util.Queue;

import com.troy.tree.BinaryTree;
import com.troy.tree.Node;

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 * Given a Binary Tree, write a function to check whether the given Binary Tree is Complete Binary Tree or not.

A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, 
and all nodes are as far left as possible. See following examples.

The following trees are examples of Complete Binary Trees
    1
  /   \
 2     3
  
       1
    /    \
   2       3
  /
 4

       1
    /    \
   2      3
  /  \    /
 4    5  6
The following trees are examples of Non-Complete Binary Trees
    1
      \
       3
  
       1
    /    \
   2       3
    \     /  \   
     4   5    6

       1
    /    \
   2      3
         /  \
        4    5
 
 Solution:
 1) Observe that if first node at any level is leaf then all other nodes should be leaf
 2) Also, if left child is null then so should be right
 3) Check above two conditions in level order traversal
 4) Also, notice we don't need to keep track of level (just the first occurence of leaf node)
 	so, no marker node is needed
 
 * Test cases:
 * A node with only right child
 * A node with only left child
 * A node with both left and right child
 */
public class IsCompleteBinaryTree {

    public boolean isComplete(Node root){
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        boolean foundFirstNonFull = false;
        while(!queue.isEmpty()){
            root = queue.poll();
            if(foundFirstNonFull){
                if(root.left != null || root.right != null){
                    return false;
                }
                continue;
            }
            if(root.left != null && root.right != null){
                queue.offer(root.left);
                queue.offer(root.right);
            }else if(root.left != null){
                queue.offer(root.left);
                foundFirstNonFull = true;
            }else if(root.right != null){
                return false;
            }else{
                foundFirstNonFull = true;
            }
        }
        return true;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(3, head);
        head = bt.addNode(-6, head);
        head = bt.addNode(7, head);
        head = bt.addNode(-10, head);
        head = bt.addNode(-15, head);
        head = bt.addNode(-4, head);
        head = bt.addNode(4, head);
        head = bt.addNode(11, head);
        head = bt.addNode(-9, head);
            
        IsCompleteBinaryTree icbt = new IsCompleteBinaryTree();
        System.out.println(icbt.isComplete(head));
    }
}
