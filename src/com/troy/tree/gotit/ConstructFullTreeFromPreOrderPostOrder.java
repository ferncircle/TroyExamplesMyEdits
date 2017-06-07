package com.troy.tree.gotit;

import com.troy.tree.Node;

/**
 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 * Given two arrays that represent preorder and postorder traversals of a full binary tree, construct the binary tree.

A Full Binary Tree is a binary tree where every node has either 0 or 2 children

Following are examples of Full Trees.
        
                  

          1
        /   \
      2       3
    /  \     /  \
   4    5   6    7
 /  \  
8    9 

Let us consider the two given arrays as pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7} and post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};

Solution:
1) Find out root of remaining tree. Find out what subarray belongs to left and what belongs to right and then recurse
2) First element in Pre is root. Next element in Pre is 2. Look for 2 in Post and everything before that belongs in one section and rest in other section
 * Test cases
 * Empty tree
 * Tree with big on left side
 * Tree with big on right side
 */
public class ConstructFullTreeFromPreOrderPostOrder {

    public Node constructTree(int preorder[],int postorder[]){
    
        return constructTree(preorder, postorder, 0, postorder.length-2, 0);
        
    }
    
    private Node constructTree(int preorder[],int postorder[],int low,int high,int index){

        if(low > high || index >= preorder.length-1){
            Node node = new Node();
            node.data = preorder[index];
            return node;
        }
        
        Node node = new Node();
        node.data = preorder[index];
        int i=0;
        for(i=low; i <= high; i++){
            if(preorder[index+1] == postorder[i]){
                break;
            }
        }
        node.left = constructTree(preorder, postorder, low, i-1, index+1);
        node.right = constructTree(preorder, postorder, i+1, high-1, index + i-low+2);
        return node;
    }
    
    public static void main(String args[]){
        ConstructFullTreeFromPreOrderPostOrder cft = new ConstructFullTreeFromPreOrderPostOrder();
        int preorder[] = {1,2,3,6,7,8,9};
        int postorder[] = {2,6,8,9,7,3,1};
        Node root = cft.constructTree(preorder, postorder);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        tt.preOrder(root);
        tt.postOrder(root);
    }
    
    /**
     * It is not possible to construct a general Binary Tree from preorder and postorder traversals (See this). But if know that the Binary Tree is Full, we can construct the tree without ambiguity. Let us understand this with the help of following example.

Let us consider the two given arrays as pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7} and post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
In pre[], the leftmost element is root of tree. Since the tree is full and array size is more than 1. The value next to 1 in pre[], must be left child of root. So we know 1 is root and 2 is left child. How to find the all nodes in left subtree? We know 2 is root of all nodes in left subtree. All nodes before 2 in post[] must be in left subtree. Now we know 1 is root, elements {8, 9, 4, 5, 2} are in left subtree, and the elements {6, 7, 3} are in right subtree.


                  1
                /   \
               /      \
     {8, 9, 4, 5, 2}     {6, 7, 3}
We recursively follow the above approach and get the following tree.

          1
        /   \
      2       3
    /  \     /  \
   4    5   6    7
  / \  
 8   9 
     * 
     * */
    
}
