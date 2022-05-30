package com.boot.jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SelfLock implements Lock {

    // AQS 呢？如何使用呢？
    private static class Sync extends AbstractQueuedSynchronizer {
        //加锁的时候用 state int 类型
        public boolean tryAcquire(int acquires) {
                if(compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
        }
        //解锁的
        public boolean tryRelease(int releases) {
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(0); // 不用，当前线程释放锁，说明，当前线程持有锁。
            return true;
        }

        //创建condition. wait notify
        Condition newCondition(){
            return new ConditionObject();
        }

        public boolean isLocked(){
            //锁定的话，state == 1
            return getState() == 1;
        }
    }

    private final Sync sync = new Sync();

    public void lock() {
//        sync.tryAcquire(); //调用错误
        sync.acquire(1); //传说中的模板方法吗？ 是的
    }
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public void unlock() {
        sync.release(1);
    }
    public Condition newCondition() {
        return sync.newCondition();
    }
    public boolean isLocked(){
        return sync.isLocked();
    }
    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }
}
