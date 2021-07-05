package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CyclicBarrierExample {

    public static void main(String[] args) {
        int n=4;
        CyclicBarrier barrier=new CyclicBarrier(4,()->{
            System.out.println("所有线程都写入完成，继续处理其他任务");
        });  // 4
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier barrier){
            this.cyclicBarrier=barrier;
        }
        @Override
        public void run(){
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"写入数据完毕，等待其他线程");
                cyclicBarrier.await();  //-1的动作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
