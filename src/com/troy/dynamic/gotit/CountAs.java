package com.troy.dynamic.gotit;

/**
 * http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 * Imagine you have a special keyboard with the following keys: 
Key 1:  Prints 'A' on screen
Key 2: (Ctrl-A): Select screen
Key 3: (Ctrl-C): Copy selection to buffer
Key 4: (Ctrl-V): Print buffer on screen appending it
                 after what has already been printed. 

If you can only press the keyboard for N times (with the above four
keys), write a program to produce maximum numbers of A's. That is to
say, the input parameter is N (No. of keys that you can press), the 
output is M (No. of As that you can produce).
Examples:

Input:  N = 3
Output: 3
We can at most get 3 A's on screen by pressing 
following key sequence.
A, A, A

Input:  N = 7
Output: 9
We can at most get 9 A's on screen by pressing 
following key sequence.
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V

Solution:
Find a point from 0 to i after which we'll just do ctrl V and choose the max.
 */
public class CountAs {

    public int countAsRec(int n){
    
        if(n < 7){
            return n;
        }
        int max = Integer.MIN_VALUE;
        int result = 0;
        for(int b=n-3; b > 0; b--){
            result = (n-b-1)*countAs(b);
            if(max < result){
                max = result;
            }
        }
        return max;
    }
 
    public int countAs(int n){
        if(n < 7){
            return n;
        }
        
        int T[] = new int[n+1];
        for(int i=1; i < 7 ; i++){
            T[i] = i;
        }
        for(int i=7; i <= n; i++){
            for(int b = i-3; b > 0; b--){
                T[i] = Math.max(T[i], T[b]*(i-b-1));
            }
        }
        return T[n];
    }
    
    public static void main(String args[]){
        CountAs ca =new CountAs();
        System.out.println(ca.countAsRec(25));
        System.out.println(ca.countAs(25));
              
    }
}
