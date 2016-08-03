/**
 * 
 */
package com.os;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/*
leetcode problem number: lc
geeksforgeeks problem number: gk
other: 
*/
/**
 * @author Ojas Juneja
 *
 */
public class ProducerConsumerSemaphore2 {

	public static int maxSize = 4;

	public static class Producer implements Runnable {
		Queue<Integer> q;

		public Producer(Queue<Integer> q) {
			this.q = q;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			for (int i = 0; i < maxSize; i++) {
				produce(i);
			}
		}

		public void produce(int number) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			while (q.size() == maxSize) {
				try {
					synchronized (q) {
						q.wait();
					}
				} catch (InterruptedException ie) {

				}
			}
			synchronized (q) {
				System.out.println("Produced: " + number);
				q.offer(number);
				q.notifyAll();
			}
		}

	}

	public static class Consumer implements Runnable {
		Queue<Integer> q;

		public Consumer(Queue<Integer> q) {
			this.q = q;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				consume();
			}
		}

		public void consume() {
			while(q.isEmpty()) {
				try {
					synchronized (q) {
						q.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (q) {
				System.out.println("Consumed: " + q.poll());
			}
		}
	}

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<Integer>();
		Producer p = new Producer(q);
		Consumer c = new Consumer(q);
		Thread tp = new Thread(p);
		Thread tc = new Thread(c);
		tp.start();
		tc.start();
	}
}
