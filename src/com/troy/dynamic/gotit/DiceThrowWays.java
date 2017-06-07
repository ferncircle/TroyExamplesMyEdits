package com.troy.dynamic.gotit;

/**
 * @author Tushar Roy
 * http://www.geeksforgeeks.org/dice-throw-problem/
 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. X is the 
 * summation of values on each face when all the dice are thrown.
 * 
 * This solution assumes that 1,2,1 is different from 2,1,1 which is different from 1,1 2
 * so total 3 ways are possible
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Solution: e.g. m=6, n=3, X=8
 * For each dice, consider all it's sides and subtract it from X and recursively call
 * Sum(6, 3, 8) = Sum(6, 2, 7) + Sum(6, 2, 6) + Sum(6, 2, 5) + 
               Sum(6, 2, 4) + Sum(6, 2, 3) + Sum(6, 2, 2)

To evaluate Sum(6, 3, 8), we need to evaluate Sum(6, 2, 7) which can 
recursively written as following:
Sum(6, 2, 7) = Sum(6, 1, 6) + Sum(6, 1, 5) + Sum(6, 1, 4) + 
               Sum(6, 1, 3) + Sum(6, 1, 2) + Sum(6, 1, 1)

We also need to evaluate Sum(6, 2, 6) which can recursively written
as following:
Sum(6, 2, 6) = Sum(6, 1, 5) + Sum(6, 1, 4) + Sum(6, 1, 3) +
               Sum(6, 1, 2) + Sum(6, 1, 1)
..............................................
..............................................
Sum(6, 2, 2) = Sum(6, 1, 1)
 */
public class DiceThrowWays {

    public int numberOfWays(int n, int f, int k){
        
        int T[][] = new int[n+1][k+1];
        T[0][0] = 1;
    /*  for(int i=0; i < T.length; i++){
            T[0][i] = 1;
        }*/
        
        for(int i=1; i <= n; i++){
            for(int j =1; j <= i*f && j <= k ; j++){
                if(j == i){
                    T[i][j] = 1;
                    continue;
                }
                if(j < i){
                    continue;
                }
                for(int l =1; l <=f ;l++){
                    if(j >= l){
                        T[i][j] += T[i-1][j-l];
                    }
                }
            }
        }
        return T[n][k];
    }
    
    public static void main(String args[]){
        DiceThrowWays dtw = new DiceThrowWays();
        System.out.println(dtw.numberOfWays(3, 3, 6));
    }
}   
