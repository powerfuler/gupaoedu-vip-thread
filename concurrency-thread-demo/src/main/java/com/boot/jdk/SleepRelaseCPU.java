package com.boot.jdk;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class SleepRelaseCPU {
    public static void main(String[] args) {

        for(int i = 0; i < 100; i++) {
            Thread thread = new Thread(new SubThread(),"Daemon Thread!"+i);
            thread.start();
        }
    }
    static class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("FINISH!");
            }
        }
    }
}

