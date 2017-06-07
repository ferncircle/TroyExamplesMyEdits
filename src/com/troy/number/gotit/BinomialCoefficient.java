package com.troy.number.gotit;

/**
 * http://www.geeksforgeeks.org/space-and-time-efficient-binomial-coefficient/
 * 
 * Write a function that takes two parameters n and k and returns the value of Binomial Coefficient C(n, k). 
 * For example, your function should return 6 for n = 4 and k = 2, 
 * and it should return 10 for n = 5 and k = 2.

We have discussed a O(n*k) time and O(k) extra space algorithm in this post. The value of C(n, k) can be calculated 
in O(k) time and O(1) extra space.

C(n, k) = n! / (n-k)! * k!
        = [n * (n-1) *....* 1]  / [ ( (n-k) * (n-k-1) * .... * 1) * 
                                    ( k * (k-1) * .... * 1 ) ]
After simplifying, we get
C(n, k) = [n * (n-1) * .... * (n-k+1)] / [k * (k-1) * .... * 1]

Also, C(n, k) = C(n, n-k)  // we can change r to n-r if r > n-r 
 * Test cases
 * k is 0
 * k or n are negative
 * k greater than n
 */
public class BinomialCoefficient {

    public int calculate(int n, int k){
        if(k > n-k){
            k = n-k;
        }
        int result = 1;
        for(int i=0; i < k; i++){
            result *= (n-i);
            result /= (i+1);
        }
        return result;
    }
    
    public static void main(String args[]){
        BinomialCoefficient bc = new BinomialCoefficient();
        System.out.print(bc.calculate(8, 3));
    }
}
