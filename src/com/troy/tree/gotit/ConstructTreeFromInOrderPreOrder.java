package com.troy.tree.gotit;

import com.troy.tree.Node;

/**
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * Let us consider the below traversals:

Inorder sequence: D B E A F C
Preorder sequence: A B D E C F

In a Preorder sequence, leftmost element is the root of the tree. So we know ‘A’ is root for given sequences. 
By searching ‘A’ in Inorder sequence, we can find out all 
elements on left side of ‘A’ are in left subtree and elements on right are in right subtree. So we know below structure now.

                 A
               /   \
             /       \
           D B E     F C
We recursively follow above steps and get the following tree.

         A
       /   \
     /       \
    B         C
   / \        /
 /     \    /
D       E  F
 * Test cases:
 * Empty tree
 * One node tree
 * All left side tree
 * All right side tree
 * Mixed tree
 * Full tree
 * complete tree
 */
public class ConstructTreeFromInOrderPreOrder {
    
    private int index = 0;
    public Node createTree(int inorder[],int preorder[]){
        Node result =  createTree(inorder,preorder,0,inorder.length-1);
        index = 0;
        return result;
    }
    
    private Node createTree(int inorder[],int preorder[], int start, int end){
        if(start > end){
            return null;
        }
        int i;
        for(i=start; i <= end; i++){
            if(preorder[index] == inorder[i]){
                break;
            }
        }
        Node node = Node.newNode(preorder[index]);
        index++;
        node.left = createTree(inorder,preorder,start,i-1);
        node.right = createTree(inorder,preorder,i+1,end);
        return node;
    }
}
