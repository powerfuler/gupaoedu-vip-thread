package com.boot.jdk;

import java.util.concurrent.CountDownLatch;

public class CyclicBarrierTest {
    /**
     * 使用 CountDownLatch
     */
    static CountDownLatch cd = new CountDownLatch(2);
    static int[] array = new int[2];
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(() -> {
            //计算 3*5
            array[0] = 3*5;
            cd.countDown();
        });
        Thread B = new Thread(() -> {
            //计算 10+2
            array[1] = 10+2;
            cd.countDown();
        });
        A.start();
        B.start();
        cd.await();
        System.out.println(array[0] + array[1]);
    }

}
