/**
 * 
 */
package com.os;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author Ojas Juneja
 *
 */
public class ProducerConsumerSemaphore {
	public static int maxSize = 4;
	public static Semaphore semaProd = new Semaphore(1);
	public static Semaphore semaCons = new Semaphore(0);

	public static class Producer implements Runnable {
		Queue<Integer> queue;

		public Producer(Queue<Integer> queue) {
			this.queue = queue;
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

		public void produce(int i) {
			try {
				semaProd.acquire();
				System.out.println("produced " + i);
				queue.offer(i);
				semaCons.release();
				Thread.sleep(50);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

	}

	public static class Consumer implements Runnable {
		Queue<Integer> queue;

		public Consumer(Queue<Integer> queue) {
			this.queue = queue;
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
			try {
				semaCons.acquire();
				System.out.println("Consumed: " + queue.poll());
				semaProd.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<Integer>();
		Producer producer = new Producer(q);
		Consumer consumer = new Consumer(q);
		Thread tp = new Thread(producer);
		Thread tc = new Thread(consumer);
		tp.start();
		tc.start();
	}
}
