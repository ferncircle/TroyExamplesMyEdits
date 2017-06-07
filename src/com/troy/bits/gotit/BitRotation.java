package com.troy.bits.gotit;

/**
 * http://www.geeksforgeeks.org/rotate-bits-of-an-integer/
 * 
 * Bit Rotation: A rotation (or circular shift) is an operation similar to shift except that the bits that fall off 
 * at one end are put back to the other end.

In left rotation, the bits that fall off at left end are put back at right end.

In right rotation, the bits that fall off at right end are put back at left end.

Example:
Let n is stored using 8 bits. Left rotation of n = 11100101 by 3 makes n = 00101111 (Left shifted by 3 and first 3
 bits are 
put back in last ). 

Solution:
1) Shift n left by d positions 
2) Shift n right by (8-d) positions(assuming byte size)
3) OR above two operations
 */
public class BitRotation {

    public byte rotateLeft(byte num, int d){
        return (byte)((num << d) | (num >> (8-d)));
    }
    
    public static void main(String args[]){
        BitRotation br = new BitRotation();
        System.out.println(br.rotateLeft((byte)28, 2));
    }
}
