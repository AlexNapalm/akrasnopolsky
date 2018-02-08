package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;

@ThreadSafe
public class ThreadPool {
    /**
     * List of tasks.
     */
    @GuardedBy("this.tasks")
    private final LinkedList<Work> tasks;

    /**
     * Array - pool of available threads
     */
    private final Thread[] threads;

    private volatile boolean isStopped = false;

    /**
     * Number of cores.
     */
    private final int cores;

    /**
     * Constructs thread pool.
     */
    public ThreadPool() {
        this.cores = Runtime.getRuntime().availableProcessors();
        this.tasks = new LinkedList<>();
        this.threads = new Thread[this.cores];
    }

    private void init() {
        for (int i = 0; i < this.cores; i++) {
            threads[i] = new WorkingThread();
            threads[i].start();
        }
    }

    /**
     * Adds work to list of tasks.
     * @param work work.
     */
    public void add(Work work) {
        synchronized (this.tasks) {
            tasks.addLast(work);
            tasks.notify();
        }
    }

    /**
     * Stops all threads in the pool.
     */
    public void stop() {
        this.isStopped = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public class WorkingThread extends Thread {
        public void run() {
            while (!isStopped) {
                try {
                    Work work;
                    synchronized (tasks) {
                        while (tasks.isEmpty()) {
                            tasks.wait();
                        }
                        work = tasks.removeFirst();
                    }
                    work.fill();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        pool.init();
        Work work = new Work();
        for (int i = 0; i < 100; i++) {
            pool.add(work);
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.stop();
    }
}
