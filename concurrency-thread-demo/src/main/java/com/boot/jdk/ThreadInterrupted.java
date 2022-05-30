package com.boot.jdk;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        // 休眠5秒，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        //sleep方法响应中断，肯定会中断sleep。在抛出异常之前，会清理掉我们的 中断标志。 会返回false，因为当前线程已经停止了。
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());

        // busy thread ,没有立即响应中断，知识他的中断标志位 显示 被中断，这个是isInterrupted会返回true。
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        // 防止sleepThread和busyThread立刻退出
        TimeUnit.SECONDS.sleep(5);
    }

    static class SleepRunner implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                try {
                    // 先清除标志，后抛异常、（sleep）
                    TimeUnit.SECONDS.sleep(100);
                } catch (Exception e) {
                    System.out.println("======");
                }
            }
        }
    }
    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

}

