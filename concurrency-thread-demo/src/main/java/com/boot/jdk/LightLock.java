package com.boot.jdk;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

public class LightLock {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        System.out.println("====A 加锁前==="+ClassLayout.parseInstance(obj).toPrintable());
        Thread A = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("===A 加锁中==="+ClassLayout.parseInstance(obj).toPrintable());
                    Thread.sleep(2000);
                }

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
        Thread.sleep(5000);
        synchronized (obj) {
            System.out.println("====再次加锁中==="+ClassLayout.parseInstance(obj).toPrintable());
        }

        Object objnew = new Object();
        synchronized (objnew) {
            System.out.println("====新对象加锁中==="+ClassLayout.parseInstance(objnew).toPrintable());
        }
    }

}