package com.troy.number;

/**
 * http://www.geeksforgeeks.org/fast-multiplication-method-without-using-multiplication-operator-russian-peasants-algorithm/
 * Given two integers, write a function to multiply them without using multiplication operator.

There are many other ways to multiply two numbers (For example, see this). One interesting method is the Russian peasant 
algorithm. The idea is to double the first number and halve the second number repeatedly till the second number doesn’t 
become 1. In the process, whenever the second number become odd, we add the first number to result (result is initialized as 0)
 * Test cases
 * Division by 0
 * Negative numbers
 */
public class RussianPeasantMultiplication {

    public int multiply(int a,int b){
        int res = 0;
        while(b > 0){
            if(b % 2 != 0){
                res += a;
            }
            a = a<<1;
            b = b>>1;
        }
        return res;
    }
    
    public static void main(String args[]){
        RussianPeasantMultiplication rpm = new RussianPeasantMultiplication();
        System.out.println(rpm.multiply(3, 7));
    }
}
