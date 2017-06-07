package com.troy.dynamic;

import java.util.*;

/**
 * Date 04/03/2016
 * @author Tushar Roy
 *
 *Problem 1) Partitioning the string into palindromes.
 *
 * https://leetcode.com/problems/palindrome-partitioning/
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
 * 
 * 
 Problem 2) https://leetcode.com/problems/palindrome-partitioning-ii/
 Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

Problem 1 Solution: 
1) From current index as start, figure out how many strings are palindromes
2) Recurse from rest of the strings

Problem 2 Solution:
1) Build a dp[length][length] palindrome table that tells if any substring is palindrome O(n^2)
	To build: Use DP interval(gap) for bottom up approach
	if chars at i and j are same then recurse for i+1,j-1
2) For every suffix that is palindrome, find out minimum cut O(n^2)
 */
public class PalindromePartition {

    /*
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * https://leetcode.com/problems/palindrome-partitioning-ii/
     * 
     * Here he has merged both steps into one
     */
    public int minCut(String str){
        if (str.length() == 0) {
            return 0;
        }

        int[] cut = new int[str.length()];
        boolean isPal[][] = new boolean[str.length()][str.length()];
        for (int gap = 1; gap < str.length(); gap++) {
            int min = gap;
            for (int j = 0; j <= gap; j++) {
                if (str.charAt(gap) == str.charAt(j) && (gap <= j + 1 || isPal[gap - 1][j + 1])) {
                    isPal[gap][j] = true;
                    min = Math.min(min, j == 0 ? 0 : 1 + cut[j - 1]);
                }
            }
            cut[gap] = min;
        }
        return cut[str.length() - 1];
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * https://leetcode.com/problems/palindrome-partitioning/
     */
    public List<List<String>> partition(String s) {
        Map<Integer, List<List<String>>> dp = new HashMap<>();
        List<List<String>> result =  partitionUtil(s, dp, 0);
        List<List<String>> r = new ArrayList<>();
        for (List<String> l : result) {
            r.add(l);
        }
        return r;
    }

    private List<List<String>> partitionUtil(String s, Map<Integer, List<List<String>>> dp, int start) {
        if (start == s.length()) {
            List<String> r = new ArrayList<>();
            return Collections.singletonList(r);
        }

        if (dp.containsKey(start)) {
            return dp.get(start);
        }

        List<List<String>> words = new ArrayList<>();
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i) ) {
                continue;
            }
            String newWord = s.substring(start, i + 1);
            List<List<String>> result = partitionUtil(s, dp, i + 1);
            for (List l : result) {
                List<String> l1 = new ArrayList<>();
                l1.add(newWord);
                l1.addAll(l);
                words.add(l1);
            }
        }
        dp.put(start, words);
        return words;
    }

    private  boolean isPalindrome(String str, int r, int t) {
        while(r < t) {
            if (str.charAt(r) != str.charAt(t)) {
                return false;
            }
            r++;
            t--;
        }
        return true;
    }
}
