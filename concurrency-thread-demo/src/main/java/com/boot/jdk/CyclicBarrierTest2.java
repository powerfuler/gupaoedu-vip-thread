package com.boot.jdk;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 implements Runnable{
    /**
     * 使用 CyclicBarrier
     */
    CyclicBarrier cb = new CyclicBarrier(2,this);
    int[] array = new int[2];
    public void calcute(){
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
    }

    @Override
    public void run() {
        System.out.println(array[0] + array[1]);
    }

    public static void main(String[] args) {
        CyclicBarrierTest2 cyclicBarrierTest2 = new CyclicBarrierTest2();
        cyclicBarrierTest2.calcute();
    }

    // 我们的cycbarrier 能够支持 一个 runnable的 action去做后续的数据的操作。能够适用于更加复杂的
    // 场景。
}
