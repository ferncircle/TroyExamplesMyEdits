package com.troy.misc.gotit;

import java.util.ArrayList;
import java.util.List;

/**
 * All prime numbers before n
 * 
 * 1) Make list of primes so far (start with 2, 3)
 * 2) for each number from 5 to n see if it's divisible by above primes so far, otherwise add it to primes
 */
public class PrimeNumbersBeforeN {

    public List<Integer> primeNumbers(int n){
        List<Integer> result = new ArrayList<Integer>();
        result.add(2);
        for(int i=3; i < n; i+=2){//only consider odd numbers
        	boolean divisible = false;
            
            for(int r : result){ //all primes so far
                if(2*r > i){
                    break;
                }
                if(i % r == 0){//check if cur num divisible by primes so far
                    divisible = true;
                    break;
                }
            }
            if(!divisible){
                result.add(i);
            }
        }
        return result;
    }
    
    public static void main(String args[]){
        PrimeNumbersBeforeN pnb = new PrimeNumbersBeforeN();
        List<Integer> result = pnb.primeNumbers(150);
        result.forEach(i -> System.out.print(i + " "));
    }
}
