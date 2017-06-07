package com.troy.tree;

import com.troy.tree.gotit.ConstructTreeFromInOrderPreOrder;

/**
 * Date 07/20/2014
 * @author tusroy
 * 
 * Video link - https://youtu.be/4fiDs7CCxkc
 * 
 *Given a Binary Tree, write a function that returns the size of the largest subtree which is also a 
 *Binary Search Tree (BST). If the complete Binary Tree is BST, then return the size of whole tree.

Examples:

Input: 
      5
    /  \
   2    4
 /  \
1    3

Output: 3 
The following subtree is the maximum size BST subtree 
   2  
 /  \
1    3


Input: 
       50
     /    \
  30       60
 /  \     /  \ 
5   20   45    70
              /  \
            65    80
Output: 5
The following subtree is the maximum size BST subtree 
      60
     /  \ 
   45    70
        /  \
      65    80
      
      
      
 * Solution: 
 * 1) For each node, check if there is bst rooted here, for that left subtree and right subtree 
 * 		has to be BST and max in left subtree, this node and min of right subtree
 * 2) Traverse tree in post order fashion. Left and right nodes return 4 piece
 * of information to root which isBST, size of max BST, min and max in those
 * subtree. 
 * 3)If both left and right subtree are BST and this node data is greater than max
 * of left and less than min of right then it returns to above level left size +
 * right size + 1 and new min will be min of left side and new max will be max of
 * right side.
 * 
 * Note that if it's any subtree instead of entire subtree, then we probably can use inorder traversal and find longest
 * span of increasing sequence
 * 
 * References:
 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTInBinaryTree {

    public int largestBST(Node root){
        MinMax m = largest(root);
        return m.size;
    }
    
    private MinMax largest(Node root){
        //if root is null return min as Integer.MAX and max as Integer.MIN 
        if(root == null){
            return new MinMax();
        }
        
        //postorder traversal of tree. First visit left and right then
        //use information of left and right to calculate largest BST.
        MinMax leftMinMax = largest(root.left);
        MinMax rightMinMax =largest(root.right);
        
        MinMax m = new MinMax();
        
        //if either of left or right subtree says its not BST or the data
        //of this node is not greater/equal than max of left and less than min of right
        //then subtree with this node as root will not be BST. 
        //Return false and max size of left and right subtree to parent
        if(leftMinMax.isBST == false || rightMinMax.isBST == false || (leftMinMax.max > root.data || rightMinMax.min <= root.data)){
            m.isBST = false;
            m.size = Math.max(leftMinMax.size, rightMinMax.size);
            return m;
        }
        
        //if we reach this point means subtree with this node as root is BST.
        //Set isBST as true. Then set size as size of left + size of right + 1.
        //Set min and max to be returned to parent.
        m.isBST = true;
        m.size = leftMinMax.size + rightMinMax.size + 1;
     
        //if root.left is null then set root.data as min else
        //take min of left side as min
        m.min = root.left != null ? leftMinMax.min : root.data;
  
        //if root.right is null then set root.data as max else
        //take max of right side as max.
        m.max = root.right != null ? rightMinMax.max : root.data;
   
        return m;
    }
    
    public static void main(String args[]){
        LargestBSTInBinaryTree lbi = new LargestBSTInBinaryTree();
        ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
        //this is just to create a binary tree.
        int inorder[]  = {-7,-6,-5,-4,-3,-2,1,2,3,16,6,10,11,12,14};
        int preorder[] = {3,-2,-3,-4,-5,-6,-7,1,2,16,10,6,12,11,14};
        Node root = ctf.createTree(inorder, preorder);
        int largestBSTSize = lbi.largestBST(root);
        System.out.println("Size of largest BST  is " + largestBSTSize);
        assert largestBSTSize == 8;
    }
}

/**
 * Object of this class holds information which child passes back
 * to parent node.
 */
class MinMax{
    int min;
    int max;
    boolean isBST;
    int size ;
    
    MinMax(){
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        isBST = true;
        size = 0;
    }
    
    /**
     * In method 1, we traverse the tree in top down manner and do BST test for every node. If we traverse the tree in bottom up manner, then we can pass information about subtrees to the parent. The passed information can be used by the parent to do BST test (for parent node) only in constant time (or O(1) time). A left subtree need to tell the parent whether it is BST or not and also need to pass maximum value in it. So that we can compare the maximum value with the parent’s data to check the BST property. Similarly, the right subtree need to pass the minimum value up the tree. The subtrees need to pass the following information up the tree for the finding the largest BST.
1) Whether the subtree itself is BST or not (In the following code, is_bst_ref is used for this purpose)
2) If the subtree is left subtree of its parent, then maximum value in it. And if it is right subtree then minimum value in it.
3) Size of this subtree if this subtree is BST (In the following code, return value of largestBSTtil() is used for this purpose)

max_ref is used for passing the maximum value up the tree and min_ptr is used for passing minimum value up the tree.
     * 
     */
     
}
