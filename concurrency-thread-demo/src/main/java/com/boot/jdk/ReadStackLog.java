package com.boot.jdk;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;

public class ReadStackLog {
    public static void main(String[] args) throws JsonProcessingException {
        new Thread(new TimeWaiting (), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }
}
// 该线程不断地进行睡眠
 class TimeWaiting implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(1000000);
        }
    }
}
// 该线程在Waiting.class实例上等待
 class Waiting implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (Waiting.class) {
                try {
                    Waiting.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
// 该线程在Blocked.class实例上加锁后，不会释放该锁
 class Blocked implements Runnable {
    @SneakyThrows
    public void run() {
        synchronized (Blocked.class) {
            while (true) {
                Thread.sleep(1000000);
            }
        }
    }
}

