/**
 * 
 */
package com.troy.number;

/**
 * 
 * count(9)=1
 * count(99)=9*(count(9))+10
 * count(999)=9*(count(99))*100
 *
 */
public class CountNumbersContaining4 {

	int count(int n){
		if(n<4) return 0;
		if(n<10) return 1;

		//count length of digits for 326, len=2
		int len=(int)(Math.log(n)/Math.log(10));

		int power=(int)Math.pow(10, len);
		//get msd, for 326, msd=3
		int msd=n/power;

		//get count by length, for 1=1, 2(0-99)=19
		int[] dp=new int[len+1];
		dp[0]=0; dp[1]=1;
		for (int i = 2; i <= len; i++) {
			dp[i]=9*dp[i-1]+(int)Math.pow(10, i-1);
		}

		if(msd<4){
			//for 326 =0-299 + count(26)
			return msd*dp[len] + count(n%power);
		}
		else if(msd==4){
			//for 426= 0-399 + 26
			return msd*dp[len] + n%power;
		}
		//msd>4
		//for 626=(0-399+500-599)+100+count(26)
		return (msd-1)*dp[len]+power+count(n%power);


	}

	public static void main(String[] args) {

		System.out.println(new CountNumbersContaining4().count(87));
		System.out.println(new CountNumbersContaining4().count(626));

	}

}
