package com.gupaoedu.concurrent;

import java.util.Locale;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ThreadLocalExample {
    //希望每一个线程获得的num都是0
//    private static int num=0;

    //全局用户信息的存储

    static ThreadLocal<Integer> local= ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread[] thread=new Thread[5];
        for(int i=0;i<5;i++){
            thread[i]=new Thread(()->{
                int num=local.get().intValue();
                local.set(num+=5);
                System.out.println(Thread.currentThread().getName()+" "+local.get());
            });
        }
        local=null;
        for (int i = 0; i < 5; i++) {
            thread[i].start();
        }
    }
}
