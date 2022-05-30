package com.boot.jdk;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseAtomicTest {
    static AtomicInteger ai = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            for(int i = 0; i < 5 ; i++) {
                ai.incrementAndGet(); //返回的增加1之后的值
            }
        });
        Thread b = new Thread(() -> {
            for(int i = 0; i < 7 ; i++) {
                ai.incrementAndGet(); //返回的增加1之后的值
            }
        });
        a.start();
        b.start();
        Thread.sleep(100); //为了保障 a b线程执行完毕之后，才继续执行后续的代码、
        System.out.println(ai.get()); // 获取ai的值
    }
}
