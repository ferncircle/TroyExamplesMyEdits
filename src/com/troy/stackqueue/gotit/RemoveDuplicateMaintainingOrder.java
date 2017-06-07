package com.troy.stackqueue.gotit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;

/**
 * Date 02/29/2016
 * @author Tushar Roy
 *
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once 
 * and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

Solution:
1) Count occurances of each char in map
2) Maintain a stack that keeps chars in descending order from top 
3) Maintain Visited Set 
4) For each new char, keep removing the element from top of stack if 
	a) top element is greater than cur element
	b) top element has still more chars left
   Insert new char at top of stack
   
Note that this is similar to RemoveKDigits problem.

 *
 * Reference:
 * https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateMaintainingOrder {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) 
            count.compute(s.charAt(i), (key, val) -> val==null?1:val+1);
        

        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            count.put(curChar, count.get(curChar) - 1);
            if (visited.contains(curChar)) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > curChar && count.get(stack.peek()) > 0) {
                visited.remove(stack.peek());
                stack.pop();
            }

            stack.push(curChar);
            visited.add(curChar);
        }

        StringBuffer buff = new StringBuffer();
        while (!stack.isEmpty()) {
            buff.append(stack.pop());
        }
        return buff.reverse().toString();
    }

    public static void main(String args[]) {
    	assertThat(new RemoveDuplicateMaintainingOrder().removeDuplicateLetters("bcabc"), is("abc"));
        
        assertThat(new RemoveDuplicateMaintainingOrder().removeDuplicateLetters("cbacdcbc"), is("acdb"));
        System.out.println("all tests passed");
    }
}
