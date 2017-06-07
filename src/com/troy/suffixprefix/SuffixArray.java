package com.troy.suffixprefix;

import java.util.Arrays;

/**
 http://www.geeksforgeeks.org/suffix-array-set-1-introduction/
 
 Solution:
 1) Create suffix starting index array
 2) Sort the array based its suffixes to create suffix array
 3) For seaching a suffix, do a binary search in suffix array
  
  O(n^2Logn) : O(nLogN) for sort and n for string comparison 
 */
public class SuffixArray {
    
    public Integer[] createSuffixArray(String str){
    
        Integer suffix[] = new Integer[str.length()];
        for(int i=0; i < suffix.length; i++)
            suffix[i] = i;
        
        Arrays.sort(suffix,(i1,i2)->{
            return str.substring(i1).compareTo(str.substring(i2));
        });
        	
        return suffix;  
    }
    
    private int stringCompare(char str[],char subString[],int pos){
        int i=0;
        while(pos < str.length && i < subString.length && str[pos] == subString[i]){
            pos++;
            i++;
        }
        if(i == subString.length){
            return 0;
        }
        if(pos == str.length){
            return -1;
        }
        return str[pos] < subString[i] ? -1 : 1;  
    }
    
    public int subStringSearch(char str[],char subString[], Integer suffix[]){
        int low =0;
        int high = suffix.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            int result = stringCompare(str, subString, suffix[mid]);
            if(result == 0){
                return suffix[mid];
            }
            if(result > 0){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return -1;
    }
    
    public static void main(String args[]){
        SuffixArray suffix = new SuffixArray();
        String str = "banana";
        Integer result[] = suffix.createSuffixArray(str);
        
        System.out.print(suffix.subStringSearch(str.toCharArray(), "sippi".toCharArray(), result));
    }
}
