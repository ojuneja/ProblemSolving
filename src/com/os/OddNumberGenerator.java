/**
 * 
 */
package com.os;

import java.util.concurrent.Semaphore;

/**
 * @author Ojas Juneja
 *
 */
public class OddNumberGenerator {

	public static class OddNumberGenerate {
		Semaphore s;
		int n = 1;

		OddNumberGenerate(int number) {
			s = new Semaphore(number);
		}

		private int getOdd() throws InterruptedException {
			s.acquire();
			n++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			n++;
			s.release();
			return n;
		}
	}

	public static class OddNumberPrint implements Runnable {
		OddNumberGenerate o;

		OddNumberPrint(OddNumberGenerate o) {
			this.o = o;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			printOddNumber();
		}

		public void printOddNumber() {
			for (int i = 0; i < 20; i++) {
				try {
					System.out.println(o.getOdd());
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		OddNumberGenerate generator = new OddNumberGenerate(1);
		OddNumberPrint print1 = new OddNumberPrint(generator);
		OddNumberPrint print2 = new OddNumberPrint(generator);
		Thread t1 = new Thread(print1);
		Thread t2 = new Thread(print2);
		t1.start();
		t2.start();
	}
}
