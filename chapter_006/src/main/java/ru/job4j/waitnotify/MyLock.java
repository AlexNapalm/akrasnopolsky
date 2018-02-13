package ru.job4j.waitnotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private final Object lock = new Object();

    private boolean isLocked = false;

    private Thread thread = null;

    @Override
    public void lock() {
        synchronized (this.lock) {
            while (this.isLocked) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.isLocked = true;
            this.thread = Thread.currentThread();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this.lock) {
            if (this.thread != null) {
                this.thread.interrupt();
                this.isLocked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public synchronized boolean tryLock() {
        boolean lock = false;
        synchronized (this.lock) {
            if (!this.isLocked) {
                this.isLocked = true;
                lock = true;
                this.thread = Thread.currentThread();
            }
        }
        return lock;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        boolean lock = false;
        long waitTime = System.currentTimeMillis() + unit.toMillis(time);
        synchronized (this.lock) {
            while (this.isLocked && waitTime > System.currentTimeMillis()) {
                this.lock.wait(waitTime - System.currentTimeMillis());
            }
            if (!this.isLocked) {
                this.isLocked = true;
                lock = true;
                this.thread = Thread.currentThread();
            }
        }
        return lock;
    }

    @Override
    public void unlock() {
        synchronized (this.lock) {
            this.isLocked = false;
        }
        this.thread = null;
        this.notifyAll();
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}