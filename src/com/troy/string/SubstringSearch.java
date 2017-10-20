package com.troy.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Date 09/22/2014
 * @author tusroy
 * 
 * Do pattern matching using KMP algorithm
 * 
 * Runtime complexity - O(m + n) where m is length of text and n is length of pattern
 * Space complexity - O(n)
 */
public class SubstringSearch {

    /**
     * Slow method of pattern matching
     */
    public boolean hasSubstring(char[] text, char[] pattern){
        int i=0;
        int j=0;
        int k = 0;
        while(i < text.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
                if(j == pattern.length)
                    return true;   
            }else{
                j=0;
                k++;
                i = k;
            }
        }
        return false;
    }
    
    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private int[] computeTemporaryArray(char pattern[]){
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    /**
     * KMP algorithm of pattern matching.
     */
    public boolean KMP(char []text, char []pattern){
        
        int lps[] = computeTemporaryArray(pattern);
        int i=0;
        int j=0;
        while(i < text.length)
            if(text[i] == pattern[j]){
                i++;
                j++;
                if(j == pattern.length)
                    return true;                
            }else if(j==0)
            	i++;
            else
            	j=lps[j-1];
        
        
        return false;
    }
        
    public static void main(String args[]){
        
    	 assertThat(new SubstringSearch().hasSubstring("abcxabcdabcdabcy".toCharArray(), "abcdabcy".toCharArray()), is(true));
         assertThat(new SubstringSearch().hasSubstring("abcxabcdabcdabcy".toCharArray(), "abcda1".toCharArray()), is(false));
         
                
        assertThat(new SubstringSearch().KMP("abcxabcdabcdabcy".toCharArray(), "abcdabcy".toCharArray()), is(true));
        assertThat(new SubstringSearch().KMP("abcxabcdabcdabcy".toCharArray(), "abcda1".toCharArray()), is(false));
        
        System.out.println("all cases passed");
        
    }
}
