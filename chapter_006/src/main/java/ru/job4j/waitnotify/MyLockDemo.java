package ru.job4j.waitnotify;

public class MyLockDemo {

    private final MyLock lock = new MyLock();

    private int count = 0;

    /**
     * Method inc field.
     * @throws InterruptedException if interrupt
     */
    public void increment() throws InterruptedException {
        lock.lock();
        count++;
        lock.unlock();
    }

    /**
     * Getter for count field.
     * @return count
     */
    public int getCount() {
        return this.count;
    }

    public static void main(String[] args) {
        MyLockDemo lockDemo = new MyLockDemo();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(String.format("Thread %s goes working", Thread.currentThread().getName()));
                        lockDemo.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(String.format("Thread %s goes working", Thread.currentThread().getName()));
                        lockDemo.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(String.format("Thread %s goes working", Thread.currentThread().getName()));
                        lockDemo.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lockDemo.getCount());
    }
}
