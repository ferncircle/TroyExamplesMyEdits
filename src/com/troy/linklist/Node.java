package com.troy.linklist;

public class Node{
    public int data;
    public Node next;
    public Node before;
    public Node child;
    public Object obj;
    
    public static Node newNode(int data, Object... obj){
        Node n = new Node();
        n.data = data;
        n.next = null;
        n.before = null;
        if(obj.length > 0)
        {    
            n.obj = obj[0];
        }
        return n;
    }
}