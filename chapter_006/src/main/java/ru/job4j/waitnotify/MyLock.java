package ru.job4j.waitnotify;

public class MyLock {

    private boolean isLocked = false;

    public final synchronized void lock() throws InterruptedException {
        while (this.isLocked) {
            this.wait();
        }
        this.isLocked = true;
    }

    public final synchronized void unlock() {
        this.isLocked = false;
        this.notifyAll();
    }
}