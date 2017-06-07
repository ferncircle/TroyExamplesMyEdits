package com.troy.number;

/**
 * http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
 * 
Check if AP EXISTS for 3 numbers
{1,3,6,7,10,11,15}

Solution:
1) Consider current number as middle and have two pointers to move in both directions
2) Decision to move which pointer to move is based input[i]*2 > input[j] + input[k]

Yes: 
 */
public class ArithemeticProgressionExists {

    public boolean exists(int input[]){
        
        for(int i=1; i < input.length-1; i++){
            int j = i-1;
            int k = i+1;
            while(j >=0 && k <= input.length-1){
                if(input[i]*2 == input[j] + input[k]){
                    return true;
                }else if(input[i]*2 > input[j] + input[k]){
                    k++;
                }else{
                    j--;
                }
            }
        }
        return false;
    }
    public static void main(String args[]){
        int input[] = {1,3,6,7,10,11,15};
        ArithemeticProgressionExists ape = new ArithemeticProgressionExists();
        System.out.println(ape.exists(input));
    }
}
