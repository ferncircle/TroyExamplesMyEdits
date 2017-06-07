package com.troy.tree.gotit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.troy.tree.BinaryTree;
import com.troy.tree.Node;

/**
 *  Date 11/01/2015
 *  @author Tushar
 * Note that this is same as encoding/decoding a binary tree using PreOrder traversal. 
 *  A succinct encoding of Binary Tree takes close to minimum possible space. The number of structurally different 
 *  binary trees on n nodes is n’th Catalan number. For large n, this is about 4n; thus we need at least about 
 *  log2 4 n = 2n bits to encode it. A succinct binary tree therefore would occupy 2n+o(n) bits.

One simple representation which meets this bound is to visit the nodes of the tree in preorder, outputting “1” for an 
internal node and “0” for a leaf. If the tree contains data, we can simply simultaneously store it in a consecutive 
array in preorder.

Below is algorithm for encoding:

function EncodeSuccinct(node n, bitstring structure, array data) {
    if n = nil then
        append 0 to structure;
    else
        append 1 to structure;
        append n.data to data;
        EncodeSuccinct(n.left, structure, data);
        EncodeSuccinct(n.right, structure, data);
}
And below is algorithm for decoding

function DecodeSuccinct(bitstring structure, array data) {
    remove first bit of structure and put it in b
    if b = 1 then
        create a new node n
        remove first element of data and put it in n.data
        n.left = DecodeSuccinct(structure, data)
        n.right = DecodeSuccinct(structure, data)
        return n
    else
        return nil
}
Source: https://en.wikipedia.org/wiki/Binary_tree#Succinct_encodings

Example:

Input:   
        10
     /      \
   20       30
  /  \        \
 40   50      70 

Data Array (Contains preorder traversal)
10 20 40 50 30 70

Structure Array
1 1 1 0 0 1 0 0 1 0 1 0 0 
1 indicates data and 0 indicates NULL
 *
 * References - http://www.geeksforgeeks.org/succinct-encoding-of-binary-tree/
 * https://en.wikipedia.org/wiki/Binary_tree#Succinct_encodings
 */
public class SuccinctTree {

    public static class Result {
        List<Integer> binaryRep = new ArrayList<>();
        List<Integer> actualData = new ArrayList<>();
    }

    public Result encode(Node root) {
        Result r = new Result();
        encode(root, r);
        return r;
    }

    private void encode(Node root, Result r) {
        if(root == null) {
            r.binaryRep.add(0);
            return;
        }
        r.actualData.add(root.data);
        r.binaryRep.add(1);

        encode(root.left, r);
        encode(root.right, r);
    }

    public Node decode(Result r) {
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        return decode(r, x, y);
    }

    private Node decode(Result r, AtomicInteger x, AtomicInteger y) {
        if(r.binaryRep.get(x.get()) == 0) {
            x.getAndIncrement();
            return null;
        }

        Node root = new Node();
        root.data = r.actualData.get(y.getAndIncrement());
        x.getAndIncrement();
        root.left = decode(r, x, y);
        root.right = decode(r, x, y);
        return root;
    }

    public static void main(String args[]) {
        Node root = null;
        BinaryTree bt = new BinaryTree();
        root = bt.addNode(10, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(20, root);
        root = bt.addNode(15, root);
        root = bt.addNode(-7, root);
        root = bt.addNode(22, root);
        root = bt.addNode(-4, root);
        root = bt.addNode(12, root);
        System.out.println("Before decoding");
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
        System.out.println();
        SuccinctTree st = new SuccinctTree();
        Result r = st.encode(root);
        root = st.decode(r);
        System.out.println("After decoding");
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
    }

}
