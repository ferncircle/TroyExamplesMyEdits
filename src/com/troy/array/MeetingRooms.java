package com.troy.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Date 05/01/2016
 * @author Tushar Roy
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 *
 *Solution: 
 *We want to count max overlapping intervals at any given point(seems like queue or stack to store intervals)
 *Consider the interval: (i1    (i2  i1)    (i3   i2)   i3)
 *Note that interval i2 overlaps both i1 and i3 (that's why using stack to store interval won't work)
 *When we are dealing with interval i3, we want to remove i1, that's why we need priority queue
 *
 *1) Sort array by start time
 *2) Use priority queue for storing overlapping intervals
 *3) If first item in queue is outside the current interval then remove it from the queue
 * 
 * https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRooms {	

	public int minMeetingRooms(Interval[] intervals){
		Arrays.sort(intervals, (a,b)->a.start-b.start);

		PriorityQueue<Integer> st=new PriorityQueue<Integer>((a,b)->intervals[a].end-intervals[b].end);
		int min=0;
		int i=0;
		while(i<intervals.length){
			Interval cur=intervals[i];
			if(st.isEmpty() || cur.start<intervals[st.peek()].end){
				st.add(i);    			
				min=Math.max(min, st.size());
				i++;
			}else{
				st.remove();
			}
		}

		return min;
	}



	public int minMeetingRooms1(Interval[] intervals) {
		int[] start = new int[intervals.length];
		int[] end = new int[intervals.length];

		for (int i = 0; i < intervals.length; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}

		Arrays.sort(start);
		Arrays.sort(end);

		int j = 0;
		int rooms = 0;
		for (int i = 0; i < start.length; i++) {
			if (start[i] < end[j]) {
				rooms++;
			} else {
				j++;
			}
		}
		return rooms;
	}
	
	
	
	/**
	 *This is not correct
	 */
	public int minMeetingRooms2(Interval[] intervals) {
		int min=1;
		int rooms=1;
		Integer[] times=new Integer[intervals.length*2];
		for (int i = 0; i < intervals.length; i++) {
			times[2*i]=intervals[i].start;
			times[2*i+1]=-intervals[i].end;
		}
		Arrays.sort(times, (a,b)->Math.abs(a)-Math.abs(b));
		
		for (int i = 1; i < times.length; i++) {
			if(times[i]>0)
				rooms++;
			else 
				rooms--;
			
			min=Math.max(min, rooms);
		}
		return min;
	}

	
	public static void main(String[] args){

		MeetingRooms meetingRooms = new MeetingRooms();
		ArrayList<Interval> list=new ArrayList<MeetingRooms.Interval>();
		list.add(new MeetingRooms.Interval(7,29));
		list.add(new MeetingRooms.Interval(19,162));
		list.add(new MeetingRooms.Interval(50,157));
		list.add(new MeetingRooms.Interval(59,107));
		list.add(new MeetingRooms.Interval(64,122));
		
		Random r =new Random();
		for (int i = 0; i < 1000; i++) {
			int start=r.nextInt(100);
			int end=start+r.nextInt(120);
			list.add(new MeetingRooms.Interval(start,end));
		}
		System.out.println( meetingRooms.minMeetingRooms1(list.toArray(new Interval[list.size()])));
		System.out.println( meetingRooms.minMeetingRooms(list.toArray(new Interval[list.size()])));
		System.out.println( meetingRooms.minMeetingRooms2(list.toArray(new Interval[list.size()])));
		
		System.out.println("All cases passed");
	}
	

	public static class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
		@Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
		}
	}
}
