package com.boot.jdk.myTest;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        /**
         *
         */
        lock.writeLock().lock();

        /**
         * AQS - acquireShared
         */
        lock.readLock().lock();

    }
}
