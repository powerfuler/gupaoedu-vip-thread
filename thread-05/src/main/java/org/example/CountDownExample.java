package org.example;

import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CountDownExample {

    static CountDownLatch countDownLatch=new CountDownLatch(1);

    static class Thread1 extends Thread{

        @Override
        public void run(){
            //TODO
            try {
                Thread.sleep(500);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //表示我已经干完了
        }
    }
    static class Thread2 extends Thread{

        @Override
        public void run(){
            try {
                Thread.sleep(500);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Thread3 extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(500);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1=new Thread1();
        t1.start();
        Thread2 t2=new Thread2();
        t2.start();
        Thread3 t3=new Thread3();
        t3.start();
        System.out.println("所有线程执行结束");
        countDownLatch.countDown();
    }
}
