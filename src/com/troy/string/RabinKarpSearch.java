package com.troy.string;

/**
 * Date 09/25/2014
 * @author Tushar Roy
 *
 * Rabin Karp algorith for substring matching.
 *
 *Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all 
 *occurrences of pat[] in txt[]. You may assume that n > m.

Examples:

Input:  txt[] = "THIS IS A TEST TEXT"
        pat[] = "TEST"
Output: Pattern found at index 10

Input:  txt[] =  "AABAACAADAABAABA"
        pat[] =  "AABA"
Output: Pattern found at index 0
        Pattern found at index 9
        Pattern found at index 12
        

       The Naive String Matching algorithm slides the pattern one by one. After each slide, it one by one checks 
       characters at the current shift and if all characters match then prints the match.
Like the Naive Algorithm, Rabin-Karp algorithm also slides the pattern one by one. But unlike the Naive algorithm, 
Rabin Karp algorithm matches the hash value of the pattern with the hash value of current substring of text, and if the 
hash values match then only it starts matching individual characters. So Rabin Karp algorithm needs to calculate hash 
values for following strings.

1) Pattern itself.
2) All the substrings of text of length m.

Since we need to efficiently calculate hash values for all the substrings of size m of text, we must have a hash function 
which has following property.
Hash at the next shift must be efficiently computable from the current hash value and next character in text or we can say 
hash(txt[s+1 .. s+m]) must be efficiently computable from hash(txt[s .. s+m-1]) and 
txt[s+m] i.e., hash(txt[s+1 .. s+m])= rehash(txt[s+m], hash(txt[s .. s+m-1]) and rehash must be O(1) operation.

The hash function suggested by Rabin and Karp calculates an integer value. The integer value for a string is numeric 
value of a string. For example, if all possible characters are from 1 to 10, the numeric value of “122” will be 122. 
The number of possible characters is higher than 10 (256 in general) and pattern length can be large. So the numeric 
values cannot be practically stored as an integer. Therefore, the numeric value is calculated using modular arithmetic 
to make sure that the hash values can be stored in an integer variable (can fit in memory words). To do rehashing, we 
need to take off the most significant digit and add the new least significant digit for in hash value. Rehashing is done 
using the following formula.

hash( txt[s+1 .. s+m] ) = d ( hash( txt[s .. s+m-1]) – txt[s]*h ) + txt[s + m] ) mod q

hash( txt[s .. s+m-1] ) : Hash value at shift s.
hash( txt[s+1 .. s+m] ) : Hash value at next shift (or shift s+1)
d: Number of characters in the alphabet
q: A prime number
h: d^(m-1)
 * Time complexity in worst case O(n^2)(depends on hash function)
 * Space complexity O(1)
 *
 * References
 * https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
 */
public class RabinKarpSearch {

    private int prime = 101;
    
    public int patternSearch(char[] text, char[] pattern){
        int m = pattern.length;
        int n = text.length;
        long patternHash = createHash(pattern, m - 1);
        long textHash = createHash(text, m - 1);
        for (int i = 1; i <= n - m + 1; i++) {
            if(patternHash == textHash && checkEqual(text, i - 1, i + m - 2, pattern, 0, m - 1)) {
                return i - 1;
            }
            if(i < n - m + 1) {
                textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
            }
        }
        return -1;
    }
    
    private long recalculateHash(char[] str,int oldIndex, int newIndex,long oldHash, int patternLen) {
        long newHash = oldHash - str[oldIndex];
        newHash = newHash/prime;
        newHash += str[newIndex]*Math.pow(prime, patternLen - 1);
        return newHash;
    }
    
    private long createHash(char[] str, int end){
        long hash = 0;
        for (int i = 0 ; i <= end; i++) {
            hash += str[i]*Math.pow(prime,i);
        }
        return hash;
    }
    
    private boolean checkEqual(char str1[],int start1,int end1, char str2[],int start2,int end2){
        if(end1 - start1 != end2 - start2) {
            return false;
        }
        while(start1 <= end1 && start2 <= end2){
            if(str1[start1] != str2[start2]){
                return false;
            }
            start1++;
            start2++;
        }
        return true;
    }
    
    public static void main(String args[]){
        RabinKarpSearch rks = new RabinKarpSearch();
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "sharRoy".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "Roy".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "shas".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "usha".toCharArray()));
        System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "Tus".toCharArray()));
    }
}
