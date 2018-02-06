package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Size limit.
     */
    private static final int MAX_SIZE = 5;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * Adds element.
     * @param value element to add.
     */
    public synchronized void offer(T value) {
        while (this.isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.queue.offer(value);
        System.out.println("Item added, size: " + size());
        notify();

    }
    /**
     * Returns one element.
     * @return element.
     */
    @GuardedBy("this")
    public synchronized T peek() {
        while (this.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T result = this.queue.poll();
        notify();
        System.out.println("Item bought, size: " + size());
        return result;
    }
    @GuardedBy("this")
    public int size() {
        return this.queue.size();
    }
    @GuardedBy("this")
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    @GuardedBy("this")
    public boolean isFull() {
        return this.size() >= MAX_SIZE;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    queue.offer(i);
                }
            }
        };
        Thread customer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    queue.peek();
                }
            }
        };
        producer.join();
        customer.join();

        customer.start();
        producer.start();
    }
}
