package com.boot.jdk.myTest;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock();
    static ReentrantLock lock1 = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        /*
        * AQS-compareAndSetState
        * acquire
        * addWaiter
        * enq
        * acquireQueued
        * shouldParkAfterFailedAcquire
        * parkAndCheckInterrupt
        * cancelAcquire
        * */
        // acquire 方法是 aqs提供的模板方法，是为了进行锁的获取；tryAcquire 方法是aqs提供的可以复写的方法，主要是完成了加锁
        //状态变化的逻辑（state）；addWaiter 将我们的获取失败的线程放到我们的同步队列里；enq 如果addwaiter第一次没有成功，就
        //进行死循环添加；acquireQueued：这部分其实是通过循环的自我检查，如果当前节点的pred节点是头节点，那么就尝试获取锁；
        //如果不是头节点，就调用 shouldParkAfterFailedAcquire 方法，判断pred节点是否为 SIGNAL 状态，如果是signal状态，自己就好好
        //的等着；如果是 cancell状态，就移除cancell的节点。其他状态的节点，会通过cas操作替换为 SIGNAL状态。
        lock.lock();

        /**
         *AQS - tryAcquireNanos
         * doAcquireNanos
         * shouldParkAfterFailedAcquire
         *
         */
        //tryLock（） 为了进行一次性的获取锁，如果获取成功则成功，如果失败则失败
        //tryLock(1,null) 在超时时间以内，循环获取锁、
        lock.tryLock();
        lock.tryLock(1,null); //InterruptedException

        /**
         * AQS -acquireInterruptibly
         */
        // lockInterruptibly方法，是一个支持中断的加锁方式。他与 lock.tryLock(1,null) 这个有什么区别？
        // 相同点：都支持中断
        //不同点： lockInterruptibly方法仅仅支持中断；不支持超时。lock.tryLock(1,null)即支持超时，也支持超时内的时间中断
        lock.lockInterruptibly();
        lock.isHeldByCurrentThread();

        /**
         * AQS - release
         * unparkSuccessor
         *
         */
        // 调用tryRelease，直到释放掉所有的锁（state =0），因为考虑有重入的情况。然后唤醒后继（unparkSuccessor）线程让他进行锁竞争。
        lock.unlock();

        /**
         * AQS - hasQueuedPredecessors // 如果新线程来了，它在queue里吗？没在q里就没有pre节点。没办法，直接加入tail
         * 他是公平锁的关键方法
         */
        lock1.lock();

    }
}
