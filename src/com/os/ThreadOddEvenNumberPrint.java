/**
 * 
 */
package com.os;

/**
 * @author Ojas Juneja
 *
 */
public class ThreadOddEvenNumberPrint {

	static boolean flag = true;

	public static class Odd implements Runnable {
		Object lock;
		int max = 50;

		Odd(Object lock) {
			this.lock = lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			printOdd();
		}

		public void printOdd() {
			for (int i = 1; i <= max; i += 2) {
				try {
					synchronized (lock) {
						if (flag) {
							System.out.println("Odd thread is waiting!!!");
							lock.wait();
						}
						System.out.println("Odd: " + i);
						flag = true;
						lock.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class Even implements Runnable {

		Object lock;
		int max = 50;

		Even(Object lock) {
			this.lock = lock;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			printEven();
		}

		public void printEven() {
			for (int i = 0; i <= max; i += 2) {
				try {
					synchronized (lock) {
						if (!flag) {
							System.out.println("Even thread is waiting!!!");
							lock.wait();
						}
						System.out.println("Even: " + i);
						flag = false;
						lock.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Object object = new Object();
		Odd odd = new Odd(object);
		Even even = new Even(object);
		Thread tOdd = new Thread(odd);
		Thread tEven = new Thread(even);
		tOdd.start();
		tEven.start();
	}

}
