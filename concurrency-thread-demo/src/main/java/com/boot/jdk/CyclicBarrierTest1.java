package com.boot.jdk;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest1 {
    /**
     * 使用 CyclicBarrier
     */
    static CyclicBarrier cb = new CyclicBarrier(3);
    static int[] array = new int[2];
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread A = new Thread(() -> {
            //计算 3*5
            array[0] = 3*5;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread B = new Thread(() -> {
            //计算 10+2
            array[1] = 10+2;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        A.start();
        B.start();
        cb.await();
        System.out.println(array[0] + array[1]);

        // 对于这个场景，建议使用CountdownLatch。因为CountdownLatch里边的参数直接写的是2，就是进行2次倒计时，我们的main函数
        //调用的await。就是告诉我们main线程在等待其他两个线程执行完毕。
        // 然而，对于 CyclicBarrier 这种实现形式，main函数和其他两个线程都被阻塞到了一个地方。此处main函数到达了屏障之后
        //挂起自己，等待其他两个线程达到屏障之后才开始执行。

        // CountdownLatch 针对业务完成之后放行；（赛跑的过程里边的到达终点。）
        // CyclicBarrier 针对的是所有的线程在统一的屏障集合之后开始 （赛跑过程里边的起跑线。）
    }

}
