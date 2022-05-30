package com.boot.jdk;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadDaemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonThread(),"Daemon Thread!");
        thread.setDaemon(true); // 守护线程
        thread.start();
        // main 线程退出
    }
    static class DaemonThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally { //finally 不能够保证我们的守护线程的最终执行
                System.out.println("FINISH!");
            }
        }
    }
}

