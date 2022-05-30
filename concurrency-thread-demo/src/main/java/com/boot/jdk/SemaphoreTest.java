package com.boot.jdk;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    static Semaphore s = new Semaphore(10);
    public static void main(String[] args) {
        for(int i = 0 ; i < 100; i++) {
            Thread a = new Thread(() -> {
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("链接db，保存数据。");
                s.release();
            });
            a.start();
        }
    }
}
