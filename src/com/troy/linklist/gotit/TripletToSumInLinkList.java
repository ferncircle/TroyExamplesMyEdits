package com.troy.linklist.gotit;

import com.troy.linklist.LinkList;
import com.troy.linklist.Node;

/**
 * http://www.geeksforgeeks.org/find-a-triplet-from-three-linked-lists-with-sum-equal-to-a-given-number/
 * 
 * Given three linked lists, say a, b and c, find one node from each list such that the sum of the values of the nodes is 
 * equal to a given number. 
For example, if the three linked lists are 12->6->29, 23->5->8 and 90->20->59, and the given number is 101, the output 
should be tripel “6 5 90”.

In the following solutions, size of all three linked lists is assumed same for simplicity of analysis. 

Solution: 1) Treat it as a problem of finding two numbers in sorted array that sum to given number
2) Sort list 2 ascending and list 3 descending 
3) Take each item in list1 and find its counterpart in list2 and list3




 * Test case
 * empty list
 * list with 0 1 or more nodes
 * negative sum
 * 0 sum
 * positive sum
 */
public class TripletToSumInLinkList {

    public void printTriplet(Node head1, Node head2, Node head3,int sum){
        if(head1 == null || head2 == null || head3 == null){
            return;
        }
        
        MergeSortLinkList msll = new MergeSortLinkList();
        head2 = msll.sort(head2, true);
        head3 = msll.sort(head3, false);
        
        while(head1 != null){
            int newSum = sum - head1.data;
            Node tempHead2 = head2;
            Node tempHead3 = head3;
            while(tempHead2 != null && tempHead3 != null){
                if(tempHead2.data + tempHead3.data == newSum){
                    System.out.println(head1.data + " " + tempHead2.data + " " + tempHead3.data);
                    break;
                }
                else if(tempHead2.data + tempHead3.data < newSum){
                    tempHead2 = tempHead2.next;
                }else{
                    tempHead3 = tempHead3.next;
                }
            }
            head1 = head1.next;
        }
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(8, head);
        head = ll.addNode(-3, head);
        head = ll.addNode(14, head);
        
        Node head1 = null;
        head1 = ll.addNode(-1, head1);
        head1 = ll.addNode(22, head1);
        head1 = ll.addNode(31, head1);
        head1 = ll.addNode(11, head1);
        
        Node head2 = null;
        head2 = ll.addNode(5, head2);
        head2 = ll.addNode(7, head2);
        head2 = ll.addNode(3, head2);
        head2 = ll.addNode(1, head2);
        
        TripletToSumInLinkList tts  = new TripletToSumInLinkList();
        tts.printTriplet(head, head1, head2, 20);
    }
}
