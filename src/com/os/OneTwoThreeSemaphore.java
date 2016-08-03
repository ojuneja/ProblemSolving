/**
 * 
 */
package com.os;

import java.util.concurrent.Semaphore;

/**
 * @author Ojas Juneja
 *
 */
public class OneTwoThreeSemaphore {
	static Semaphore s1 = new Semaphore(1);
	static Semaphore s2 = new Semaphore(0);
	static Semaphore s3 = new Semaphore(0);

	public static class ClassNumber1 implements Runnable {
		public ClassNumber1() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				try {
					printNumber1();
					Thread.sleep(50);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}

		private void printNumber1() throws InterruptedException {
			s1.acquire();
			System.out.println(1);
			s1.release();
		}

	}
	
	public static class ClassNumber2 implements Runnable {
		public ClassNumber2() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				try {
					printNumber2();
					Thread.sleep(50);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}

		private void printNumber2() throws InterruptedException {
			s1.acquire();
			System.out.println(2);
			s1.release();
		}

	}
	
	public static class ClassNumber3 implements Runnable {
		public ClassNumber3() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				try {
					printNumber3();
					Thread.sleep(50);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}

		private void printNumber3() throws InterruptedException {
			s1.acquire();
			System.out.println(3);
			s1.release();
		}

	}
	
	public static void main(String [] args) throws InterruptedException
	{
		ClassNumber1 one = new ClassNumber1();
		ClassNumber2 two = new ClassNumber2();
		ClassNumber3 three = new ClassNumber3();
		Thread t1 = new Thread(one);
		Thread t2 = new Thread(two);
		Thread t3 = new Thread(three);
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
	}
}
