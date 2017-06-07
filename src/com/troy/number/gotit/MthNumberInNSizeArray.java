package com.troy.number.gotit;

import java.util.Arrays;

/**
 * @author Tushar Roy
 * Date 01/21/2016
 *
 * Given a number n, find mth value in permutation of array consisting of [1..n] values.
 * mth is defined by lexicographical order.
 * 
 * Solution: Note that this is a opposite problem of LexicographicRankInPermutation
 * 1) Create a result array and find appropriate number to be placed at each position from [1..n]
 * 2) e.g. for position 0 in result array, find i in [1..n] such that
 * 		i*(n-1)! <= m < (i+1)*(n-1)!
 * 	  we can use binary search for it (but seems overkill)
 * 3) Now, repeat the above process for next position with m=m-i*(n-1)!
 */
public class MthNumberInNSizeArray {

    public int[] find(int n, int m) {
        boolean used[] = new boolean[n];
        int result[] = new int[n];
        find(result, used, 0, n, m);
        return result;
    }

    private void find(int result[], boolean used[], int start, int n, int m) {
        if(1 == m) {
            fillupRemaining(result, used, start);
            return;
        }
        int index = binarySearch(m, start + 1, n, factorial(n - start - 1));
        int i = nthUnused(used, index);
        used[i] = true;
        result[start] = i + 1;
        find(result, used, start + 1, n, m - (index-1)*factorial(n - start - 1));
    }

    private int binarySearch(int m, int start, int end, int factorial) {
        int i = 1;
        int j = end - start + 1;
        while (i <= j) {
            int middle = (i + j)/2;
            if (factorial*(middle) >= m && factorial*(middle - 1) < m) {
                return middle;
            } else if (factorial*middle < m){
                i = middle + 1;
            } else {
                j = middle - 1;
            }
        }
        throw new IllegalArgumentException();
     }
    private void fillupRemaining(int result[], boolean used[], int index) {
        int j = 0;
        for(int i = index; i < result.length; i++) {
            while (true) {
                if (!used[j]) {
                    result[i] = j + 1;
                    used[j] = true;
                    break;
                }
                j++;
            }
        }
    }

    private int nthUnused(boolean used[], int n) {
        int unused = 0;
        for ( int i = 0; i < used.length; i++) {
            if (!used[i]) {
                unused++;
                if (unused == n) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private int factorial(int n) {
        int result = 1;
        for ( int i = 1; i <= n; i++) {
            result = result*i;
        }
        return result;
    }

    public static void main(String args[]) {
        MthNumberInNSizeArray mn = new MthNumberInNSizeArray();
        int n = 5;
        int m = 120;
        for ( int i = 1; i <= m; i++) {
            int result[] = mn.find(n, i);
            Arrays.stream(result).forEach(r -> System.out.print(r + " "));
            System.out.print("\n");
        }
    }
}


