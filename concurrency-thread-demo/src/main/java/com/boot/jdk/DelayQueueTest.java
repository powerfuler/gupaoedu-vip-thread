package com.boot.jdk;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> delayQueue = new DelayQueue();
        Message m1 = new Message("First Job",5, TimeUnit.SECONDS);
        Message m2 = new Message("Sec Job",10, TimeUnit.SECONDS);
        delayQueue.add(m2);
        delayQueue.add(m1);

        int length = delayQueue.size();
        for(int i = 0; i < length; i++) {
            Message msg = delayQueue.take();
            System.out.println(msg);
        }
    }
}
