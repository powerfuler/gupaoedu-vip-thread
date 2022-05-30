package com.boot.jdk;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class SyncSyncLockRelease1 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        System.out.println("====A 加锁前==="+ClassLayout.parseInstance(obj).toPrintable());
        Thread A = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("===A 加锁中==="+ClassLayout.parseInstance(obj).toPrintable());
                }
                Thread.sleep(2000);
            }
        };
        A.start();
        Thread.sleep(500);
        System.out.println("====B加锁前==="+ClassLayout.parseInstance(obj).toPrintable());
        Thread B = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("====B加锁中==="+ClassLayout.parseInstance(obj).toPrintable());
                    Thread.sleep(1000);
                }
            }
        };
        B.start();
    }
}