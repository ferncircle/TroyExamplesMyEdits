package com.troy.multithreaded;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class that has 5 threads - two to increment the myVar variable, two to decrement the myVar variable and one to print the value of myVar.
 * Implement increment(), decrement() and printVar() methods such that the following series is printed:
 * 0 1 2 3 4 5 4 3 2 1 0 1 2 3 4 5 4 3 2 1 ... (repeating)
 */
public class PrintInSequence {

	private volatile int val = 0;
	private volatile boolean printing = true;
	private volatile boolean isIncreasing = true;
	private Lock lock = new ReentrantLock();
	public void increment() {
		while(true){
			lock.lock();
			if (!printing && isIncreasing) {
				val = val + 1;
				if (val == 5) {
					isIncreasing = false;
				}
				printing = true;
			}
			lock.unlock();
		}
	}

	public void decrement() {
		while(true){
			lock.lock();
			if (!printing && !isIncreasing) {
				val = val - 1;
				if (val == 0) {
					isIncreasing = true;
				}
				printing = true;
			}
			lock.unlock();
		}
	}

	//only one thread is calling print. So no contention in updating shouldPrint flag.
	public void printVar() {
		while(true){
			if (printing) {
				System.out.println(val);
				printing = false;
			}
		}
	}

	public static void main(String args[]) {
		PrintInSequence printInSequence = new PrintInSequence();
		new Thread(printInSequence::increment).start();
		new Thread(printInSequence::increment).start();

		//new Thread(printInSequence::runPrint).start();
		new Thread(() -> printInSequence.printVar()).start(); //other way of running it

		new Thread(printInSequence::decrement).start();
		new Thread(printInSequence::decrement).start();
	}

	private void runIncrement() {
		while(true) {
			this.increment();
		}
	}

	private void runPrint() {
		while (true) {
			this.printVar();
		}
	}

	private void runDecrement() {
		while (true) {
			this.decrement();
		}
	}
}
