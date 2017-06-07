package com.troy.string.gotit;

/**
 * Given a string find its lexicographical rank.
 * e.g. for string with characters {x y z}
 * xyz=1
 * xzy=2
 * yxz=3
 * yzx=4
 * zxy=5
 * zyx=6
 *
 *Solution:
 *1) Consider string zxy
 *2) We need to find total number strings before this string. (in this case 4)
 *3) Consider position by position. At each position, how many characters can appear at current position in previous strings. 
 *		Then permute the characters on its right.
 *	e.g. At position 0, chars 'x' and 'y' can appear before (since they are smaller than 'z' i.e. total of 2) and then just permute rest of 
 *		characters (2!)
 *4) Do above step for each position, 
 *5) We need to find total chars on right side smaller than current char for each char. We can use modified merge sort to do that (nLogn) or 
 *	self balancing tree (AVL, red-black) nLogn
 */
public class LexicographicRankInPermutation {


    public int rank(char []str){
        
        int rank =0;
        for(int i=0; i < str.length;i++){
            int totalSmallerCharsOnRight = findNumberOfSmallerCharactersOnRight(i, str);
            int totalCharsOnRight=str.length -i-1;
            rank += factorial(totalCharsOnRight)*totalSmallerCharsOnRight;
        }
        return rank+1;
        
    }
    
    //you can create a AVL tree to efficiently find total number of smaller characters.
    //You can keep size of subtree at root and keep moving left or right
    //depending on the character you looking for
    private int findNumberOfSmallerCharactersOnRight(int index,char []str){
        int count=0;
        for(int i=index+1; i < str.length; i++){
            if(str[i] < str[index]){
                count++;
            }
        }
        return count;
    }
    
    private int factorial(int n){
        int fact = 1;
        for(int i =1; i <=n; i++){
            fact = i*fact;
        }
        return fact;
    }
        
    public static void main(String args[]){
        LexicographicRankInPermutation lrp = new LexicographicRankInPermutation();
        int rank = lrp.rank("zxy".toCharArray());
        System.out.println(rank);
    }
}
