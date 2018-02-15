package ru.job4j.waitnotify;

public class MyLock {

    private final Object lock = new Object();
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockCount = 0;

    public void lock() {
        synchronized (this.lock) {
            Thread callingThread = Thread.currentThread();

            while (isLocked && lockedBy != callingThread) {
                try {
                    this.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.isLocked = true;
            this.lockedBy = Thread.currentThread();
            lockCount++;
        }
    }

    public void unlock() {
        synchronized (this.lock) {
            if (Thread.currentThread() == lockedBy) {
                lockCount--;

                if (lockCount == 0) {
                    isLocked = false;
                    lockedBy = null;
                    notifyAll();
                }
            }
        }
    }
}