package com.troy.stackqueue;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
 * You are not allowed to use loop constructs like while, for..etc, and you can only use the following ADT 
 * functions on Stack S:
isEmpty(S)
push(S)
pop(S)

Solution:
The idea of the solution is to hold all values in Function Call Stack until the stack becomes empty. When the 
stack becomes empty, insert all held items one by one at the bottom of the stack.

For example, let the input stack be

    1  <-- top
    2
    3
    4   
First 4 is inserted at the bottom.
    4 <-- top

Then 3 is inserted at the bottom
    4 <-- top    
    3

Then 2 is inserted at the bottom
    4 <-- top    
    3 
    2
     
Then 1 is inserted at the bottom
    4 <-- top    
    3 
    2
    1
    
 */
public class ReverseStackUsingRecursion {

    public void reverse(Deque<Integer> stack){
        if(stack.size() == 0){
            return;
        }
        int temp = stack.pollFirst();
        reverse(stack);
        
        pushAtBottom(stack,temp);
    }
    
    private void pushAtBottom(Deque<Integer> stack,int data){
        if(stack.size() == 0){
            stack.offerFirst(data);
            return;
        }
        int temp = stack.pollFirst();
        pushAtBottom(stack, data);
        stack.offerFirst(temp);
    }
    
    public static void main(String args[]){
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        
        Iterator<Integer> itr =  stack.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
        ReverseStackUsingRecursion rsu = new ReverseStackUsingRecursion();
        rsu.reverse(stack);
        
        itr =  stack.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
    }
    
}
