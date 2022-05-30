package com.boot.jdk;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class SyncSyncLockRelease2 {
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Object obj = new Object(); //创建20个object对象
            list.add(obj);
            System.out.println("====A 加锁前==="+ClassLayout.parseInstance(obj).toPrintable());
            Thread A = new Thread() {
                @SneakyThrows
                @Override
                public void run() { //创建了20条线程
                    synchronized (obj) {
                        System.out.println("===A 加锁中==="+ClassLayout.parseInstance(obj).toPrintable());
                    }
                    Thread.sleep(20000);
                }
            };
            A.start();
        }
        Thread.sleep(200);

        for(int i = 0; i < 20; i++) {
            Object obj = list.get(i);
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

        Thread.sleep(5000);
        synchronized (list.get(19)) {
            System.out.println("===再次加锁==="+ClassLayout.parseInstance(list.get(19)).toPrintable());
        }

        System.out.println("===新对象=="+ClassLayout.parseInstance(new Object()).toPrintable());
    }
}