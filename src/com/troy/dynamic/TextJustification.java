package com.troy.dynamic;

import java.util.Arrays;

import com.troy.utils.ArrayUtils;

/**
 * Date 05/07/2015
 * @author tusroy
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * Video link - https://youtu.be/RORuwHiblPc
 * 
 * Given a sequence of words, and a limit on the number of characters that can be put in one line (line width). 
 * Put line breaks in the given sequence such that the lines are printed neatly. Assume that the length of each word is 
 * smaller than the line width.

The extra spaces includes spaces put at the end of every line except the last one.  
The problem is to minimize the following total cost.
 Cost of a line = (Number of extra spaces in the line)^3
 Total Cost = Sum of costs for all lines

For example, consider the following string and line width M = 15
 "Geeks for Geeks presents word wrap problem" 
     
Following is the optimized arrangement of words in 3 lines
Geeks for Geeks
presents word
wrap problem 

The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively. 
So optimal value of total cost is 0 + 2*2 + 3*3 = 13
Please note that the total cost function is not sum of extra spaces, but sum of cubes (or square is also used) of extra 
spaces. The idea behind this cost function is to balance the spaces among lines. For example, consider the following two 
arrangement of same set of words:

1) There are 3 lines. One line has 3 extra spaces and all other lines have 0 extra spaces. Total extra spaces = 3 + 0 + 0 = 3.
 Total cost = 3*3*3 + 0*0*0 + 0*0*0 = 27.

2) There are 3 lines. Each of the 3 lines has one extra space. Total extra spaces = 1 + 1 + 1 = 3. 
Total cost = 1*1*1 + 1*1*1 + 1*1*1 = 3.

Total extra spaces are 3 in both scenarios, but second arrangement should be preferred because extra spaces are balanced in 
all three lines. The cost function with cubic sum serves the purpose because the value of total cost in second scenario is 
less.
 * Solution:
 * It's similar to Number partition problem except here there are no fixed number partitions so, we need only
 * one-dimentional array for dp
 		** In number partition, we precompute the cost(sum) between every interval i,j, Here also, we'll precompute
 			cost between every interval i,j on one single line
 * 
 * Let minCost[i]=minimum cost to put words from 1 to i among all lines 
 * 		(what we want to return, notice it's only one dimentional as opposed to number partition problem)
 * cost[i][j]=cost for putting words from i to j on one line (cube of extra spaces after putting words i to j 
 * 					on single line)
 * 
 * minCost[j]= min(minCost[i-1]+cost[i][j]) for i=1 to j
 * 
 * Roy T: 
 * Badness - We define badness has square of empty spaces in every line. So 2 empty space
 * on one line gets penalized as 4 (2^2) while 1 each empty space on 2 lines gets
 * penalized as 2(1 + 1). So we prefer 1 empty space on different lines over 2 empty space on
 * one line.
 * 
 * For every range i,j(words from i to j) find the cost of putting them on one line. If words 
 * from i to j cannot fit in one line cost will be infinite. Cost is calculated as square of
 * empty space left in line after fitting words from i to j.
 * 
 * Then apply this formula to get places where words need to be going on new line.
 * minCost[i] = minCost[j] + cost[i][j-1]
 * Above formula will try every value of j from i to len and see which one gives minimum 
 * cost to split words from i to len.
 * 
 * Space complexity is O(n^2)
 * Time complexity is O(n^2)
 * 
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 */
public class TextJustification {

    public String justify(String words[], int width) {
        
        int spaceLeft[][] = new int[words.length][words.length];
        System.out.println("line width="+width);
        System.out.println(Arrays.toString(words));
        System.out.println();
        //precompute step
        //next 2 for loop is used to calculate cost of putting words from
        //i to j in one line. If words don't fit in one line then we put
        //Integer.MAX_VALUE there.
        for(int i=0 ; i < words.length; i++){
            spaceLeft[i][i] = width - words[i].length();
            for(int j=i+1; j < words.length; j++){
                spaceLeft[i][j] = spaceLeft[i][j-1] - words[j].length() - 1; 
            }
        }
        ArrayUtils.print(spaceLeft);
        System.out.println();
        for(int i=0; i < words.length; i++){
            for(int j=i; j < words.length; j++){
                if(spaceLeft[i][j] < 0){
                    spaceLeft[i][j] = Integer.MAX_VALUE;
                }else{
                    spaceLeft[i][j] = (int)Math.pow(spaceLeft[i][j], 2);
                }
            }
        }
        
        ArrayUtils.print(spaceLeft);
        System.out.println();
        //minCost from i to len is found by trying
        //j between i to len and checking which
        //one has min value
        int minCost[] = new int[words.length];
        int result[] = new int[words.length];
        for(int i = words.length-1; i >= 0 ; i--){
            minCost[i] = spaceLeft[i][words.length-1];
            result[i] = words.length;
            for(int j=words.length-1; j > i; j--){
                if(spaceLeft[i][j-1] == Integer.MAX_VALUE){
                    continue;
                }
                if(minCost[i] > minCost[j] + spaceLeft[i][j-1]){
                    minCost[i] = minCost[j] + spaceLeft[i][j-1];
                    result[i] = j;
                }
            }
        }
        int i = 0;
        int j;
        
        System.out.println("Minimum cost is " + minCost[0]);
        System.out.println("\n");
        //finally put all words with new line added in 
        //string buffer and print it.
        StringBuilder builder = new StringBuilder();
        do{
            j = result[i];
            for(int k=i; k < j; k++){
                builder.append(words[k] + " ");
            }
            builder.append("\n");
            i = j;
        }while(j < words.length);
        
        return builder.toString();
    }
    
    public static void main(String args[]){
        String words1[] = {"Tushar","likes","to","write","code","at", "free", "time"};
        TextJustification awl = new TextJustification();
        System.out.println(awl.justify(words1, 12));
    }
}
