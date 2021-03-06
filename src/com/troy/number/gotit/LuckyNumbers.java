package com.troy.number.gotit;

/**
 * http://www.geeksforgeeks.org/lucky-numbers/
 * Lucky numbers are subset of integers. Rather than going into much theory, let us see the process of arriving at 
 * lucky numbers,

Take the set of integers
1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,��

First, delete every second number, we get following reduced set.
1,3,5,7,9,11,13,15,17,19,����

Now, delete every third number, we get
1, 3, 7, 9, 13, 15, 19,�.�.

Continue this process indefinitely��
Any number that does NOT get deleted due to above process is called �lucky�.

Therefore, set of lucky numbers is 1, 3, 7, 13,���

Now, given an integer �n�, write a function to say whether this number is lucky or not.

Solution: 
1) Get the rank of number and see if rank%counter==0
 */
public class LuckyNumbers {

    public boolean isLuck(int n,int counter){
        if(n < counter){
            return true;
        }
        if(n % counter == 0){
            return false;
        }
        
        return isLuck( n - n/counter,counter+1);
    }
    
    public static void main(String args[]){
        LuckyNumbers ln = new LuckyNumbers();
        System.out.println(ln.isLuck(19, 2));
    }
    
}
