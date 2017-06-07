package com.troy.number;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/find-the-largest-number-multiple-of-3/
 * Given an array of non-negative integers. Find the largest multiple of 3 that can be formed from array elements.

For example, if the input array is {8, 1, 9}, the output should be “9 8 1”, and if the input array is {8, 1, 7, 6, 0},
 output should be “8 7 6 0”.
 
 Solution:
 1) create 3 queues: 
 	queue0: all elements that a[i]%3=0
 	queue1: all elements that a[i]%3=1
 	queue2: all elements that a[i]%3=2
 2) Get total sum of all elements
 3) If sum%3==1 then remove 1 elements from queue1. If queue1 is empty then remove 2 elements from queue2
 	
 */
public class LargestMultipleOf3inArray {

    public void swap(int input[],int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    private void reverse(int input[]){
        for(int i=0; i < input.length/2; i++){
            swap(input,i, input.length-1-i);
        }
    }
    
    public int[] largestMultiple(int input[]){
        Arrays.sort(input);
        int output[];
        Queue<Integer> q0 = new LinkedList<Integer>();
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        
        int sum = 0;
        for(int i=0; i < input.length; i++){
            if(input[i] % 3 == 0){
                q0.add(input[i]);
            }
            if(input[i] % 3 == 1){
                q1.add(input[i]);
            }
            if(input[i] % 3 == 2){
                q2.add(input[i]);
            }
            sum += input[i];
        }
    
        if(sum % 3 == 0){
            output = new int[input.length];
            for(int i=0; i < input.length; i++){
                output[input.length-1-i] = input[i];
            }
            return output;
        }
        else if(sum % 3 == 1){
            int len = input.length;
            if(q1.size() > 0){
                q1.poll();
                len--;
            }else if (q2.size() > 1){
                q2.poll();
                q2.poll();
                len -= 2;
            }else{
                return null;
            }
            output = new int[len];
            int i=0;
            while(q0.size() > 0){
                output[i] = q0.poll();
                i++;
            }
            while(q1.size() > 0){
                output[i] = q1.poll();
                i++;
            }
            while(q2.size() > 0){
                output[i] = q2.poll();
                i++;
            }
            Arrays.sort(output);
            reverse(output);
        }else{
            int len = input.length;
            if(q2.size() > 0){
                q2.poll();
                len--;
            }else if(q1.size() > 1){
                q1.poll();
                q1.poll();
                len -= 2;
            }else{
                return null;
            }
            output = new int[len];
            int i=0;
            while(q0.size() > 0){
                output[i] = q0.poll();
                i++;
            }
            while(q1.size() > 0){
                output[i] = q1.poll();
                i++;
            }
            while(q2.size() > 0){
                output[i] = q2.poll();
                i++;
            }
            Arrays.sort(output);
            reverse(output);
        }
        return output;
    }
    
    public static void main(String args[]){
        int input[] = {8,9,0,2,2,2};
        LargestMultipleOf3inArray lma = new LargestMultipleOf3inArray();
        int output[] = lma.largestMultiple(input);
        for(int a : output){
            System.out.print(a + " ");
        }
    }
}

