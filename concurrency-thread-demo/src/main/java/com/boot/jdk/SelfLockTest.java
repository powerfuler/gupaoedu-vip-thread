package com.boot.jdk;

import java.util.concurrent.locks.Lock;

public class SelfLockTest {
    static Lock lock = new SelfLock();
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(() -> {
            testLock();
        });
        Thread B = new Thread(() -> {
            testLock();
        });
        A.setName("I am A");
        B.setName("I am B");
        A.start();
        Thread.sleep(100);
        B.start();
    }
    public static void testLock() {
        System.out.println("I want IN。。。。");
        lock.lock();
        try {
            System.out.println("我获取到锁了，哈哈！线程名称 = "
                    + Thread.currentThread().getName());
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
