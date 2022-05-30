package com.boot.jdk;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    static Exchanger<String> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        Thread A = new Thread(() -> {
            String a = "aa";
            String b = "";
            try {
                b = exchanger.exchange(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程A： " + a + b);
        });
        Thread B = new Thread(() -> {
            String b = "bb";
            String a = "";
            try {
               a = exchanger.exchange(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程B： " + b  + a);
        });
        A.start();
        B.start();
    }
}
