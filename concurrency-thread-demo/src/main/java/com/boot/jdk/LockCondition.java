package com.boot.jdk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition<T> {
    private Object[]    items;
    // 添加的下标，删除的下标和数组当前数量
    private int addIndex, removeIndex, count;
    private Lock lock     = new ReentrantLock(); //创建锁
    private Condition notEmpty = lock.newCondition();//创建condition。 1个等待队列
    private Condition notFull = lock.newCondition();//创建condition   2个等待队列
    // 是不是我们的阻塞队列都是如此实现的呢？ 后续我们会对队列进行一个分析，也会看源码，到时候你就能看到
    // 队列的头和队列的尾都是分别创建了一个condition，就是为了将我们的队列的双端的等待队列进行区分，互不影响。
    public LockCondition(int size) {
        items = new Object[size];
    }
    // 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) // 如果生产已满
                notFull.await(); //生产者暂定生产，去等待队列吧
            items[addIndex] = t;
            if (++addIndex == items.length)
                addIndex = 0;
            ++count;
            notEmpty.signal(); //告诉消费者线程开始消费吧
        } finally {
            lock.unlock();
        }
    }
    // 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await(); //没东西了，消费者的线程无法进行消费，进入等待队列
            Object x = items[removeIndex];
            if (++removeIndex == items.length)
                removeIndex = 0;
            --count;
            notFull.signal();// 通知生产者线程赶紧工作。
            return (T) x;
        } finally {
            lock.unlock();
        }
    }
}
