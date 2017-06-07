package com.troy.tree;

public class Node {
    public Node left;
    public Node right;
    public Node next;
    public int data;
    public int lis;
    public int height;
    public int size;
    public Color color;
    
    public static Node newNode(int data){
        Node n = new Node();
        n.left = null;
        n.right = null;
        n.data = data;
        n.lis = -1;
        n.height = 1;
        n.size = 1;
        n.color = Color.RED;
        return n;
    }


}
