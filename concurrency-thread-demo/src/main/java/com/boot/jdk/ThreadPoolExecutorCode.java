package com.boot.jdk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorCode {
    public static void main(String[] args) {
        ThreadPoolExecutor pool
                = new ThreadPoolExecutor(5,10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(); //创建无界队列
        LinkedBlockingQueue linkedBlockingQueue1 = new LinkedBlockingQueue(10); //创建有界队列
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
    }
}
