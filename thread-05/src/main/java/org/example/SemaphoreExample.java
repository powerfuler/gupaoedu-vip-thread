package org.example;

import com.sun.deploy.panel.JHighDPITable;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SemaphoreExample {

    public static void main(String[] args) {
        //限制资源的并发数量.
        Semaphore semaphore=new Semaphore(10);
        for (int i = 0; i < 20; i++) {
            new Car(i,semaphore).start();
        }
    }
    static class Car extends  Thread{
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run(){
            try {
                semaphore.acquire(); //获得一个令牌
                System.out.println("第 "+num+"俩车抢到一个车位");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("第 "+num+"走喽~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(); //释放一个令牌
            }
        }
    }
}
