package com.troy.number;

/**
 * http://www.geeksforgeeks.org/count-numbers-that-dont-contain-3/
 * 
 * Given a number n, write a function that returns count of numbers from 1 to n that don’t contain digit 3 in their decimal 
 * representation.

Examples:

Input: n = 10
Output: 9 

Input: n = 45
Output: 31 
// Numbers 3, 13, 23, 30, 31, 32, 33, 34, 
// 35, 36, 37, 38, 39, 43 contain digit 3. 

Input: n = 578
Ouput: 385

Solution: 
1) break the problem at multiples of 10* and do it recursively
2) 	 			578	
			/		  \
		5* 100			78
						/	\
					7*10 	8

count(87)=count(8)*count(10-1)+ count(8)+count(7)
count(256)=count(2)*count(100-1)+count(2)+count(56)



 */
public class CountNumbersNotIncluding4 {

	public int count(int n){
        if(n < 4){
            return n;
        }
        if( n >=4 && n <=10){
        	return n-1;
        }
        

		//count length of digits for 326, len=2
		int len=(int)(Math.log(n)/Math.log(10));

		int pow=(int)Math.pow(10, len);
        
        int msd = n/pow;
        if(msd == 4){
            return count(msd*pow -1);
        }else{
            //suppose number is 276. So this becomes count(2)*count(99) +
            //count(2) + count(76)
            //reason we split this way rather than count(2)*count(100) is because
            //count(100) can go into infinite loop
            return count(msd)*count(pow-1) + count(msd) + count(n%pow);
        }
    }
	    
    public static void main(String args[]){
        CountNumbersNotIncluding4 cn = new CountNumbersNotIncluding4();
        int n=9999;
        System.out.println(cn.count(n));
        System.out.println((cn.count(n)+new CountNumbersContaining4().count(n))+"="+n);
    }
}
