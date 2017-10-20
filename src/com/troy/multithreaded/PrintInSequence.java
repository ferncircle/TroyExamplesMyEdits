package com.troy.multithreaded;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A class that has 5 threads - two to increment the myVar variable, two to decrement the myVar variable and one to print the value of myVar.
 * Implement increment(), decrement() and printVar() methods such that the following series is printed:
 * 0 1 2 3 4 5 4 3 2 1 0 1 2 3 4 5 4 3 2 1 ... (repeating)
 */
public class PrintInSequence {

    private volatile int val = 0;
    private volatile boolean printing = true;
    private volatile boolean isIncreasing = true;
    private ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock().writeLock();
    public synchronized void increment() {
        //lock.lock();
        if (!printing && isIncreasing) {
            val = val + 1;
            if (val == 5) {
                isIncreasing = false;
            }
            printing = true;
        }
        //lock.unlock();
    }

    public synchronized void decrement() {
        //lock.lock();
        if (!printing && !isIncreasing) {
            val = val - 1;
            if (val == 0) {
                isIncreasing = true;
            }
            printing = true;
        }
        //lock.unlock();
    }

    //only one thread is calling print. So no contention in updating shouldPrint flag.
    public synchronized void printVar() {
        if (printing) {
            System.out.println(val);
            printing = false;
        }
    }

    public static void main(String args[]) {
        PrintInSequence printInSequence = new PrintInSequence();
        new Thread(printInSequence::runIncrement).start();
        new Thread(printInSequence::runIncrement).start();
        
        //new Thread(printInSequence::runPrint).start();
        new Thread(() -> printInSequence.runPrint()).start(); //other way of running it
        
        new Thread(printInSequence::runDecrement).start();
        new Thread(printInSequence::runDecrement).start();
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
